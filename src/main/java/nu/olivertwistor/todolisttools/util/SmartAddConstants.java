package nu.olivertwistor.todolisttools.util;

import org.jetbrains.annotations.NonNls;

/**
 * This class holds the various smart add prefixes, such as ^ for due date and
 * @ for location.
 *
 * @since 0.1.0
 */
public final class SmartAddConstants
{
    /**
     * Prefix for the name attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_NAME = "";

    /**
     * Prefix for the URL attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_URL = "";

    /**
     * Prefix for the start date attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_START = "~";

    /**
     * Prefix for the due date attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_DUE = "^";

    /**
     * Prefix for the repeat attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_REPEAT = "*";

    /**
     * Prefix for the location attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_LOCATION = "@";

    /**
     * Prefix for the priority attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_PRIORITY = "!";

    /**
     * Prefix for the list attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_LIST = "#";

    /**
     * Prefix for the tag attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_TAG = "#";

    /**
     * Prefix for the time estimate attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_TIME_ESTIMATE = "=";

    /**
     * Prefix for the comments attribute.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PREFIX_COMMENTS = "//";

    /**
     * Empty private constructor to prevent instantiation.
     *
     * @since 0.1.0
     */
    private SmartAddConstants() { }
}
