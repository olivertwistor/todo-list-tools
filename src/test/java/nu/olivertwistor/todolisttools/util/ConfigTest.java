package nu.olivertwistor.todolisttools.util;

import org.ini4j.InvalidFileFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;

class ConfigTest
{
    @Test
    public void getApiKey()
    {
        try
        {
            final Config config = new Config("config.ini");
            final String apiKey = config.getApiKey();

            Assert.assertThat(apiKey, is("xxx"));
        }
        catch (final InvalidFileFormatException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
