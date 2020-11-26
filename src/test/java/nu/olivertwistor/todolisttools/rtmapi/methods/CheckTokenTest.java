package nu.olivertwistor.todolisttools.rtmapi.methods;

import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.*;

public class CheckTokenTest
{
    private static Config config;

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

    @Test
    public void When_InvalidTokenIsGiven_Then_CheckTokenReturnsFail()
    {
        try
        {
            final CheckToken checkToken = new CheckToken(config, "foobar");

            final boolean failResponse =
                    checkToken.getResponse().isResponseFailure();

            Assert.assertThat(failResponse, is(true));
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}
