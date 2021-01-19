package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.java.tui.Terminal;
import nu.olivertwistor.todolisttools.rtmapi.rest.CreateTimeline;
import nu.olivertwistor.todolisttools.util.Session;
import nu.olivertwistor.todolisttools.models.Task;
import nu.olivertwistor.todolisttools.rtmapi.rest.AddTask;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Adds tasks to Remember The Milk based on a user supplied CSV file.
 *
 * @since 1.0.0
 */
public final class CsvAddTasksAction implements MenuAction
{
    private static final int SECONDS_PER_REQUEST = 1_500;

    @Override
    public boolean execute(final Config config, final Session session)
    {
        CsvAddTasksAction.createTimeline(config, session);

        final String[] csvUserInput;
        csvUserInput = CsvAddTasksAction.readCsvUserInput();

        List<Task> parsedFile = null;
        final Path filePath = Paths.get(csvUserInput[0]);
        final File file = filePath.toFile();
        parsedFile = CsvAddTasksAction.parseCsvFile(file, csvUserInput[1]);

        for (final Task task : parsedFile)
        {
            final String smartAdd = task.toSmartAdd();
            final AddTask addTask = new AddTask(config, session, smartAdd);
            if (addTask.isResponseSuccess())
            {
                System.out.println("Added task to RTM: " + smartAdd);
            }
            else
            {
                System.out.println(
                        "Failed to add task tag to RTM: " + smartAdd);
            }

            try
            {
                // Wait a moment until adding the next task to make sure we
                // adhere to the API rate limit.
                Thread.sleep(CsvAddTasksAction.SECONDS_PER_REQUEST);
            }
            catch (final InterruptedException e)
            {
                System.out.println("Current thread was interrupted.");
            }
        }

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
     * @since 1.0.0
     */
    static List<Task> parseCsvFile(final File file, final String delimiter)
            throws IOException
    {
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
                System.out.println("Skipped line: " + skippedLine);

                for (String line = br.readLine(); line != null;
                     line = br.readLine())
                {
                    nReadLines++;

                    final String[] columns = line.split(delimiter, -1);
                    final Task task = new Task(columns[0]);
                    tasks.add(task);
                }
            }

            System.out.println("Read " + nReadLines + " lines.");
            return tasks;
        }
    }

    /**
     * Creates a new timeline by requesting one from the Remember The Milk API,
     * and stores it within this session object.
     *
     * Note that this action will invalidate the last timeline, making all
     * actions made before calling this method undoable.
     *
     * @since 1.0.0
     */
    private static void createTimeline(final Config config,
                                       final Session session)
    {
        if (!session.hasTimeline())
        {
            final CreateTimeline createTimeline = new CreateTimeline(config);
            final String timeline = createTimeline.getTimeline();
            session.setTimeline(timeline);
        }
    }

    /**
     * Asks the user for the path to a CSV file to load, as well as for the
     * delimiter between columns in that file.
     *
     * @return The path to a CSV file and the delimiter between columns,
     *         together in an array.
     *
     * @since 1.0.0
     */
    private static String[] readCsvUserInput()
    {
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

        Objects.requireNonNull(csvFileInput,
                () -> "Failed to find file: " + csvFileInput);

        return new String[] { csvFileInput, csvDelimiter };
    }
}
