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
 * The Milk's REST service. See the base class for further information.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class RestRequest extends Request
{
    /**
     * Creates a REST request.
     *
     * @param config     Config object for access to API key etc.
     * @param methodName name of the method to call
     * @param parameters a sorted map of additional parameters
     *
     * @since 0.1.0
     */
    public RestRequest(final Config config,
                       final String methodName,
                       final SortedMap<String, String> parameters)
    {
        super(config, parameters);
        this.parameters.put(Constants.METHOD_PARAM, methodName);
    }

    /**
     * Creates a REST request.
     *
     * @param config     Config object for access to API key etc.
     * @param methodName name of the method to call
     *
     * @since 0.1.0
     */
    public RestRequest(final Config config, final String methodName)
    {
        this(config, methodName, new TreeMap<>());
    }

    /**
     * Creates a URI object based on the REST endpoint, added parameters and a
     * hashed api_sig parameter using {@link #hash(String)}.
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
        final URIBuilder builder = new URIBuilder(Constants.REST_ENDPOINT);
        this.parameters.forEach(
                (key, value) -> builder.addParameter(key, value));
        builder.addParameter(Constants.API_SIG_PARAM, this.generateSignature());

        return builder.build();
    }

    @Override
    public String toString()
    {
        return "RestRequest{super=" + super.toString() + "}";
    }
}
