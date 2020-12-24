package nu.olivertwistor.todolisttools.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;

/**
 * Unit tests of the {@link Config} class.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class ConfigTest
{
    /**
     * Asserts that an instantiated Config class can read the API key
     * property.
     *
     * @since 0.1.0
     */
    @Test
    public void getApiKey()
    {
        try
        {
            final Config config = new Config("config.ini");
            final String apiKey = config.getApiKey();

            Assert.assertThat(apiKey, is("xxx"));
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }
}
