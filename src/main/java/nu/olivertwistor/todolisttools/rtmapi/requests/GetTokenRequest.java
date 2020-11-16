package nu.olivertwistor.todolisttools.rtmapi.requests;
import nu.olivertwistor.todolisttools.util.Config;

public final class GetTokenRequest extends RestRequest
{
    private static final String method_get_token = "rtm.auth.getToken";

    @SuppressWarnings("ParameterNameDiffersFromOverriddenParameter")
    public GetTokenRequest(final Config config, final String frob)
    {
        super(config, method_get_token);

        this.addParameter(PARAM_API_KEY, config.getApiKey());
        this.addParameter(PARAM_FROB, frob);
    }
}
