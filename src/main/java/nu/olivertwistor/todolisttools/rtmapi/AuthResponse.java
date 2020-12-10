package nu.olivertwistor.todolisttools.rtmapi;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This is a specialized version of {@link Response} for responses from
 * Remember The Milk's authentication service. See the base class for further
 * information.
 *
 * @since  0.1.0
 */
public class AuthResponse extends Response
{
    @NonNls
    private static final String attrib_user_id = "id";

    @NonNls
    private static final String attrib_username = "username";

    @NonNls
    private static final String attrib_fullname = "fullname";

    @NonNls
    private static final String tag_auth = "auth";

    @NonNls
    private static final String tag_permissions = "perms";

    @NonNls
    private static final String tag_token = "token";

    @NonNls
    private static final String tag_user = "user";

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
    public AuthResponse(final InputStream contentStream)
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
        final URLConnection connection = request.toUrl().openConnection();
        final InputStream contentStream = connection.getInputStream();

        return new AuthResponse(contentStream);
    }

    public String getToken() throws NoSuchElementException
    {
        final Element tokenElement = this.getElement(tag_auth, tag_token);

        return tokenElement.getText();
    }

    public String getPermissions() throws NoSuchElementException
    {
        final Element permElement = this.getElement(tag_permissions, tag_auth);

        return permElement.getText();
    }

    /**
     * Gets the Remember The Milk user info: user id, username and full name.
     *
     * @return The Remember The Milk user info as a map with the following
     * keys: {@link #attrib_user_id}, {@link #attrib_username} and
     * {@link #attrib_fullname}.
     *
     * @throws NoSuchElementException if the user couldn't be found in the
     *                                response.
     *
     * @since 0.1.0
     */
    public Map<String, String> getUser() throws NoSuchElementException
    {
        final Map<String, String> userData = new HashMap<>();

        final Element user = this.getElement(tag_user, tag_auth);
        final Attribute userId = user.attribute(attrib_user_id);
        final Attribute username = user.attribute(attrib_username);
        final Attribute fullname = user.attribute(attrib_fullname);

        userData.put(attrib_user_id, userId.getValue());
        userData.put(attrib_username, username.getValue());
        userData.put(attrib_fullname, fullname.getValue());

        return userData;
    }
}
