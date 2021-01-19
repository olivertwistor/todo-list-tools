package nu.olivertwistor.todolisttools.util;

import org.jetbrains.annotations.NonNls;

/**
 * A session holds the current timeline. The timeline is a bookmark of sorts,
 * after which some actions may be undone. Actions made before the timeline may
 * not. At object instantiation, there is no timeline at all.
 *
 * @since 1.0.0
 */
public final class Session
{
    private final Config config;
    private String timeline;

    /**
     * Creates a new session object with no timeline set.
     *
     * @param config Config object for access to API key etc.
     *
     * @since 1.0.0
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
     * @since 1.0.0
     */
    public boolean hasTimeline()
    {
        return !this.timeline.isEmpty();
    }

    public void setTimeline(final String timeline)
    {
        this.timeline = timeline;
    }
}
