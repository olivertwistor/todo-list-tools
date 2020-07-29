package nu.olivertwistor.todolisttools;

public class App
{
    public App(final String configFilePath)
    {
        System.out.println("Todo List Tool");
        System.out.println("==============");
        System.out.println();
        System.out.println("This app is collecting data from your Remember " +
                "the Milk account, in order to present aggregations and " +
                "calculations on lists and smartlists. For more " +
                "information, please read the file \"privacy-policy.md\" " +
                "located in the root folder.");
        System.out.println();
    }

    public static void main(final String[] args)
    {
        // Read program arguments.
        if (args.length < 1)
        {
            System.err.println("Too few arguments.");
            System.out.println("Usage: java -jar todo-list-tools.jar [path " +
                    "to config file]");
            System.exit(1);
        }

        new App(args[0]);
    }
}
