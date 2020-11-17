package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.rtmapi.requests.AuthRequest;
import nu.olivertwistor.todolisttools.rtmapi.requests.GetFrobRequest;
import nu.olivertwistor.todolisttools.rtmapi.requests.GetTokenRequest;
import nu.olivertwistor.todolisttools.rtmapi.responses.GetTokenResponse;
import nu.olivertwistor.todolisttools.rtmapi.responses.GetFrobResponse;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

/**
 * This class handles authentication to the Remember The Milk service. The way
 * to use this class is to first call
 * {@link #generateAuthRequest(Config, String)}. That method returns a URL the
 * user should go to and give this application necessary permissions to access
 * the user's RTM account. When that is done, {@link #obtainToken(Config)} may
 * be called to obtain a token, with which all subsequent API calls to the RTM
 * service will be made.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class Authentication
{
    private AuthRequest authRequest;
    private String frob;

    /**
     * Generates a URL with which the user may give this application the
     * necessary permissions to access the user's Remember The Milk account.
     *
     * @param config     Config object for access to the API key etc.
     * @param permission which permission level to ask for
     *
     * @return The URL the user need to visit to give this application the
     *         necessary permissions.
     *
     * @throws NoSuchElementException
     * @throws DocumentException
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     * @throws IOException
     */
    @SuppressWarnings("JavaDoc")
    public URL generateAuthRequest(final Config config,
                                   final String permission)
            throws NoSuchElementException, DocumentException,
            NoSuchAlgorithmException, MalformedURLException, IOException
    {
        // First, retrieve a FROB.
        final GetFrobRequest frobRequest = new GetFrobRequest(config);
        final GetFrobResponse frobResponse = new GetFrobResponse(frobRequest);
        this.frob = frobResponse.getFrob();

        // Then, construct an AuthRequest object and store it in this class.
        // Return the URL.
        this.authRequest = new AuthRequest(config, permission, this.frob);
        return this.authRequest.toUrl();
    }

    /**
     * If {@link #generateAuthRequest(Config, String)} has been called
     * beforehand, an authentication token is retrieved from Remember the Milk
     * for use with all subsequent calls to the RTM API.
     *
     * @param config Config object for access to the API key etc.
     *
     * @return The authentication token.
     *
     * @throws UnsupportedOperationException if {@link #generateAuthRequest(Config, String)}
     *                                       hasn't been called prior to
     *                                       calling this method
     * @throws NoSuchElementException
     * @throws DocumentException
     * @throws NoSuchAlgorithmException
     * @throws MalformedURLException
     * @throws IOException
     */
    @SuppressWarnings("JavaDoc")
    public String obtainToken(final Config config)
            throws UnsupportedOperationException, NoSuchElementException,
            DocumentException, NoSuchAlgorithmException, MalformedURLException,
            IOException
    {
        if (this.authRequest == null)
        {
            throw new UnsupportedOperationException("Authentication has not " +
                    "yet been obtained. Please call #generateAuthRequest() " +
                    "before calling this method.");
        }

        final GetTokenRequest getTokenRequest =
                new GetTokenRequest(config, this.frob);
        final GetTokenResponse getTokenResponse =
                new GetTokenResponse(getTokenRequest);

        return getTokenResponse.getToken();
    }

    @Override
    public String toString()
    {
        return "Authentication{authRequest=" + this.authRequest +
                ", frob=" + this.frob + "}";
    }
}
