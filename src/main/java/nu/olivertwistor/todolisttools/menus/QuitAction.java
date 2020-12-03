package nu.olivertwistor.todolisttools.menus;

/**
 * Prints out a goodbye message and exits the application.
 *
 * @since 0.1.0
 */
public class QuitAction implements MenuAction
{
    @Override
    public void execute()
    {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
