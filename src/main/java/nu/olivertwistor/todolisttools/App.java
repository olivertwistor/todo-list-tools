package nu.olivertwistor.todolisttools;

public class App
{
    public App(final String configFilePath)
    {
        System.out.println("Config file: " + configFilePath);
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
