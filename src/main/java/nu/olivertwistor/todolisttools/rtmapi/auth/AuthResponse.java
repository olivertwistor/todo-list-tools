package nu.olivertwistor.todolisttools.rtmapi.auth;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Element;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * This is a specialized version of {@link Response} for responses from
 * Remember The Milk's authentication service. See the base class for further
 * information.
 *
 * @since  1.0.0
 */
final class AuthResponse extends Response
{
    private static final @NonNls Logger LOG = LogManager.getLogger(
            AuthResponse.class);

    @NonNls
    private static final String TAG_AUTH = "auth";

    @NonNls
    private static final String TAG_TOKEN = "token";

    /**
     * Creates an authentication response based off of a request. See the
     * constructor in the base class for more information.
     *
     * @param contentStream an InputStream with the XML response to parse
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 1.0.0
     */
    private AuthResponse(final InputStream contentStream) throws IOException
    {
        super(contentStream);

        LOG.trace("Entering AuthResponse(InputStream)...");
    }

    /**
     * Creates an authentication response object based off of a REST request.
     * The XML response is parsed into a {@link Element} tree, from where the
     * caller may retrieve the elements they wish.
     *
     * @param request the {@link Request}, which XML response to parse
     *
     * @return An authentication response object.
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 1.0.0
     */
    static AuthResponse createAuthResponse(final Request request)
            throws IOException
    {
        LOG.trace("Entering createAuthResponse(Request)...");

        final URL url = request.toUrl();

        // Make an HTTP request to get the response.
        final URLConnection connection = url.openConnection();
        final InputStream contentStream = connection.getInputStream();

        return new AuthResponse(contentStream);
    }

    /**
     * Gets the token from the REST response.
     *
     * @return The token as a string.
     *
     * @since 1.0.0
     */
    public String getToken()
    {
        LOG.trace("Entering getToken()...");

        final Element tokenElement = this.getElement(
                AuthResponse.TAG_AUTH, AuthResponse.TAG_TOKEN);

        final String token = tokenElement.getText();
        LOG.debug("Found token: {}", token);
        return token;
    }
}
