package nu.olivertwistor.todolisttools.rtmapi;

import ch.rfin.util.Pair;
import nu.olivertwistor.todolisttools.util.Config;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a specialized version of {@link Request} for requests to Remember
 * The Milk's authentication service. See the base class for further
 * information.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class AuthRequest extends Request
{
    private static final String endpoint_auth =
            "https://www.rememberthemilk.com/services/auth/";
    private static final String param_permissions = "perms";

    /**
     * Creates an authentication request.
     *
     * @param config      Config object for access to API key etc.
     * @param permissions which permissions this app should get
     * @param frob        FROB used for this authentication
     * @param parameters  a sorted map of additional parameters
     *
     * @since 0.1.0
     */
    public AuthRequest(final Config config,
                       final String permissions,
                       final String frob,
                       final List<Pair<String, String>> parameters)
    {
        super(config, parameters);
        this.parameters.add(Pair.of(PARAM_API_KEY, config.getApiKey()));
        this.parameters.add(Pair.of(param_permissions, permissions));
        this.parameters.add(Pair.of(Request.PARAM_FROB, frob));
    }

    /**
     * Creates an authentication request.
     *
     * @param config      Config object for access to API key etc.
     * @param permissions which permissions this app should get
     * @param frob        FROB used for this authentication
     *
     * @since 0.1.0
     */
    public AuthRequest(final Config config,
                       final String permissions,
                       final String frob)
    {
        this(config, permissions, frob, new LinkedList<>());
    }

    /**
     * Creates a URI object based on the authentication endpoint, added
     * parameters and a hashed API signature parameter.
     *
     * @return URI object needed for making the request.
     *
     * @throws URISyntaxException       if the resulting URI is malformed.
     * @throws NoSuchAlgorithmException if the hashing algorith used by
     *                                  {@link #hash(String)} doesn't exist
     *
     * @since 0.1.0
     */
    @Override
    public URI toUri() throws URISyntaxException, NoSuchAlgorithmException
    {
        final URIBuilder builder = new URIBuilder(endpoint_auth);
        this.parameters.forEach((Pair<String, String> item) ->
                builder.addParameter(item._1, item._2));
        builder.addParameter(param_api_signature, this.generateSignature());

        return builder.build();
    }

    @Override
    public String toString()
    {
        return "AuthRequest{super=" + super.toString() + "}";
    }
}
