package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.rtmapi.requests.AuthRequest;
import nu.olivertwistor.todolisttools.rtmapi.requests.GetFrobRequest;
import nu.olivertwistor.todolisttools.rtmapi.requests.GetTokenRequest;
import nu.olivertwistor.todolisttools.rtmapi.responses.GetTokenResponse;
import nu.olivertwistor.todolisttools.rtmapi.responses.GetFrobResponse;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

public final class Authentication
{
    public static final String VAL_READ_PERMISSIONS = "read";

    private AuthRequest authRequest;
    private String frob;

    public URL generateAuthRequest(final Config config,
                                   final String permission)
            throws NoSuchElementException, DocumentException,
            NoSuchAlgorithmException, IOException
    {
        // https://www.rememberthemilk.com/services/api/authentication.rtm

        // First, retrieve a FROB.
        final GetFrobRequest frobRequest = new GetFrobRequest(config);
        final GetFrobResponse frobResponse = new GetFrobResponse(frobRequest);
        this.frob = frobResponse.getFrob();

        // Then, construct an AuthRequest object and store it in this class.
        // Return the URL.
        this.authRequest = new AuthRequest(config, permission, this.frob);
        return this.authRequest.toUrl();
    }

    public String obtainToken(final Config config)
            throws UnsupportedOperationException, NoSuchElementException,
            DocumentException, NoSuchAlgorithmException, IOException
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
}
