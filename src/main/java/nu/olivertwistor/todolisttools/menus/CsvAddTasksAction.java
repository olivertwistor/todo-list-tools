package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.java.tui.Terminal;
import nu.olivertwistor.todolisttools.models.Task;
import nu.olivertwistor.todolisttools.rtmapi.rest.AddTask;
import nu.olivertwistor.todolisttools.rtmapi.rest.CreateTimeline;
import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.ErrorMessage;
import nu.olivertwistor.todolisttools.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adds tasks to Remember The Milk based on a user supplied CSV file.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"HardCodedStringLiteral", "StringConcatenation"})
public final class CsvAddTasksAction implements MenuAction
{
    private static final Logger LOG = LogManager.getLogger(
            CsvAddTasksAction.class);

    private static final int MILLISECONDS_PER_REQUEST = 1_500;

    @Override
    public boolean execute(final Config config, final Session session)
    {
        LOG.trace("Entering execute(Config, Session)...");

        try
        {
            createTimeline(config, session);
        }
        catch (final IOException e)
        {
            ErrorMessage.printAndLogError(
                    LOG, ErrorMessage.COMMUNICATE_WITH_RTM, e);
            return false;
        }

        final String[] csvUserInput;
        try
        {
            csvUserInput = readCsvUserInput();
        }
        catch (final IOException e)
        {
            ErrorMessage.printAndLogError(
                    LOG, ErrorMessage.READ_USER_INPUT, e);
            return false;
        }

        final Path filePath = Paths.get(csvUserInput[0]);
        final File file = filePath.toFile();
        final List<Task> parsedFile;
        final int nParsedTasks;
        try
        {
            parsedFile = parseCsvFile(file, csvUserInput[1]);
            nParsedTasks = parsedFile.size();
            LOG.info("Parsed {} tasks from {}.",
                    nParsedTasks, file.getAbsolutePath());
        }
        catch (final IOException e)
        {
            ErrorMessage.printAndLogError(
                    LOG, ErrorMessage.CSV_FILE_NOT_FOUND, e);
            return false;
        }

        LOG.debug("Looping through list of tasks: {}", parsedFile);
        int nAddedTasks = 0;
        int currentTaskId = 0;
        for (final Task task : parsedFile)
        {
            currentTaskId++;
            LOG.debug("Looking at: {}", task);

            final String smartAdd = task.toSmartAdd();
            final AddTask addTask;
            try
            {
                addTask = new AddTask(config, session, smartAdd);
            }
            catch (final IOException e)
            {
                ErrorMessage.printAndLogError(
                        LOG, ErrorMessage.COMMUNICATE_WITH_RTM, e);
                return false;
            }
            if (addTask.isResponseSuccess())
            {
                nAddedTasks++;
                System.out.println("Added task " + currentTaskId + '/' +
                        nParsedTasks + '.');
                LOG.debug("Added {}.", task);
            }
            else
            {
                System.out.println("Failed to add task " + currentTaskId +
                        '/' + nParsedTasks + '.');
                LOG.error("Failed to add {}.", task);
            }

            try
            {
                // Wait a moment until adding the next task to make sure we
                // adhere to the API rate limit.
                Thread.sleep(CsvAddTasksAction.MILLISECONDS_PER_REQUEST);
            }
            catch (final InterruptedException e)
            {
                LOG.warn(e);
            }
        }

        System.out.println("Successfully added " + nAddedTasks + " tasks " +
                "out of " + nParsedTasks + '.');
        LOG.info("Finished looping through list of tasks. Added: {}; " +
                "failed: {}.", nAddedTasks, nParsedTasks - nAddedTasks);

        return false;
    }

    /**
     * Parses a CSV file into a list of Task objects.
     *
     * @param file      the file to parse
     * @param delimiter the delimiting character between columns in the file
     *
     * @return A list of Task objects.
     *
     * @throws IOException if the CSV file couldn't be opened.
     *
     * @since 1.0.0
     */
    static List<Task> parseCsvFile(final File file, final String delimiter)
            throws IOException
    {
        LOG.trace("Entering parseCsvFile(File, String)...");

        // Read the file line by line and split each line into columns.
        final Path filePath = file.toPath();
        try (final BufferedReader br = Files.newBufferedReader(
                filePath, StandardCharsets.UTF_8))
        {
            // Skip the first line.
            final String skippedLine = br.readLine();
            int nReadLines = 0;
            final List<Task> tasks = new ArrayList<>();
            if (skippedLine != null)
            {
                LOG.debug("Skipped the first line of the CSV file: {}.",
                        skippedLine);

                for (String line = br.readLine(); line != null;
                     line = br.readLine())
                {
                    nReadLines++;
                    LOG.debug("Reading line {}.", nReadLines + 1);

                    final String[] columns = line.split(delimiter, -1);
                    LOG.debug("Columns: {}", Arrays.toString(columns));

                    final Task task = new Task(columns[0]);
                    tasks.add(task);
                    LOG.debug("Added {} to the list.", task);
                }
            }

            LOG.info("Read {} lines after the first line.", nReadLines);

            return tasks;
        }
    }

    /**
     * Creates a new timeline by requesting one from the Remember The Milk API,
     * and stores it within this session object. If there already exists a
     * timeline, this method does nothing.
     *
     * @param config  Config object containing the API key etc.
     * @param session active session where the timeline is stored
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 1.0.0
     */
    private static void createTimeline(final Config config,
                                       final Session session) throws IOException
    {
        LOG.trace("Entering createTimeline(Config, Session)...");

        if (session.hasTimeline())
        {
            LOG.debug("Session already has a timeline.");
            return;
        }

        LOG.debug("Session has no timeline.");

        final CreateTimeline createTimeline = new CreateTimeline(config);
        final String timeline = createTimeline.getTimeline();
        session.setTimeline(timeline);

        LOG.info("Created new timeline: {}", timeline);
    }

    /**
     * Asks the user for the path to a CSV file to load, as well as for the
     * delimiter between columns in that file.
     *
     * @return The path to a CSV file and the delimiter between columns,
     *         together in an array.
     *
     * @throws IOException if reading from standard input failed.
     *
     * @since 1.0.0
     */
    private static String[] readCsvUserInput() throws IOException
    {
        LOG.trace("Entering reaCsvUserInput()...");

        System.out.println(String.join(System.lineSeparator(),
                "Here, you can specify a CSV file containing tasks to add to ",
                "Remember The Milk. Please note that the first row is ",
                "considered headings, and the only task property currently ",
                "implemented is name and it must be in the first column. All ",
                "other columns are ignored. The tasks will be added to ",
                "Inbox, and have the default due date and neither tags nor ",
                "time estimates."));
        System.out.println();

        final String csvFileInput = Terminal.readString(
                "Path to the CSV file to load: ");
        final String csvDelimiter = Terminal.readString(
                "By which character is the columns separated? ");

        return new String[] { csvFileInput, csvDelimiter };
    }
}
