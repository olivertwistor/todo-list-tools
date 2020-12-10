package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.Session;
import nu.olivertwistor.todolisttools.util.Config;

/**
 * Classes implementing this interface define some action that can be executed
 * when a menu item is selected.
 *
 * @since 0.1.0
 */
@FunctionalInterface
public interface MenuAction
{
    /**
     * Executes the action.
     *
     * @param config  Config object to use throughout this app
     * @param session Session containing the timeline for this app run
     *
     * @since 0.1.0
     */
    void execute(Config config, Session session);
}
