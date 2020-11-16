package nu.olivertwistor.todolisttools.rtmapi.requests;

import nu.olivertwistor.todolisttools.util.Config;

/**
 * This is a specialized version of {@link RestRequest} for requests to
 * Remember The Milk's API method {@link #METHOD_GET_FROB}. See the base class
 * for further information.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class GetFrobRequest extends RestRequest
{
    /**
     * Name of the method for getting a FROB.
     *
     * @since 0.1.0
     */
    public static final String METHOD_GET_FROB = "rtm.auth.getFrob";

    /**
     * Creates a request for getting a FROB.
     *
     * @param config Config object for access to API key
     *
     * @since 0.1.0
     */
    public GetFrobRequest(final Config config)
    {
        super(config, METHOD_GET_FROB);

        this.addParameter(PARAM_API_KEY, config.getApiKey());
    }
}
