package nu.olivertwistor.todolisttools.menus;

import ch.rfin.util.Pair;
import nu.olivertwistor.java.tui.Terminal;
import nu.olivertwistor.todolisttools.Session;
import nu.olivertwistor.todolisttools.rtmapi.methods.AddTask;
import nu.olivertwistor.todolisttools.util.Config;
import sun.text.resources.cldr.fo.FormatData_fo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class CsvAddTasksAction implements MenuAction
{
    private final Config config;
    private final Session session;

    public CsvAddTasksAction(final Config config, final Session session)
    {
        this.config = config;
        this.session = session;
    }

    @Override
    public void execute()
    {
        System.out.println(String.join(System.lineSeparator(),
                "Here, you can specify a CSV file containing tasks to add to ",
                "Remember The Milk. Please note that the file must have ",
                "column names as its first row and that the columns (and ",
                "column names) must exactly match task properties in ",
                "Remember The Milk. For example, for due dates to be ",
                "recognized they must be in a column with the column name ",
                "\"Due\"."));
        System.out.println();

        String csvFileInput = null;
        String csvDelimiter = null;
        try
        {
            csvFileInput = Terminal.readString(
                    "Path to the CSV file to load: ");
            csvDelimiter = Terminal.readString(
                    "By which character is the columns separated? ");
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }

        if (csvFileInput != null)
        {
            Map<String, List<String>> parsedFile = null;
            try
            {
                final Path filePath = Paths.get(csvFileInput);
                final File file = filePath.toFile();

                parsedFile = this.parseCsvFile(file, csvDelimiter);
            }
            catch (final InvalidPathException | IOException |
                    URISyntaxException e)
            {
                e.printStackTrace();
            }

            if (parsedFile != null)
            {
                throw new UnsupportedOperationException("not implemented");
            }
            else
            {
                System.out.println("Failed to parse the CSV file.");
            }
        }
        else
        {
            System.out.println("No filename entered.");
        }
    }

    Map<String, List<String>> parseCsvFile(final File file,
                                           final String delimiter)
            throws IOException, URISyntaxException
    {
        final Map<String, List<String>> out = new HashMap<>();

        // Read the file line by line and split each line into columns.
        try (final BufferedReader br = Files.newBufferedReader(
                file.toPath(), StandardCharsets.UTF_8))
        {
            // Take a look at the first line because it contains the column
            // titles. Put each of them in the map to return and instantiate a
            // list for each of them. These lists will hold the rest of the
            // column values.
            String line = br.readLine();
            if (line != null)
            {
                final String[] columnTitles = line.split(delimiter, -1);
                for (final String columnTitle : columnTitles)
                {
                    out.put(columnTitle, new LinkedList<>());
                }

                // Now it's time to fill those lists, by looping through all
                // the other lines in the file and store everything in its
                // right place.
                for (line = br.readLine(); line != null; line = br.readLine())
                {
                    final String[] columns = line.split(delimiter, -1);
                    final int nColumns = columns.length;
                    for (int i = 0; i < nColumns; i++)
                    {
                        final String columnTitle = columnTitles[i];
                        final List<String> list = out.get(columnTitle);
                        if (list != null)
                        {
                            list.add(columns[i]);
                        }
                        else
                        {
                            System.err.println("Failed to find the column " +
                                    "title " + columnTitle);
                        }
                    }
                }
            }
            else
            {
                System.err.println("The provided CSV file is empty.");
            }
        }

        return out;
    }
}
