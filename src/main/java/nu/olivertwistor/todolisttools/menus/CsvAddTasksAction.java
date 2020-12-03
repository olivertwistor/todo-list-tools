package nu.olivertwistor.todolisttools.menus;

import ch.rfin.util.Pair;
import nu.olivertwistor.java.tui.Terminal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class CsvAddTasksAction implements MenuAction
{
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

        try
        {
            final String csvFileInput = Terminal.readString(
                    "Name of the CSV file to load: ");
            final String csvDelimiter = Terminal.readString(
                    "By which character is the columns separated? ");

            // Begin the parsing of the file.
            final Map<String, List<String>> parsedFile =
                    this.parseCsvFile(csvFileInput, csvDelimiter);
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }

    Map<String, List<String>> parseCsvFile(final String filename,
                                           final String delimiter)
            throws IOException
    {
        final Map<String, List<String>> out = new HashMap<>();

        // Read the file line by line and split each line into columns.
        try (final BufferedReader br = Files.newBufferedReader(
                new File(filename).toPath(), StandardCharsets.UTF_8))
        {
            // Take a look at the first line because it contains the column
            // titles. Put each of them in the map to return and instantiate a
            // list for each of them. These lists will hold the rest of the
            // column values.
            String line = br.readLine();
            if (line != null)
            {
                final String[] columnTitles = line.split(delimiter);
                for (final String columnTitle : columnTitles)
                {
                    out.put(columnTitle, new LinkedList<>());
                }

                // Now it's time to fill those lists, by looping through all
                // the other lines in the file and store everything in its
                // right place.
                do
                {
                    line = br.readLine();
                    final String[] columns = line.split(delimiter);
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
                while (line != null);
            }
            else
            {
                System.err.println("The provided CSV file is empty.");
            }
        }

        return out;
    }
}
