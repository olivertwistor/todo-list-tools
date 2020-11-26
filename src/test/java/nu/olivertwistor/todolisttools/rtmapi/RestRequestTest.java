package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.util.Config;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
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
