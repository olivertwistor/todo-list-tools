package nu.olivertwistor.todolisttools;

import ch.rfin.util.Pair;
import nu.olivertwistor.java.tui.Terminal;
import nu.olivertwistor.java.tui.UnclosableInputStream;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;

/**
 * The main menu for this application. Menu items are created in the
 * constructor. Call {@link #show()} to print the menu. Call {@link #act()} to
 * ask for user input and do different things depending on the chosen menu item.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class MainMenu
{
    private static final String val_read_permissions = "read";

    private final Config config;
    private final SortedMap<String, Pair<String, Consumer<String>>> menuItems;

    /**
     * Creates a new HashMap for the menu items and adds the menu items.
     *
     * Each menu item consists of the following:
     * <ul>
     *     <li>character or short string the user writes to select the menu
     *     item</li>
     *     <li>longer description displayed in the menu</li>
     *     <li>function name for the action; called in {@link #act()}</li>
     * </ul>
     *
     * @param config Config object to use throughout this app
     *
     * @since 0.1.0
     */
    public MainMenu(final Config config)
    {
        this.config = config;
        this.menuItems = new TreeMap<>();
        this.menuItems.put("o", Pair.of("[O]btain authentication",
                str -> this.obtainAuthentication(str)));
        this.menuItems.put("q", Pair.of("[Q]uit",
                str -> quit(str)));
    }

    /**
     * Prints out the main menu to stdout.
     *
     * @since 0.1.0
     */
    public void show()
    {
        System.out.println("Main menu");
        System.out.println("---------");
        for (final Pair<String, Consumer<String>> pair :
                this.menuItems.values())
        {
            System.out.println(pair.get_1());
        }
        System.out.println();
    }

    /**
     * Reads a line from user input and calls the corresponding function from
     * the menu items Map.
     *
     * If any error occurs in reading user input, the program is terminated.
     *
     * @since 0.1.0
     */
    public void act()
    {
        // Temporarily set the system InputStream to our own InputStream that
        // doesn't close.
        System.setIn(new UnclosableInputStream(System.in));

        try (final BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8)))
        {
            boolean validItem = false;

            while (!validItem)
            {
                // Read user input.
                System.out.print("? ");
                final String input = br.readLine().toLowerCase(Locale.ENGLISH);

                // Call the appropriate method.
                final Pair<String, Consumer<String>> pair =
                        this.menuItems.get(input);
                if (pair != null)
                {
                    final Consumer<String> item = pair.get_2();
                    if (item != null)
                    {
                        validItem = true;
                        item.accept(input);
                    }
                }
                else
                {
                    System.out.println("That menu item doesn't exist.");
                }
            }
            System.out.println();
        }
        catch (final IOException e)
        {
            System.err.println(
                    "Failed to read user input: " + e.getLocalizedMessage());
        }
        finally
        {
            // Reset the system InputStream to System.in.
            System.setIn(System.in);
        }
    }

    /**
     * Asks the user to go to the provided URL to give this application the
     * required permissions. When that is done, an authentication token is
     * retrieved from the Remember The Milk service and it is stored in the
     * config file for this application.
     *
     * @param str unused
     *
     * @since 0.1.0
     */
    @SuppressWarnings({"OverlyLongMethod", "CallToPrintStackTrace"})
    private void obtainAuthentication(final String str)
    {
        System.out.println(String.join(System.lineSeparator(),
                "In order to use this program, first you have to go to the ",
                "Remember The Milk website and give it this program ",
                "permission to access your account. Please enter the ",
                "following URL in a web browser and follow the instructions. ",
                "Afterwards, return to this program."));
        System.out.println();

        final Authentication authentication = new Authentication();
        try
        {
            final URL authUrl = authentication.generateAuthRequest(
                    this.config, val_read_permissions);
            System.out.println(authUrl.toExternalForm());
        }
        catch (final NoSuchElementException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (final DocumentException e)
        {
            System.err.println("Failed to read authentication response.");
            e.printStackTrace();
            System.exit(1);
        }
        catch (final NoSuchAlgorithmException e)
        {
            System.err.println(
                    "Failed to create hash for communication with the API.");
            e.printStackTrace();
            System.exit(1);
        }
        catch (final IOException e)
        {
            System.err.println("I/O error.");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("When you are done giving this program the " +
                "required permissions, press ENTER.");
        try
        {
            Terminal.readString();
        }
        catch (final IOException e)
        {
            System.err.println("Failed to read user input.");
            e.printStackTrace();
            System.exit(1);
        }

        // Now when we have obtained authentication and the user have confirmed
        // that, we can retrieve the token we will use for any subsequent calls
        // to the API.
        final String token;
        try
        {
            token = authentication.obtainToken(this.config);

            // Store the retrieved token to the config file.
            this.config.setToken(token);
        }
        catch (final DocumentException e)
        {
            System.err.println("Failed to read authentication response.");
            e.printStackTrace();
            System.exit(1);
        }
        catch (final NoSuchAlgorithmException e)
        {
            System.err.println(
                    "Failed to create hash for communication with the API.");
            e.printStackTrace();
            System.exit(1);
        }
        catch (final IOException e)
        {
            System.err.println("I/O error.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Prints out a goodbye message and exits the application.
     *
     * @param str unused
     *
     * @since 0.1.0
     */
    private static void quit(final String str)
    {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    @Override
    public String toString()
    {
        return "MainMenu{config=" + this.config + ", " +
                "menuItems=" + this.menuItems + "}";
    }
}
