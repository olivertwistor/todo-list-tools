package nu.olivertwistor.todolisttools.rtmapi.requests;

import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;

public final class GetFrob extends RestRequest
{
    public GetFrob(final Config config)
    {
        super(config);

        this.addParameter(Constants.API_KEY_PARAM, config.getApiKey());
    }
}
