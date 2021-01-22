package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.util.Config;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

/**
 * Unit tests for the {@link Authentication} class.
 *
 * @since 1.0.0
 */
public final class AuthenticationTest
{
    private static Config config;

    /**
     * Creates a config object from test resources.
     *
     * @throws Exception if anything went wrong.
     *
     * @since 1.0.0
     */
    @BeforeClass
    public static void setUp() throws Exception
    {
        final URL url = AuthenticationTest.class.getResource("/app.cfg");
        config = new Config(url);
    }

    /**
     * Asserts that when generating an authentication request, no exception is
     * thrown.
     *
     * @throws Exception if anything went wrong.
     *
     * @since 1.0.0
     */
    @Test
    public void When_GeneratingAuthRequest_Then_NoExceptionIsThrown()
            throws Exception
    {
        final Authentication authentication = new Authentication();
        authentication.generateAuthRequest(config, "write");
    }
}
