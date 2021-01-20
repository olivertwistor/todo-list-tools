package nu.olivertwistor.todolisttools.models;

import org.jetbrains.annotations.NonNls;

/**
 * This enum holds the various smart add prefixes, such as ^ for due date and
 * @ for location.
 *
 * @since 1.0.0
 */
public enum SmartAddPrefixes
{
    /**
     * Prefix for the name attribute.
     *
     * @since 1.0.0
     */
    NAME(""),

    /**
     * Prefix for the URL attribute.
     *
     * @since 1.0.0
     */
    URL(""),

    /**
     * Prefix for the start date attribute.
     *
     * @since 1.0.0
     */
    START("~"),

    /**
     * Prefix for the due date attribute.
     *
     * @since 1.0.0
     */
    DUE("^"),

    /**
     * Prefix for the repeat attribute.
     *
     * @since 1.0.0
     */
    REPEAT("*"),

    /**
     * Prefix for the location attribute.
     *
     * @since 1.0.0
     */
    LOCATION("@"),

    /**
     * Prefix for the priority attribute.
     *
     * @since 1.0.0
     */
    PRIORITY("!"),

    /**
     * Prefix for the list attribute.
     *
     * @since 1.0.0
     */
    LIST("#"),

    /**
     * Prefix for the tag attribute.
     *
     * @since 1.0.0
     */
    TAG("#"),

    /**
     * Prefix for the time estimate attribute.
     *
     * @since 1.0.0
     */
    TIME_ESTIMATE("="),

    /**
     * Prefix for the comments attribute.
     *
     * @since 1.0.0
     */
    COMMENTS("//");

    private final String prefix;

    /**
     * Creates a new smart add prefix.
     *
     * @param prefix the prefix as a string
     *
     * @since 1.0.0
     */
    SmartAddPrefixes(final @NonNls String prefix)
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return this.prefix;
    }

    @Override
    public String toString()
    {
        return "SmartAddPrefixes{" +
                "prefix='" + this.prefix + '\'' +
                '}';
    }
}
