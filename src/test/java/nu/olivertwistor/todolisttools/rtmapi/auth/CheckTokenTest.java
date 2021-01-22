package nu.olivertwistor.todolisttools.rtmapi.auth;

import nu.olivertwistor.todolisttools.util.Config;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

/**
 * Unit tests for the {@link CheckToken} class.
 *
 * @since 1.0.0
 */
public final class CheckTokenTest
{
    private static Config config;

    /**
     * Sets up the foundation for all the tests in this class. A config file
     * containing a valid authentication token is loaded.
     *
     * @throws Exception if anything went wrong.
     *
     * @since 1.0.0
     */
    @BeforeClass
    public static void setUp() throws Exception
    {
        final URL url = CheckTokenTest.class.getResource("/app.cfg");
        config = new Config(url);
    }

    /**
     * Asserts that when a valid authentication is given, {@link CheckToken}
     * returns success.
     *
     * @throws Exception if anything went wrong.
     *
     * @since 1.0.0
     */
    @Test
    public void When_ValidTokenIsGiven_Then_CheckTokenReturnsSuccess()
            throws Exception
    {
        final CheckToken checkToken = new CheckToken(config, config.getToken());

        final boolean successResponse = checkToken.isResponseSuccess();

        Assert.assertThat("A known valid token should pass validation.",
                successResponse, CoreMatchers.is(true));
    }

    /**
     * Asserts that when an invalid authentication is given, {@link CheckToken}
     * returns failure.
     *
     * @throws Exception if anything went wrong.
     *
     * @since 1.0.0
     */
    @Test
    public void When_InvalidTokenIsGiven_Then_CheckTokenReturnsFail()
            throws Exception
    {
        final CheckToken checkToken = new CheckToken(config, "foobar");

        final boolean failResponse =
                checkToken.isResponseFailure();

        Assert.assertThat("A known invalid token should not validate.",
                failResponse, CoreMatchers.is(true));
    }
}
