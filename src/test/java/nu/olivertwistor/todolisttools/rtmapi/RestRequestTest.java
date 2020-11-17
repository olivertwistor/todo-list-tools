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

/**
 * Unit tests for the {@link RestRequest} class.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class RestRequestTest
{
    private static Config config;

    /**
     * Tries to create a Config object from the file "dev-config.ini" (a
     * development config file containing a proper API key and shared secret).
     *
     * @since 0.1.0
     */
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

    /**
     * Asserts that the following statement is true: "When adding URL
     * parameters, the resulting URL will show the parameters in alphanumerical order."
     *
     * @since 0.1.0
     */
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

    /**
     * Asserts that the following statement is true: "Given a known string, its
     * hash value is the MD5 hash."
     *
     * @since 0.1.0
     */
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
