package nu.olivertwistor.todolisttools.rtmapi.rest;

import ch.rfin.util.Pair;
import nu.olivertwistor.todolisttools.rtmapi.Request;
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
 * @since 1.0.0
 */
public class RestRequest extends Request
{
    private static final String ENDPOINT_REST =
            "https://api.rememberthemilk.com/services/rest/";
    private static final String PARAM_METHOD = "method";

    /**
     * Creates a REST request.
     *
     * @param config     Config object for access to API key etc.
     * @param methodName name of the method to call
     * @param parameters a list of additional parameters
     *
     * @since 1.0.0
     */
    private RestRequest(final Config config,
                        final String methodName,
                        final List<Pair<String, String>> parameters)
    {
        super(config, parameters);
        this.parameters.add(Pair.of(RestRequest.PARAM_METHOD, methodName));
    }

    /**
     * Creates a REST request.
     *
     * @param config     Config object for access to API key etc.
     * @param methodName name of the method to call
     *
     * @since 1.0.0
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
     * @since 1.0.0
     */
    @Override
    public final URI toUri()
    {
        final URIBuilder builder = new URIBuilder(RestRequest.ENDPOINT_REST);
        this.parameters.forEach((Pair<String, String> item) ->
                builder.addParameter(item._1, item._2));
        builder.addParameter(Request.PARAM_API_SIGNATURE,
                this.generateSignature());

        return builder.build();
    }
}
