package nu.olivertwistor.todolisttools;

import nu.olivertwistor.todolisttools.rtmapi.methods.CreateTimeline;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;

public class Session
{
    private final Config config;
    private String timeline;

    public Session(final Config config)
    {
        this.config = config;
        this.timeline = "";
    }

    public String getTimeline()
    {
        return this.timeline;
    }

    public boolean hasTimeline()
    {
        return !this.timeline.isEmpty();
    }

    public void createTimeline() throws DocumentException,
            NoSuchAlgorithmException, IOException, MalformedURLException
    {
        final CreateTimeline createTimeline = new CreateTimeline(this.config);
        this.timeline = createTimeline.getTimeline();
    }
}
