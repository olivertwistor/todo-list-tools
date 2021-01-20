package nu.olivertwistor.todolisttools.rtmapi.rest;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.Element;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;

/**
 * This class handles creating a timeline. A timeline is a set point in time
 * after which actions can be undone. Also, handles the response from Remember
 * The Milk.
 *
 * @since 1.0.0
 */
public final class CreateTimeline
{
    @NonNls
    private static final String METHOD_CREATE_TIMELINE = "rtm.timelines.create";

    @NonNls
    private static final String TAG_TIMELINE = "timeline";

    private final Response response;

    /**
     * Creates a fully formed REST request for creating a timeline, sends that
     * request to Remember The Milk and retrieves a response.
     *
     * @param config Config object for access to API key etc.
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 1.0.0
     */
    public CreateTimeline(final Config config) throws IOException
    {
        final String apiKey = config.getApiKey();
        final String token = config.getToken();

        final Request request = new RestRequest(
                config, CreateTimeline.METHOD_CREATE_TIMELINE);
        request.addParameter(Request.PARAM_API_KEY, apiKey);
        request.addParameter(Request.PARAM_AUTH_TOKEN, token);

        this.response = Response.createResponse(request);
    }

    /**
     * Gets the timeline from the REST response.
     *
     * @return The timeline as a string.
     *
     * @since 1.0.0
     */
    public String getTimeline()
    {
        final Element timelineElement = this.response.getElement(
                CreateTimeline.TAG_TIMELINE);

        return timelineElement.getText();
    }

    boolean isResponseSuccess()
    {
        return this.response.isResponseSuccess();
    }

    public Response getResponse()
    {
        return this.response;
    }

    @Override
    public String toString()
    {
        return "CreateTimeline{" +
                "response=" + this.response +
                '}';
    }
}
