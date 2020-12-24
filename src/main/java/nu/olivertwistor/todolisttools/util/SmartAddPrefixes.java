package nu.olivertwistor.todolisttools.util;

/**
 * This class holds the various smart add prefixes, such as ^ for due date and
 * @ for location.
 *
 * @since 0.1.0
 */
public enum SmartAddPrefixes
{
    /**
     * Prefix for the name attribute.
     *
     * @since 0.1.0
     */
    NAME(""),

    /**
     * Prefix for the URL attribute.
     *
     * @since 0.1.0
     */
    URL(""),

    /**
     * Prefix for the start date attribute.
     *
     * @since 0.1.0
     */
    START("~"),

    /**
     * Prefix for the due date attribute.
     *
     * @since 0.1.0
     */
    DUE("^"),

    /**
     * Prefix for the repeat attribute.
     *
     * @since 0.1.0
     */
    REPEAT("*"),

    /**
     * Prefix for the location attribute.
     *
     * @since 0.1.0
     */
    LOCATION("@"),

    /**
     * Prefix for the priority attribute.
     *
     * @since 0.1.0
     */
    PRIORITY("!"),

    /**
     * Prefix for the list attribute.
     *
     * @since 0.1.0
     */
    LIST("#"),

    /**
     * Prefix for the tag attribute.
     *
     * @since 0.1.0
     */
    TAG("#"),

    /**
     * Prefix for the time estimate attribute.
     *
     * @since 0.1.0
     */
    TIME_ESTIMATE("="),

    /**
     * Prefix for the comments attribute.
     *
     * @since 0.1.0
     */
    COMMENTS("//");

    private final String prefix;

    SmartAddPrefixes(final String prefix)
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return this.prefix;
    }
}
