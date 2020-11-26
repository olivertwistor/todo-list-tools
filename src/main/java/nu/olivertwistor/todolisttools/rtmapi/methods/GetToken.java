package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
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
    private static final String attrib_user_id = "id";

    @NonNls
    private static final String attrib_username = "username";

    @NonNls
    private static final String attrib_fullname = "fullname";

    @NonNls
    private static final String method_get_token = "rtm.auth.getToken";

    @NonNls
    private static final String tag_auth = "auth";

    @NonNls
    private static final String tag_permissions = "perms";

    @NonNls
    private static final String tag_token = "token";

    @NonNls
    private static final String tag_user = "user";

    private final Request request;
    private final Response response;

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

        this.response = new Response(this.request);
    }

    public String getToken() throws NoSuchElementException
    {
        final Element tokenElement = this.response.getElement(
                tag_token, tag_auth);

        return tokenElement.getText();
    }

    public String getPermissions() throws NoSuchElementException
    {
        final Element permElement = this.response.getElement(
                tag_permissions, tag_auth);

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

        final Element user = this.response.getElement(tag_user, tag_auth);
        final Attribute userId = user.attribute(attrib_user_id);
        final Attribute username = user.attribute(attrib_username);
        final Attribute fullname = user.attribute(attrib_fullname);

        userData.put(attrib_user_id, userId.getValue());
        userData.put(attrib_username, username.getValue());
        userData.put(attrib_fullname, fullname.getValue());

        return userData;
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
