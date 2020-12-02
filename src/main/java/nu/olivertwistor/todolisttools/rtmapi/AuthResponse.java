package nu.olivertwistor.todolisttools.rtmapi;

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

    public AuthResponse(final Request request) throws MalformedURLException,
            NoSuchAlgorithmException, IOException, DocumentException
    {
        super(request);
    }

    public String getToken() throws NoSuchElementException
    {
        final Element tokenElement = this.getElement(tag_token, tag_auth);

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
