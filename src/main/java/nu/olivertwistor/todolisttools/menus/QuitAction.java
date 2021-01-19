package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.util.Session;
import nu.olivertwistor.todolisttools.util.Config;

/**
 * Prints out a goodbye message and exits the application.
 *
 * @since 1.0.0
 */
public final class QuitAction implements MenuAction
{
    @Override
    public boolean execute(final Config config, final Session session)
    {
        System.out.println("Goodbye!");
        return true;
    }
}
