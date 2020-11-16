package nu.olivertwistor.todolisttools.rtmapi.requests;

import nu.olivertwistor.todolisttools.util.Config;

public final class GetFrobRequest extends RestRequest
{
    public static final String METHOD_GET_FROB = "rtm.auth.getFrob";

    public GetFrobRequest(final Config config)
    {
        super(config, METHOD_GET_FROB);

        this.addParameter(PARAM_API_KEY, config.getApiKey());
    }
}
