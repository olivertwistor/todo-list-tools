package nu.olivertwistor.todolisttools;

import nu.olivertwistor.todolisttools.menus.MainMenu;
import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.ErrorMessage;
import nu.olivertwistor.todolisttools.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.IOException;

/**
 * Main class for this app. Contains the main method.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"HardCodedStringLiteral", "ClassUnconnectedToPackage"})
final class App
{
    private static final @NonNls Logger LOG = LogManager.getLogger(App.class);

    /**
     * Prints a short privacy policy, loads configuration and then creates the
     * main menu.
     *
     * @param args unused
     *
     * @since 1.0.0
     */
    public static void main(final String[] args)
    {
        LOG.trace("Entering main(String[])...");

        System.out.println("==============");
        System.out.println("Todo List Tool");
        System.out.println("==============");
        System.out.println();
        System.out.println(String.join(System.lineSeparator(),
                "This program is collecting data from your Remember the Milk ",
                "account, in order to present aggregations and calculations ",
                "on lists and smartlists. For more information, please read ",
                "the file 'privacy-policy.md' located in the root folder."));
        System.out.println();
        System.out.println(String.join(System.lineSeparator(),
                "This product uses the Remember The Milk API but is not ",
                "endorsed or certified by Remember The Milk."));
        System.out.println();

        // Load a config file from command-line arguments.
        if (args.length < 1)
        {
            System.out.println("Missing argument: path to config file");
            return;
        }
        final Config config;
        try
        {
            final File file = new File(args[0]);
            config = new Config(file);
            LOG.info("Loaded config: {}", file.getAbsolutePath());
        }
        catch (final IOException e)
        {
            ErrorMessage.printAndLogFatal(
                    LOG, ErrorMessage.LOAD_CONFIG_FILE, e);
            return;
        }
        final Session session = new Session();

        final MainMenu mainMenu = new MainMenu(config, session);
        boolean exit;
        do
        {
            mainMenu.show();
            exit = mainMenu.act();
        }
        while (!exit);
    }

    /**
     * Empty constructor. This class doesn't need to be instantiated, because
     * it's the entry point for this app.
     *
     * @since 1.0.0
     */
    private App()
    {
        LOG.trace("Entering App()...");
    }
}
