package nu.olivertwistor.todolisttools;

import nu.olivertwistor.todolisttools.rtmapi.AuthRequest;
import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.rtmapi.RestResponse;
import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.Constants;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public final class Authentication
{
    public static void obtainAuthentication(final Config config,
                                            final String permission)
            throws DocumentException, NoSuchAlgorithmException, IOException
    {
        // https://www.rememberthemilk.com/services/api/authentication.rtm

        // First, retrieve a FROB.
        final RestRequest frobRequest =
                new RestRequest(config, Constants.GET_FROB_METHOD);
        frobRequest.addParameter(Constants.API_KEY_PARAM, config.getApiKey());
        final RestResponse frobResponse = new RestResponse(frobRequest);
        final String frobString =
                frobResponse.getElement(Constants.FROB_ATTRIB).getText();

        // Then, construct a URL for the auth request an read the response.
        final AuthRequest authRequest =
                new AuthRequest(config, permission, frobString);
    }

    private Authentication() { }
}
