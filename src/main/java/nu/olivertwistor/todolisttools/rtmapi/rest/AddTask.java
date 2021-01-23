package nu.olivertwistor.todolisttools.rtmapi.rest;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;

/**
 * This class handles adding tasks to Remember The Milk.
 *
 * @since 1.0.0
 */
public final class AddTask
{
    private static final @NonNls Logger LOG = LogManager.getLogger(
            AddTask.class);

    @NonNls
    private static final String METHOD_ADD_TASK = "rtm.tasks.add";

    @NonNls
    private static final String PARAM_TIMELINE = "timeline";

    @NonNls
    private static final String PARAM_NAME = "name";

    @NonNls
    private static final String PARAM_PARSE = "parse";

    private final Response response;

    /**
     * Creates a fully formed REST request for adding tasks to Remember The
     * Milk, sends that request and retrieves a response.
     *
     * @param config   Config object for access to API key etc.
     * @param session  Session containing the timeline for this app run
     * @param smartAdd Smart Add string representing the task to add
     *
     * @throws IOException if connection to Remember The Milk failed.
     *
     * @since 1.0.0
     */
    public AddTask(final Config config,
                   final Session session,
                   final String smartAdd) throws IOException
    {
        LOG.trace("Entering AddTask(Config, Session, String)...");

        final String apiKey = config.getApiKey();
        final String token = config.getToken();

        final Request request = new RestRequest(
                config, AddTask.METHOD_ADD_TASK);
        request.addParameter(Request.PARAM_API_KEY, apiKey);
        request.addParameter(
                AddTask.PARAM_TIMELINE, session.getTimeline());
        request.addParameter(AddTask.PARAM_NAME, smartAdd);
        request.addParameter(AddTask.PARAM_PARSE, "1"); //NON-NLS
        request.addParameter(Request.PARAM_AUTH_TOKEN, token);

        this.response = Response.createResponse(request);
    }

    public boolean isResponseSuccess()
    {
        LOG.trace("Entering isResponseSuccess()...");

        return this.response.isResponseSuccess();
    }

    @Override
    public String toString()
    {
        return "AddTask{" +
                "response=" + this.response +
                '}';
    }
}
