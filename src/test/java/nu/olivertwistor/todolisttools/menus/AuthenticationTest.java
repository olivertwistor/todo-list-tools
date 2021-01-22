package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.util.Config;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

public final class AuthenticationTest
{
    private static Config config;
    private static Authentication authentication;

    @BeforeClass
    public static void setUp() throws Exception
    {
        final URL url = AuthenticationTest.class.getResource("/app.cfg");
        config = new Config(url);

        authentication = new Authentication();
    }

    @Test
    public void When_GeneratingAuthRequest_Then_NoExceptionIsThrown()
            throws Exception
    {
        authentication.generateAuthRequest(config, "write");
    }
}
