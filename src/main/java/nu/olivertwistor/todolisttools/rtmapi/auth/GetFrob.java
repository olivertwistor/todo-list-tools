package nu.olivertwistor.todolisttools.rtmapi.auth;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.rest.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Element;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;

/**
 * This class handles the generation of a request for a FROB string and also
 * handles the response from Remember The Milk.
 *
 * @since 1.0.0
 */
public final class GetFrob
{
    private static final @NonNls Logger LOG = LogManager.getLogger(
            GetFrob.class);

    @NonNls
    private static final String METHOD_GET_FROB = "rtm.auth.getFrob";

    @NonNls
    private static final String TAG_FROB = "frob";

    private final AuthResponse response;

    /**
     * Creates a fully formed REST request for getting a FROB string, sends
     * that request to Remember The Milk and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 0.1.0
     */
    public GetFrob(final Config config) throws IOException
    {
        LOG.trace("Entering GetFrob(Config)...");

        final String apiKey = config.getApiKey();

        final Request request = new RestRequest(
                config, GetFrob.METHOD_GET_FROB);
        request.addParameter(Request.PARAM_API_KEY, apiKey);

        this.response = AuthResponse.createAuthResponse(request);
    }

    /**
     * Gets the FROB string from the response.
     *
     * @return The FROB string.
     *
     * @since 1.0.0
     */
    public String getFrob()
    {
        LOG.trace("Entering getFrob()...");

        final Element frobElement = this.response.getElement(GetFrob.TAG_FROB);

        final String frob = frobElement.getText();
        LOG.debug("Found FROB: {}", frob);
        return frob;
    }

    @Override
    public String toString()
    {
        return "GetFrob{" +
                "response=" + this.response +
                '}';
    }
}
