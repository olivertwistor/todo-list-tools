package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.rtmapi.AuthResponse;
import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class CheckToken
{
    @NonNls
    private static final String method_check_token = "rtm.auth.checkToken";

    @NonNls
    private static final String param_auth_token = "auth_token";

    private final Request request;
    private final AuthResponse response;

    public CheckToken(final Config config, final String token)
            throws DocumentException, NoSuchAlgorithmException, IOException
    {
        this.request = new RestRequest(config, method_check_token);
        this.request.addParameter(Request.PARAM_API_KEY, config.getApiKey());
        this.request.addParameter(param_auth_token, token);

        this.response = new AuthResponse(this.request);
    }

    public AuthResponse getResponse()
    {
        return this.response;
    }
}
