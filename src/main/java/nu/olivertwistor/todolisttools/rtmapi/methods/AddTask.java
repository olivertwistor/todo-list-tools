package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.Session;
import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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

    public AddTask(final Config config,
                   final Session session,
                   final String smartAdd) throws DocumentException, NoSuchAlgorithmException, IOException
    {
        this.request = new RestRequest(config, method_add_task);
        this.request.addParameter(Request.PARAM_API_KEY, config.getApiKey());
        this.request.addParameter(param_timeline, session.getTimeline());
        this.request.addParameter(param_name, smartAdd);
        this.request.addParameter(param_parse, "1");

        this.response = Response.createResponse(this.request);
    }
}
