package nu.olivertwistor.todolisttools;

import nu.olivertwistor.todolisttools.menus.MainMenu;
import nu.olivertwistor.todolisttools.util.Config;
import org.ini4j.InvalidFileFormatException;

import java.io.IOException;

/**
 * Main class for this app. Contains the main method.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class App
{
    /**
     * Prints a short privacy policy and then creates the main menu. Loops
     * indefinitely. Thereby, it's very important that at least one of the main
     * menu items calls {@link System#exit(int)}.
     *
     * @param configFilePath path to config file
     *
     * @since 0.1.0
     */
    private App(final String configFilePath)
    {
        // We must first see whether we can load the config.
        Config config = null;
        try
        {
            config = new Config(configFilePath);
        }
        catch (final InvalidFileFormatException e)
        {
            System.err.println(
                    "The given config file hasn't got the correct format:");
            System.exit(1);
        }
        catch (final IOException e)
        {
            System.err.println("The given config file couldn't be opened.");
            System.exit(1);
        }

        System.out.println("Todo List Tool");
        System.out.println("==============");
        System.out.println();
        System.out.println(String.join(System.lineSeparator(),
                "This program is collecting data from your Remember the Milk ",
                "account, in order to present aggregations and calculations ",
                "on lists and smartlists. For more information, please read ",
                "the file \"privacy-policy.md\" located in the root folder."));
        System.out.println();
        System.out.println(String.join(System.lineSeparator(),
                "This product uses the Remember The Milk API but is not ",
                "endorsed or certified by Remember The Milk."));
        System.out.println();

        final MainMenu mainMenu = new MainMenu(config);
        do
        {
            mainMenu.show();
            mainMenu.act();
        }
        while (true);
    }

    /**
     * If the program starts with the correct number of arguments, an instance
     * of this class is created.
     *
     * @param args program arguments; should contain a path to a config file
     *
     * @since 0.1.0
     */
    public static void main(final String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Too few arguments.");
            System.err.println("Usage: java -jar todo-list-tools.jar [path " +
                    "to config file]");
            System.exit(1);
        }

        new App(args[0]);
    }
}
