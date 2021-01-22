package nu.olivertwistor.todolisttools.rtmapi.auth;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.rest.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;

/**
 * This class handles checking the validity of an authentication token. Also,
 * handles the response from Remember The Milk.
 *
 * @since 1.0.0
 */
public final class CheckToken
{
    private static final @NonNls Logger LOG = LogManager.getLogger(
            CheckToken.class);

    @NonNls
    private static final String METHOD_CHECK_TOKEN = "rtm.auth.checkToken";

    private final AuthResponse response;

    /**
     * Creates a fully formed REST request for checking the validity of the
     * provided authentication token, sends that request to Remember The Milk
     * and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     * @param token  the authentication token to verify
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 1.0.0
     */
    public CheckToken(final Config config, final String token)
            throws IOException
    {
        LOG.trace("Entering CheckToken(Config, String)...");

        final String apiKey = config.getApiKey();

        final Request request = new RestRequest(
                config, CheckToken.METHOD_CHECK_TOKEN);
        request.addParameter(Request.PARAM_API_KEY, apiKey);
        request.addParameter(Request.PARAM_AUTH_TOKEN, token);

        this.response = AuthResponse.createAuthResponse(request);
    }

    public boolean isResponseSuccess()
    {
        LOG.trace("Entering isResponseSuccess()...");

        return this.response.isResponseSuccess();
    }

    boolean isResponseFailure()
    {
        LOG.trace("Entering isResponseFailure()...");

        return this.response.isResponseFailure();
    }

    @Override
    public String toString()
    {
        return "CheckToken{" +
                "response=" + this.response +
                '}';
    }
}
