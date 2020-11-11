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
     * Endpoint URL to the Remember The Milk REST API.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String REST_ENDPOINT =
            "https://api.rememberthemilk.com/services/rest/";

    /**
     * Endpoint URL for authentication against Remember The Milk.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String AUTH_ENDPOINT =
            "https://www.rememberthemilk.com/services/auth/";

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
     * URL parameter for API permissions (read, write, delete).
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String PERMISSIONS_PARAM = "perms";

    /**
     * API permission: read. For reading data.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String READ_PERMISSIONS = "read";

    /**
     * API permission: write. For creating and editing. Includes all "read"
     * permissions.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String WRITE_PERMISSIONS = "write";

    /**
     * API permission: delete. For deleting. Includes all "write" permissions.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String DELETE_PERMISSIONS = "delete";

    /**
     * URL parameter for API signature.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String API_SIG_PARAM = "api_sig";

    /**
     * URL parameter for FROB.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String FROB_PARAM = "frob";

    /**
     * XML attribute (without the chevrons) for FROB.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String FROB_ATTRIB = "frob";

    /**
     * Empty private constructor to prevent instantiation.
     *
     * @since 0.1.0
     */
    private Constants() { }
}
