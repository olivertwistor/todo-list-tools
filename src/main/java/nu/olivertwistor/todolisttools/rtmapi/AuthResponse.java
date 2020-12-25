package nu.olivertwistor.todolisttools.rtmapi;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

/**
 * This is a specialized version of {@link Response} for responses from
 * Remember The Milk's authentication service. See the base class for further
 * information.
 *
 * @since  0.1.0
 */
@SuppressWarnings({"MethodWithTooExceptionsDeclared", "ClassWithoutLogger", "PublicMethodWithoutLogging"})
public final class AuthResponse extends Response
{
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
     * @throws MalformedURLException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws DocumentException
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    private AuthResponse(final InputStream contentStream)
            throws DocumentException
    {
        super(contentStream);
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
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    public static AuthResponse createAuthResponse(final Request request)
            throws MalformedURLException, NoSuchAlgorithmException,
            IOException, DocumentException
    {
        // Make an HTTP request to get the response.
        final URL url = request.toUrl();
        final URLConnection connection = url.openConnection();
        final InputStream contentStream = connection.getInputStream();

        return new AuthResponse(contentStream);
    }

    /**
     * Gets the token from the REST response.
     *
     * @return The token as a string.
     *
     * @throws NoSuchElementException if a token couldn't be found in the
     *                                response
     *
     * @since 0.1.0
     */
    public String getToken()
    {
        final Element tokenElement = this.getElement(
                AuthResponse.TAG_AUTH, AuthResponse.TAG_TOKEN);

        return tokenElement.getText();
    }

}
