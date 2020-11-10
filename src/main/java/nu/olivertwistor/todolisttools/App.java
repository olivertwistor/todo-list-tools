package nu.olivertwistor.todolisttools;

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

        final MainMenu mainMenu = new MainMenu();
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
