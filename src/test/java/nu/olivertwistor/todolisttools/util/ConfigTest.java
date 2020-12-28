package nu.olivertwistor.todolisttools.util;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Unit tests of the {@link Config} class.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
@SuppressWarnings("StringConcatenation")
public final class ConfigTest
{
    /**
     * Asserts that an instantiated Config class can read the API key
     * property.
     *
     * @since 0.1.0
     */
    @SuppressWarnings("HardCodedStringLiteral")
    @Test
    public void getApiKey()
    {
        try
        {
            final Config config = new Config("config.ini");
            final String apiKey = config.getApiKey();

            Assert.assertThat("The api key property should be readable from " +
                    "the config file", apiKey, Is.is("xxx"));
        }
        catch (final IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
}
