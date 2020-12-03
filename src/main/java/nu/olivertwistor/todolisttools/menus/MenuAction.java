package nu.olivertwistor.todolisttools.menus;

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
     * @since 0.1.0
     */
    void execute();
}
