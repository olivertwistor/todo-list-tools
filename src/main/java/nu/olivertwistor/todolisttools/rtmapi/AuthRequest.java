package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.Constants;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;

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
    private final String frob;

    /**
     * Creates an authentication request.
     *
     * @param config      Config object for access to API key etc.
     * @param permissions which permissions this app should get; choose between
     *                    "read", "write" and "delete"
     * @param frob        FROB used for this authentication
     * @param parameters  a sorted map of additional parameters
     *
     * @since 0.1.0
     */
    public AuthRequest(final Config config,
                       final String permissions,
                       final String frob,
                       final SortedMap<String, String> parameters)
    {
        super(config, parameters);
        this.frob = frob;
        this.parameters.put(Constants.PERMISSIONS_PARAM, permissions);
    }

    /**
     * Creates an authentication request.
     *
     * @param config      Config object for access to API key etc.
     * @param permissions which permissions this app should get; choose between
     *                    "read", "write" and "delete"
     * @param frob        FROB used for this authentication
     *
     * @since 0.1.0
     */
    public AuthRequest(final Config config,
                       final String permissions,
                       final String frob)
    {
        this(config, permissions, frob, new TreeMap<>());
    }

    /**
     * Creates a URI object based on the authentication endpoint, added
     * parameters and a hashed api_sig parameter using {@link #hash(String)}.
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
        final URIBuilder builder = new URIBuilder(Constants.AUTH_ENDPOINT);
        this.parameters.forEach(
                (key, value) -> builder.addParameter(key, value));
        builder.addParameter(Constants.FROB_PARAM, this.frob);
        builder.addParameter(Constants.API_SIG_PARAM, this.generateSignature());

        return builder.build();
    }

    @Override
    public String toString()
    {
        return "AuthRequest{super=" + super.toString() + ", " +
                "frob=" + this.frob + "}";
    }
}
