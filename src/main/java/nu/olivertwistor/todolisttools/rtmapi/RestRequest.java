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
 * The Milk's REST service. See the base class for further information.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class RestRequest extends Request
{
    private static final String endpoint_rest =
            "https://api.rememberthemilk.com/services/rest/";
    private static final String param_method = "method";

    /**
     * Creates a REST request.
     *
     * @param config     Config object for access to API key etc.
     * @param methodName name of the method to call
     * @param parameters a list of additional parameters
     *
     * @since 0.1.0
     */
    public RestRequest(final Config config,
                       final String methodName,
                       final List<Pair<String, String>> parameters)
    {
        super(config, parameters);
        this.parameters.add(Pair.of(param_method, methodName));
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
        this(config, methodName, new LinkedList<>());
    }

    /**
     * Creates a URI object based on the REST endpoint, added parameters and a
     * hashed API signature parameter.
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
        final URIBuilder builder = new URIBuilder(endpoint_rest);
        this.parameters.forEach((Pair<String, String> item) ->
                builder.addParameter(item._1, item._2));
        builder.addParameter(param_api_signature, this.generateSignature());

        return builder.build();
    }

    @Override
    public String toString()
    {
        return "RestRequest{super=" + super.toString() + "}";
    }
}
