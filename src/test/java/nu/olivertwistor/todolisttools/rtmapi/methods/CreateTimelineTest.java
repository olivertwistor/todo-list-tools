package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.util.Config;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

/**
 * Unit tests for the {@link CreateTimeline} class.
 *
 * @since 0.1.0
 */
@SuppressWarnings("StringConcatenation")
public final class CreateTimelineTest
{
    private static Config config;

    /**
     * Sets up the foundation for all the tests in this class. A config file
     * containing a valid authentication token is loaded.
     *
     * @throws Exception if anything goes wrong
     *
     * @since 0.1.0
     */
    @BeforeClass
    public static void setUp() throws Exception
    {
        final URL url = CreateTimelineTest.class.getResource("/app.cfg");
        CreateTimelineTest.config = new Config(url);
    }

    /**
     * Asserts that when a timeline is created, {@link CreateTimeline} returns
     * success.
     *
     * @throws Exception if anything goes wrong
     *
     * @since 0.1.0
     */
    @SuppressWarnings({"HardCodedStringLiteral", "LawOfDemeter"})
    @Test
    public void When_TimelineIsCreated_Then_CreateTimelineReturnsSuccess()
            throws Exception
    {
        final CreateTimeline createTimeline =
                new CreateTimeline(CreateTimelineTest.config);
        final Response response = createTimeline.getResponse();
        final boolean success = createTimeline.isResponseSuccess();

        try
        {
            Assert.assertThat("A timeline should be retrieved from RTM.",
                    success, CoreMatchers.is(true));
        }
        catch (final AssertionError e)
        {
            System.out.println("Response was: " + System.lineSeparator() +
                    response.toXmlString());
            throw e;
        }
    }
}
