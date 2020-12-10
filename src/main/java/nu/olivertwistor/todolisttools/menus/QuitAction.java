package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.Session;
import nu.olivertwistor.todolisttools.util.Config;

/**
 * Prints out a goodbye message and exits the application.
 *
 * @since 0.1.0
 */
public class QuitAction implements MenuAction
{
    @Override
    public void execute(final Config config, final Session session)
    {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
