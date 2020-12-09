package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.rtmapi.AuthResponse;
import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

/**
 * This class handles checking the validity of an authentication token. Also,
 * handles the response from Remember The Milk.
 *
 * @since 0.1.0
 */
public class CheckToken
{
    @NonNls
    private static final String method_check_token = "rtm.auth.checkToken";

    private final Request request;
    private final AuthResponse response;

    /**
     * Creates a fully formed REST request for checking the validity of the
     * provided authentication token, sends that request to Remember The Milk
     * and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     * @param token  the authentication token to verify
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    public CheckToken(final Config config, final String token)
            throws DocumentException, NoSuchAlgorithmException,
            MalformedURLException, IOException
    {
        this.request = new RestRequest(config, method_check_token);
        this.request.addParameter(Request.PARAM_API_KEY, config.getApiKey());
        this.request.addParameter(Request.PARAM_AUTH_TOKEN, token);

        this.response = AuthResponse.createAuthResponse(this.request);
    }

    public AuthResponse getResponse()
    {
        return this.response;
    }

    @Override
    public String toString()
    {
        return "CheckToken{super=" + super.toString() + ", request=" +
                this.request + ", response=" + this.response + "}";
    }
}
