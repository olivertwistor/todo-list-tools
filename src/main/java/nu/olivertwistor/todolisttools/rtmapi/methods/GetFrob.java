package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

/**
 * This class handles the generation of a request for a FROB string and also
 * handles the response from Remember The Milk.
 *
 * @since 0.1.0
 */
public class GetFrob
{
    @NonNls
    private static final String method_get_frob = "rtm.auth.getFrob";

    @NonNls
    private static final String tag_frob = "frob";

    private final Request request;
    private final Response response;

    /**
     * Creates a fully formed REST request for getting a FROB string, sends
     * that request to Remember The Milk and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     *
     * @throws DocumentException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws MalformedURLException
     */
    @SuppressWarnings("JavaDoc")
    public GetFrob(final Config config)
            throws DocumentException, NoSuchAlgorithmException, IOException,
            MalformedURLException
    {
        this.request = new RestRequest(config, method_get_frob);
        this.request.addParameter(Request.PARAM_API_KEY, config.getApiKey());

        this.response = new Response(this.request);
    }

    /**
     * Gets the FROB string from the response.
     *
     * @return The FROB string.
     *
     * @throws NoSuchElementException if the FROB couldn't be found in the
     *                                response.
     *
     * @since 0.1.0
     */
    public String getFrob() throws NoSuchElementException
    {
        final Element frobElement = this.response.getElement(tag_frob);

        return frobElement.getText();
    }

    @Override
    public String toString()
    {
        return "GetFrob{request=" + this.request + ", response=" +
                this.response + "}";
    }
}
