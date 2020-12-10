package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.Session;
import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

/**
 * This class handles adding tasks to Remember The Milk.
 *
 * @since 0.1.0
 */
public class AddTask
{
    @NonNls
    private static final String method_add_task = "rtm.tasks.add";

    @NonNls
    private static final String param_timeline = "timeline";

    @NonNls
    private static final String param_name = "name";

    @NonNls
    private static final String param_parse = "parse";

    private final Request request;
    private final Response response;

    /**
     * Creates a fully formed REST request for adding tasks to Remember The
     * Milk, sends that request and retrieves a response.
     *
     * @param config   Config object for access to API key etc.
     * @param session  Session containing the timeline for this app run
     * @param smartAdd Smart Add string representing the task to add
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    public AddTask(final Config config,
                   final Session session,
                   final String smartAdd)
            throws DocumentException, NoSuchAlgorithmException, IOException,
            MalformedURLException
    {
        this.request = new RestRequest(config, method_add_task);
        this.request.addParameter(Request.PARAM_API_KEY, config.getApiKey());
        this.request.addParameter(param_timeline, session.getTimeline());
        this.request.addParameter(param_name, smartAdd);
        this.request.addParameter(param_parse, "1");
        this.request.addParameter(Request.PARAM_AUTH_TOKEN, config.getToken());

        this.response = Response.createResponse(this.request);
    }

    public Response getResponse()
    {
        return this.response;
    }

    @Override
    public String toString()
    {
        return "AddTask{" +
                "request=" + this.request +
                ", response=" + this.response +
                '}';
    }
}
