package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.util.Config;
import org.ini4j.InvalidFileFormatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestRequestTest
{
    private static Config config = null;

    @BeforeAll
    static void setup()
    {
        try
        {
            config = new Config("config.ini");
        }
        catch (final InvalidFileFormatException e)
        {
            System.err.println("Invalid file format.");
            e.printStackTrace();
        }
        catch (final IOException e)
        {
            System.err.println("I/O exception.");
            e.printStackTrace();
        }
    }

    @Test
    public void When_AddingParameters_Then_UrlWillShowThemInOrder()
    {
        final RestRequest request = new RestRequest(
                "https://api.rememberthemilk.com/services/rest/");
        request.addParameter("api_key", "abc123");
        request.addParameter("method", "rtm.test");
        request.addParameter("contacts", "true");
        final String url = request.toUrl();

        assertEquals(
                url,
                "https://api.rememberthemilk.com/services/rest/" +
                        "?api_key=abc123&contacts=true&method=rtm.test");
    }
}
