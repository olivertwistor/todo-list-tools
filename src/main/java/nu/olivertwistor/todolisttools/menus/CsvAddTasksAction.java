package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.java.tui.Terminal;
import nu.olivertwistor.todolisttools.Session;
import nu.olivertwistor.todolisttools.models.Task;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.methods.AddTask;
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

/**
 * Adds tasks to Remember The Milk based on a user supplied CSV file.
 *
 * @since 0.1.0
 */
public class CsvAddTasksAction implements MenuAction
{
    private static final int seconds_per_request = 1500;

    @Override
    public void execute(final Config config, final Session session)
    {
        try
        {
            createTimeline(session);
        }
        catch (final DocumentException | NoSuchAlgorithmException |
                IOException e)
        {
            System.out.println("Failed to create a new timeline.");
            return;
        }

        final String[] csvUserInput;
        try
        {
            csvUserInput = readCsvUserInput();
        }
        catch (final IOException e)
        {
            System.out.println("Failed to read user input.");
            return;
        }

        List<Task> parsedFile = null;
        try
        {
            final Path filePath = Paths.get(csvUserInput[0]);
            final File file = filePath.toFile();

            parsedFile = parseCsvFile(file, csvUserInput[1]);
        }
        catch (final InvalidPathException | IOException e)
        {
            System.out.println("Failed to parse the CSV file.");
        }

        if (parsedFile == null)
        {
            System.out.println("Failed to parse the CSV file into tasks.");
            return;
        }

        for (final Task task : parsedFile)
        {
            final String smartAdd = task.toSmartAdd();
            try
            {
                final AddTask addTask = new AddTask(config, session, smartAdd);
                final Response response = addTask.getResponse();
                if (response.isResponseSuccess())
                {
                    System.out.println("Added task to RTM: " + smartAdd);
                }
                else
                {
                    System.out.println(
                            "Failed to add task tag to RTM: " + smartAdd);
                }
            }
            catch (final DocumentException | NoSuchAlgorithmException |
                    IOException e)
            {
                System.out.println(
                        "Failed to make an add task request to the RTM API.");
            }

            try
            {
                // Wait a moment until adding the next task to make sure we
                // adhere to the API rate limit.
                Thread.sleep(seconds_per_request);
            }
            catch (final InterruptedException e)
            {
                System.out.println("Current thread was interrupted.");
            }
        }
    }

    /**
     * Parses a CSV file into a list of Task objects.
     *
     * @param file      the file to parse
     * @param delimiter the delimiting character between columns in the file
     *
     * @return A list of Task objects.
     *
     * @throws IOException if the file couldn't be read
     *
     * @since 0.1.0
     */
    static List<Task> parseCsvFile(final File file, final String delimiter)
            throws IOException
    {
        final List<Task> tasks = new ArrayList<>();

        // Read the file line by line and split each line into columns.
        try (final BufferedReader br = Files.newBufferedReader(
                file.toPath(), StandardCharsets.UTF_8))
        {
            // Skip the first line.
            final String skippedLine = br.readLine();
            int nReadLines = 0;
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
        }

        return tasks;
    }

    /**
     * If the session has no timeline, it's created.
     *
     * @param session Session object with or without a timeline
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    private static void createTimeline(final Session session)
            throws DocumentException, NoSuchAlgorithmException,
            MalformedURLException, IOException
    {
        if (!session.hasTimeline())
        {
            session.createTimeline();
        }
    }

    /**
     * Asks the user for the path to a CSV file to load, as well as for the
     * delimiter between columns in that file.
     *
     * @return The path to a CSV file and the delimiter between columns,
     *         together in an array.
     *
     * @throws FileNotFoundException if the file couldn't be found
     * @throws IOException           if user input couldn't be read
     *
     * @since 0.1.0
     */
    private static String[] readCsvUserInput()
            throws FileNotFoundException, IOException
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

        if (csvFileInput == null)
        {
            throw new FileNotFoundException(
                    "Failed to find file: " + csvFileInput);
        }

        return new String[] { csvFileInput, csvDelimiter };
    }
}
