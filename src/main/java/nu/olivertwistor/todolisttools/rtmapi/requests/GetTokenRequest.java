package nu.olivertwistor.todolisttools.rtmapi.requests;

import nu.olivertwistor.todolisttools.rtmapi.responses.GetFrobResponse;
import nu.olivertwistor.todolisttools.util.Config;

/**
 * This is a specialized version of {@link RestRequest} for requests to
 * Remember The Milk's API method {@link #method_get_token}. See the base class
 * for further information.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class GetTokenRequest extends RestRequest
{
    private static final String method_get_token = "rtm.auth.getToken";

    /**
     * Creates a request for getting an authentication token.
     *
     * @param config Config object for access to API key
     * @param frob   FROB received from a {@link GetFrobResponse} call
     *
     * @since 0.1.0
     */
    public GetTokenRequest(final Config config, final String frob)
    {
        super(config, method_get_token);

        this.addParameter(PARAM_API_KEY, config.getApiKey());
        this.addParameter(param_frob, frob);
    }
}
