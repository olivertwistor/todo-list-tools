package nu.olivertwistor.todolisttools.rtmapi.auth;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.rest.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

/**
 * This class handles the generation of a request for an authentication token
 * and also handles the response from Remember The Milk.
 *
 * @since 1.0.0
 */
public final class GetToken
{
    @NonNls
    private static final String METHOD_GET_TOKEN = "rtm.auth.getToken";

    private final Request request;
    private final AuthResponse response;

    /**
     * Creates a fully formed REST request for getting an authentication token,
     * sends that request to Remember The Milk and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     *
     * @since 0.1.0
     */
    public GetToken(final Config config, final String frob)
    {
        final String apiKey = config.getApiKey();

        this.request = new RestRequest(config, GetToken.METHOD_GET_TOKEN);
        this.request.addParameter(Request.PARAM_API_KEY, apiKey);
        this.request.addParameter(Request.PARAM_FROB, frob);

        this.response = AuthResponse.createAuthResponse(this.request);
    }

    public String getToken()
    {
        return this.response.getToken();
    }

    public AuthResponse getResponse()
    {
        return this.response;
    }
}
