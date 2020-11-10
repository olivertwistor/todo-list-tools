package nu.olivertwistor.todolisttools.rtmapi.requests;

import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.Constants;

/**
 * GetFrob REST request is used for the authentication process. See
 * https://www.rememberthemilk.com/services/api/methods/rtm.auth.getFrob.rtm
 *
 * See {@link RestRequest} for more information about this class.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class GetFrob extends RestRequest
{
    /**
     * Creates a REST request object specific for the GetFrob API method.
     *
     * @param config Config object for access to API key etc.
     *
     * @since 0.1.0
     */
    public GetFrob(final Config config)
    {
        super(config, Constants.GET_FROB_METHOD);

        this.addParameter(Constants.API_KEY_PARAM, config.getApiKey());
    }
}
