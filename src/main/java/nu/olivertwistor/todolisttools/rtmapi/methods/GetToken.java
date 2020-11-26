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
import java.util.NoSuchElementException;

/**
 * This class handles the generation of a request for an authentication token
 * and also handles the response from Remember The Milk.
 *  *
 *  * The caller may then retrieve relevant parts from the response with the
 *  * various getters that exist in this class.
 *
 * @since 0.1.0
 */
public class GetToken
{
    @NonNls
    private static final String method_get_token = "rtm.auth.getToken";

    private final Request request;
    private final AuthResponse response;

    /**
     * Creates a fully formed REST request for getting an authentication token,
     * sends that request to Remember The Milk and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     *
     * @throws DocumentException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws MalformedURLException
     */
    @SuppressWarnings("JavaDoc")
    public GetToken(final Config config, final String frob)
            throws DocumentException, NoSuchAlgorithmException, IOException,
            MalformedURLException
    {
        this.request = new RestRequest(config, method_get_token);
        this.request.addParameter(Request.PARAM_API_KEY, config.getApiKey());
        this.request.addParameter(Request.PARAM_FROB, frob);

        this.response = new AuthResponse(this.request);
    }

    public AuthResponse getResponse()
    {
        return this.response;
    }

    @Override
    public String toString()
    {
        return "GetToken{" +
                "request=" + this.request + ", " +
                "response=" + this.response +
                "}";
    }
}
