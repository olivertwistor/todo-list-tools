package nu.olivertwistor.todolisttools.util;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

/**
 * Unit tests of the {@link Config} class.
 *
 * @since 1.0.0
 */
public final class ConfigTest
{
    private static Config config;

    /**
     * Sets up the foundation for all the tests in this class. A config file
     * containing an API key property is loaded.
     *
     * @throws Exception if anything went wrong.
     *
     * @since 1.0.0
     */
    @BeforeClass
    public static void setUp() throws Exception
    {
        final URL url = ConfigTest.class.getResource("/app.cfg");
        config = new Config(url);
    }

    /**
     * Asserts that an instantiated Config class can read the API key
     * property.
     *
     * @since 1.0.0
     */
    @Test
    public void When_ReadingApiKey_Then_NullIsNotReturned()
    {
        final String apiKey = config.getApiKey();

        Assert.assertThat("The API key property should be readable from the " +
                "config file.", apiKey, CoreMatchers.notNullValue());
    }

    /**
     * Assert that writing a new config value, no exception is thrown.
     *
     * @throws Exception if anything went wrong.
     *
     * @since 1.0.0
     */
    @Test
    public void When_WritingToken_Then_NoExceptionIsThrown() throws Exception
    {
        config.setToken("test");
    }
}
