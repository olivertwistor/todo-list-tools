package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.util.Config;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;

public class CreateTimelineTest
{
    private static Config config;

    /**
     * Sets up the foundation for all the tests in this class. A config file
     * containing a valid authentication token is loaded.
     *
     * @since 0.1.0
     */
    @BeforeClass
    public static void setUp()
    {
        try
        {
            config = new Config("dev-config.ini");
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Asserts that when a timeline is created, {@link CreateTimeline} returns
     * success.
     *
     * @since 0.1.0
     */
    @Test
    public void When_TimelineIsCreated_Then_CreateTimelineReturnsSuccess()
    {
        try
        {
            final CreateTimeline createTimeline = new CreateTimeline(config);

            final boolean successResponse =
                    createTimeline.getResponse().isResponseSuccess();

            Assert.assertThat(successResponse, is(true));
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}
