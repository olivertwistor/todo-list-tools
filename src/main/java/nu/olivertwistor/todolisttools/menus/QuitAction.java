package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.util.Session;
import nu.olivertwistor.todolisttools.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Prints out a goodbye message and exits the application.
 *
 * @since 1.0.0
 */
@SuppressWarnings("HardCodedStringLiteral")
public final class QuitAction implements MenuAction
{
    private static final Logger LOG = LogManager.getLogger(QuitAction.class);

    @Override
    public boolean execute(final Config config, final Session session)
    {
        LOG.trace("Entering execute(Config, Session)...");

        System.out.println("Goodbye!");
        return true;
    }
}
