package nu.olivertwistor.todolisttools.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NonNls;

/**
 * A session holds the current timeline. The timeline is a bookmark of sorts,
 * after which some actions may be undone. Actions made before the timeline may
 * not. At object instantiation, there is no timeline at all.
 *
 * @since 1.0.0
 */
@SuppressWarnings("ClassUnconnectedToPackage")
public final class Session
{
    private static final @NonNls Logger LOG = LogManager.getLogger(
            Session.class);

    private String timeline;

    /**
     * Creates a new session object with no timeline set.
     *
     * @since 1.0.0
     */
    public Session()
    {
        LOG.trace("Entering Session()...");

        this.timeline = "";
    }

    public String getTimeline()
    {
        LOG.trace("Entering getTimeline()...");

        return this.timeline;
    }

    /**
     * Determines whether this session has a timeline.
     *
     * @return Boolean.
     *
     * @since 1.0.0
     */
    public boolean hasTimeline()
    {
        LOG.trace("Entering hasTimeline()...");

        return !this.timeline.isEmpty();
    }

    public void setTimeline(final String timeline)
    {
        LOG.trace("Entering setTimeline(String)...");

        this.timeline = timeline;
    }

    @Override
    public String toString()
    {
        return "Session{" +
                "timeline='" + this.timeline + '\'' +
                '}';
    }
}
