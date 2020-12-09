package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Unit tests for the {@link CreateTimeline} class.
 *
 * @since 0.1.0
 */
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
     * @throws Exception if anything goes awry
     *
     * @since 0.1.0
     */
    @Test
    public void When_TimelineIsCreated_Then_CreateTimelineReturnsSuccess()
            throws Exception
    {
        final CreateTimeline createTimeline = new CreateTimeline(config);
        final Response response = createTimeline.getResponse();

        try
        {
            Assert.assertThat(response.isResponseSuccess(), is(true));
        }
        catch (final AssertionError e)
        {
            System.out.println("Response was:" + System.lineSeparator() +
                    response.toXmlString());
            throw e;
        }
    }
}
