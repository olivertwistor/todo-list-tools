package nu.olivertwistor.todolisttools;

import ch.rfin.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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
    private final Map<String, Pair<String, Consumer<String>>> menuItems;

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
     * @since 0.1.0
     */
    public MainMenu()
    {
        this.menuItems = new HashMap<>();
        this.menuItems.put("q", Pair.of("[Q]uit", str -> this.quit(str)));
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
     * @since 0.1.0
     */
    public void act()
    {
        try (final BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8)))
        {
            boolean validItem = false;

            while (!validItem)
            {
                // Read user input.
                System.out.print("? ");
                final String input = br.readLine();

                // Call the appropriate method.
                final Consumer<String> item = this.menuItems.get(input).get_2();
                if (item != null)
                {
                    validItem = true;
                    item.accept(input);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Prints out a goodbye message and exits the application.
     *
     * @param str unused
     *
     * @since 0.1.0
     */
    private void quit(final String str)
    {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
