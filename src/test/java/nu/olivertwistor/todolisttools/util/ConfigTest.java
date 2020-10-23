package nu.olivertwistor.todolisttools.util;

import org.ini4j.InvalidFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ConfigTest
{
    @Test
    void getApiKey()
    {
        try
        {
            final Config config = new Config("config.ini");
            final String apiKey = config.getApiKey();

            Assertions.assertEquals("xxx", apiKey);
        }
        catch (InvalidFileFormatException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
