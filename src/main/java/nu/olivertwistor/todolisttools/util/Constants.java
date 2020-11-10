package nu.olivertwistor.todolisttools.util;

import org.jetbrains.annotations.NonNls;

/**
 * Constants to use within this application.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class Constants
{
    /**
     * Endpoint URL to the Remember The Milk API.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String ENDPOINT =
            "https://api.rememberthemilk.com/services/rest/";

    /**
     * URL parameter for method.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String METHOD_PARAM = "method";

    /**
     * GetFrob method name.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String GET_FROB_METHOD = "rtm.auth.getFrob";

    /**
     * URL parameter for API key.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String API_KEY_PARAM = "api_key";

    /**
     * URL parameter for API signature.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String API_SIG_PARAM = "api_sig";

    /**
     * Empty private constructor to prevent instantiation.
     *
     * @since 0.1.0
     */
    private Constants() { }
}
