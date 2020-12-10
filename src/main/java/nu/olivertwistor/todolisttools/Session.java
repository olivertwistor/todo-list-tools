package nu.olivertwistor.todolisttools;

import nu.olivertwistor.todolisttools.rtmapi.methods.CreateTimeline;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

/**
 * A session holds the current timeline. The timeline is a bookmark of sorts,
 * after which some actions may be undone. Actions made before the timeline may
 * not. Call {@link #createTimeline()} to create a new timeline whenever a new
 * "bookmark" should be set. At object instantiation, there is no timeline at
 * all.
 *
 * @since 0.1.0
 */
public class Session
{
    private final Config config;
    private String timeline;

    /**
     * Creates a new session object with no timeline set.
     *
     * @param config Config object for access to API key etc.
     *
     * @since 0.1.0
     */
    public Session(final Config config)
    {
        this.config = config;
        this.timeline = "";
    }

    public String getTimeline()
    {
        return this.timeline;
    }

    /**
     * Determines whether this session has a timeline.
     *
     * @return Boolean.
     *
     * @since 0.1.0
     */
    public boolean hasTimeline()
    {
        return !this.timeline.isEmpty();
    }

    /**
     * Creates a new timeline by requesting one from the Remember The Milk API,
     * and stores it within this session object.
     *
     * Note that this action will invalidate the last timeline, making all
     * actions made before calling this method undoable.
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    public void createTimeline() throws DocumentException,
            NoSuchAlgorithmException, IOException, MalformedURLException
    {
        final CreateTimeline createTimeline = new CreateTimeline(this.config);
        this.timeline = createTimeline.getTimeline();
    }

    @Override
    public String toString()
    {
        return "Session{" +
                "config=" + this.config +
                ", timeline='" + this.timeline + '\'' +
                '}';
    }
}
