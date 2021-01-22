package nu.olivertwistor.todolisttools.rtmapi.auth;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.rest.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;

/**
 * This class handles the generation of a request for an authentication token
 * and also handles the response from Remember The Milk.
 *
 * @since 1.0.0
 */
public final class GetToken
{
    private static final @NonNls Logger LOG = LogManager.getLogger(
            GetToken.class);

    @NonNls
    private static final String METHOD_GET_TOKEN = "rtm.auth.getToken";

    private final AuthResponse response;

    /**
     * Creates a fully formed REST request for getting an authentication token,
     * sends that request to Remember The Milk and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     * @param frob   the FROB necessary to retrieve the authentication token
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 0.1.0
     */
    public GetToken(final Config config, final String frob) throws IOException
    {
        LOG.trace("Entering GetToken(Config, String)...");

        final String apiKey = config.getApiKey();

        final Request request = new RestRequest(
                config, GetToken.METHOD_GET_TOKEN);
        request.addParameter(Request.PARAM_API_KEY, apiKey);
        request.addParameter(Request.PARAM_FROB, frob);

        this.response = AuthResponse.createAuthResponse(request);
    }

    public String getToken()
    {
        LOG.trace("Entering getToken()...");

        return this.response.getToken();
    }

    public AuthResponse getResponse()
    {
        LOG.trace("Entering getResponse()...");

        return this.response;
    }

    @Override
    public String toString()
    {
        return "GetToken{" +
                "response=" + this.response +
                '}';
    }
}
