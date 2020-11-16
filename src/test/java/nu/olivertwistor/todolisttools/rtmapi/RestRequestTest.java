package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.rtmapi.requests.RestRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.*;

public class RestRequestTest
{
    private static Config config;

    @SuppressWarnings("OverlyBroadCatchBlock")
    @BeforeClass
    public static void setup()
    {
        try
        {
            config = new Config("dev-config.ini");
        }
        catch (final IOException e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void When_AddingParameters_Then_UrlWillShowThemInOrder()
    {
        final RestRequest request = new RestRequest(config, "rtm.test");
        request.addParameter("api_key", "abc123");
        request.addParameter("contacts", "true");
        try
        {
            Assert.assertThat(request.toUri().toString(), is(
                    "https://api.rememberthemilk.com/services/rest/" +
                            "?api_key=abc123&contacts=true" +
                            "&method=rtm.test" +
                            "&api_sig=974f8b7b6cfe60b1b14f71501573fb90"));

        }
        catch (final URISyntaxException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void Given_KnownString_Then_ValidHash()
    {
        try
        {
            Assert.assertThat(
                    Request.hash("hello.world"),
                    is("18aa7764566d19e9a9afb6ea0bf1fa81"));
        }
        catch (final NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }
}
