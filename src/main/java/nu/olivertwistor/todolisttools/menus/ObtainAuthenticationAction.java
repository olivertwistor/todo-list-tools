package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.java.tui.Terminal;
import nu.olivertwistor.todolisttools.rtmapi.auth.CheckToken;
import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.ErrorMessage;
import nu.olivertwistor.todolisttools.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.net.URL;

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
@SuppressWarnings({"HardCodedStringLiteral", "StringConcatenation"})
public final class ObtainAuthenticationAction implements MenuAction
{
    private static final Logger LOG = LogManager.getLogger(
            ObtainAuthenticationAction.class);

    private static final @NonNls String VAL_WRITE_PERMISSIONS = "write";

    @Override
    public boolean execute(final Config config, final Session session)
    {
        LOG.trace("Entering execute(Config, Session)...");

        try
        {
            checkExistingToken(config);
        }
        catch (final IOException e)
        {
            ErrorMessage.printAndLogError(
                    LOG,
                    ErrorMessage.FAILED_TO_COMMUNICATE_WITH_REMEMBER_THE_MILK,
                    e);
            return false;
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
                    config, VAL_WRITE_PERMISSIONS);
            final String urlString = authUrl.toExternalForm();
            System.out.println(urlString);
        }
        catch (final IOException e)
        {
            final String msg = "Failed to generate an authentication URL.";
            System.out.println(msg);
            LOG.fatal(msg, e);
            return true;
        }

        try
        {
            Terminal.readString("Please confirm that you have visited the " +
                    "URL (any character will do): ");
        }
        catch (final IOException e)
        {
            final ErrorMessage msg = ErrorMessage.FAILED_TO_READ_USER_INPUT;
            System.out.println(msg.getMessage());
            LOG.fatal(msg, e);
            return true;
        }

        try
        {
            // Now when we have obtained authentication and the user have
            // confirmed that, we can retrieve the token we will use for any
            // subsequent calls to the API.
            final String token = authentication.obtainToken(config);
        }
        catch (final IOException e)
        {

        }

        try
        {
            // Store the retrieved token to the config file.
            config.setToken(token);
            System.out.println("Obtained an authentication token.");
        }
        catch (final IOException e)
        {

        }

        return false;
    }

    /**
     * Determines whether there is an existing authentication token and whether
     * it is valid.
     *
     * @param config Config object containing the API key etc.
     *
     * @return True if the authentication token exists and is valid; false
     *         otherwise.
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 1.0.0
     */
    private static boolean checkExistingToken(final Config config)
            throws IOException
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
