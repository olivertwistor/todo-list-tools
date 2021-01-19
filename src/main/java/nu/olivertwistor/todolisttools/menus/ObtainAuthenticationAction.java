package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.java.tui.Terminal;
import nu.olivertwistor.todolisttools.util.Session;
import nu.olivertwistor.todolisttools.rtmapi.auth.CheckToken;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

/**
 * Asks the user to go to the provided URL to give this application the
 * required permissions. When that is done, an authentication token is
 * retrieved from the Remember The Milk service and it is stored in the config
 * file for this application.
 *
 * Before all of this, the existence and validity of an authentication token is
 * determined. If there is an existing and valid authentication token, no
 * further authentication will be done.
 *
 * @since 1.0.0
 */
public final class ObtainAuthenticationAction implements MenuAction
{
    private static final String VAL_WRITE_PERMISSIONS = "write";

    @Override
    public boolean execute(final Config config, final Session session)
    {
        ObtainAuthenticationAction.checkExistingToken(config);

        // We have no valid authentication token, so let's proceed with
        // obtaining one.
        System.out.println(String.join(System.lineSeparator(),
                "In order to use this program, first you have to go to the ",
                "Remember The Milk website and give it this program ",
                "permission to access your account. Please enter the ",
                "following URL in a web browser and follow the instructions. ",
                "Afterwards, return to this program."));
        System.out.println();

        final Authentication authentication = new Authentication();
        final URL authUrl = authentication.generateAuthRequest(
                config, ObtainAuthenticationAction.VAL_WRITE_PERMISSIONS);
        final String urlString = authUrl.toExternalForm();
        System.out.println(urlString);

        Terminal.readString("Please confirm that you have visited the URL (" +
                "any character will do): ");

        // Now when we have obtained authentication and the user have confirmed
        // that, we can retrieve the token we will use for any subsequent calls
        // to the API.
        final String token = authentication.obtainToken(config);

        // Store the retrieved token to the config file.
        config.setToken(token);
        System.out.println("Obtained an authentication token.");

        return false;
    }

    /**
     * Determines whether there is an existing authentication token and whether
     * it is valid.
     *
     * @return True if the authentication token exists and is valid; false
     *         otherwise.
     *
     * @since 1.0.0
     */
    private static boolean checkExistingToken(final Config config)
    {
        final String existingToken = config.getToken();
        final CheckToken checkToken = new CheckToken(config, existingToken);

        if (checkToken.isResponseSuccess())
        {
            System.out.println("You have already authenticated this " +
                    "application.");
            return true;
        }

        return false;
    }
}
