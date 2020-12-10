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
 * This class handles creating a timeline. A timeline is a set point in time
 * after which actions can be undone. Also, handles the response from Remember
 * The Milk.
 *
 * @since 0.1.0
 */
public class CreateTimeline
{
    @NonNls
    private static final String method_create_timeline = "rtm.timelines.create";

    @NonNls
    private static final String tag_timeline = "timeline";

    private final Request request;
    private final Response response;

    /**
     * Creates a fully formed REST request for creating a timeline, sends that
     * request to Remember The Milk and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    public CreateTimeline(final Config config)
            throws DocumentException, NoSuchAlgorithmException, IOException,
            MalformedURLException
    {
        this.request = new RestRequest(config, method_create_timeline);
        this.request.addParameter(Request.PARAM_API_KEY, config.getApiKey());
        this.request.addParameter(Request.PARAM_AUTH_TOKEN, config.getToken());

        this.response = Response.createResponse(this.request);
    }

    public String getTimeline() throws NoSuchElementException
    {
        final Element timelineElement = this.response.getElement(tag_timeline);

        return timelineElement.getText();
    }

    public Response getResponse()
    {
        return this.response;
    }

    @Override
    public String toString()
    {
        return "CreateTimeline{" +
                "request=" + this.request +
                ", response=" + this.response +
                '}';
    }
}
