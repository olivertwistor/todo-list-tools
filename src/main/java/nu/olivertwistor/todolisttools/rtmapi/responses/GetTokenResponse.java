package nu.olivertwistor.todolisttools.rtmapi.responses;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.requests.GetTokenRequest;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A GetTokenResponse takes a {@link GetTokenRequest} and reads the XML
 * response, storing the {@link Element root element} for later use.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class GetTokenResponse extends Response
{
    private static final String attrib_user_id = "id";
    private static final String attrib_username = "username";
    private static final String attrib_fullname = "fullname";
    private static final String tag_auth = "auth";
    private static final String tag_permissions = "perms";
    private static final String tag_token = "token";
    private static final String tag_user = "user";

    private final Element authElement;

    /**
     * Creates a GetTokenResponse object based off of a GetTokenRequest. The
     * XML response is parsed into a {@link Element} tree, from where the
     * caller may retrieve the elements they wish.
     *
     * @param request the {@link Request}, which XML response to parse
     *
     * @throws IOException       if there were any problem with reading either
     *                           the request or the response
     * @throws DocumentException if the response couldn't be parsed into XML
     * @throws MalformedURLException
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    public GetTokenResponse(final GetTokenRequest request)
            throws MalformedURLException, NoSuchAlgorithmException,
            IOException, DocumentException, NoSuchElementException
    {
        super(request);

        this.authElement = this.rootElement.element(tag_auth);
        if (this.authElement == null)
        {
            throw new NoSuchElementException("Failed to find the auth tag.");
        }
    }

    /**
     * Gets the authentication token.
     *
     * @return The authentication token.
     *
     * @throws NoSuchElementException if the authentication token couldn't be
     *                                found in the response.
     *
     * @since 0.1.0
     */
    public String getToken() throws NoSuchElementException
    {
        final Element tokenElement = this.authElement.element(tag_token);
        if (tokenElement == null)
        {
            throw new NoSuchElementException("Failed to find the token tag.");
        }

        return tokenElement.getText();
    }

    /**
     * Gets the permissions level.
     *
     * @return The permissions level as a string.
     *
     * @throws NoSuchElementException if the permissions level couldn't be
     *                                found in the response.
     *
     * @since 0.1.0
     */
    public String getPermissions() throws NoSuchElementException
    {
        final Element permissionElement =
                this.authElement.element(tag_permissions);
        if (permissionElement == null)
        {
            throw new NoSuchElementException(
                    "Failed to find the permissions tag.");
        }

        return permissionElement.getText();
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

        final Element user = this.authElement.element(tag_user);
        if (user == null)
        {
            throw new NoSuchElementException("Failed to find the user tag.");
        }

        final Attribute userId = user.attribute(attrib_user_id);
        if (userId == null)
        {
            throw new NoSuchElementException(
                    "Failed to find the user id attribute.");
        }

        final Attribute username = user.attribute(attrib_username);
        if (username == null)
        {
            throw new NoSuchElementException(
                    "Failed to find the username attribute.");
        }

        final Attribute fullname = user.attribute(attrib_fullname);
        if (fullname == null)
        {
            throw new NoSuchElementException(
                    "Failed to find the fullname attribute.");
        }

        userData.put(attrib_user_id, userId.getValue());
        userData.put(attrib_username, username.getValue());
        userData.put(attrib_fullname, fullname.getValue());

        return userData;
    }

    @Override
    public String toString()
    {
        return "GetTokenResponse{authElement=" + this.authElement + "}";
    }
}
