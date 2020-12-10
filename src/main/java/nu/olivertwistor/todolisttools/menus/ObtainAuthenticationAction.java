package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.java.tui.Terminal;
import nu.olivertwistor.todolisttools.Authentication;
import nu.olivertwistor.todolisttools.rtmapi.methods.CheckToken;
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
 * @since 0.1.0
 */
public class ObtainAuthenticationAction implements MenuAction
{
    private static final String val_read_permissions = "read";
    private static final String val_write_permissions = "write";

    private final Config config;

    /**
     * Creates a new ObtainAuthenticationAction.
     *
     * @param config Config object for access to the authentication token
     *
     * @since 0.1.0
     */
    public ObtainAuthenticationAction(final Config config)
    {
        this.config = config;
    }

    @Override
    public void execute()
    {
        try
        {
            final boolean existingToken = this.checkExistingToken();
        }
        catch (final DocumentException | NoSuchAlgorithmException |
                IOException e)
        {
            System.out.println("Failed to check for existing token.");
            return;
        }

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
        try
        {
            final URL authUrl = authentication.generateAuthRequest(
                    this.config, val_write_permissions);
            System.out.println(authUrl.toExternalForm());
        }
        catch (final NoSuchElementException | DocumentException |
                NoSuchAlgorithmException | IOException e)
        {
            System.out.println("Failed to generate an authentication request");
            return;
        }

        System.out.println("When you are done giving this program the " +
                "required permissions, press ENTER.");
        try
        {
            Terminal.readString();
        }
        catch (final IOException e)
        {
            System.err.println("Failed to read user input.");
            return;
        }

        // Now when we have obtained authentication and the user have confirmed
        // that, we can retrieve the token we will use for any subsequent calls
        // to the API.
        final String token;
        try
        {
            token = authentication.obtainToken(this.config);

            // Store the retrieved token to the config file.
            this.config.setToken(token);
        }
        catch (final DocumentException | NoSuchAlgorithmException |
                IOException e)
        {
            System.out.println("Failed to either obtain an authentication " +
                    "token or store it in the config file.");
            return;
        }
    }

    /**
     * Determines whether there is an existing authentication token and whether
     * it is valid.
     *
     * @return True if the authentication token exists and is valid; false
     *         otherwise.
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    private boolean checkExistingToken()
            throws DocumentException, NoSuchAlgorithmException,
            MalformedURLException, IOException
    {
        final String existingToken = this.config.getToken();
        final CheckToken checkToken =
                new CheckToken(this.config, existingToken);

        if (checkToken.getResponse().isResponseSuccess())
        {
            System.out.println("You already have authenticated this " +
                    "application.");
            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "ObtainAuthenticationAction{config=" + this.config + "}";
    }
}
