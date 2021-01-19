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
     * @since 1.0.0
     */
    @BeforeClass
    public static void setUp()
    {
        final URL url = CheckTokenTest.class.getResource("/app.cfg");
        CheckTokenTest.config = new Config(url);
    }

    /**
     * Asserts that when a valid authentication is given, {@link CheckToken}
     * returns success.
     *
     * @since 1.0.0
     */
    @Test
    public void When_ValidTokenIsGiven_Then_CheckTokenReturnsSuccess()
    {
        final CheckToken checkToken = new CheckToken(
                CheckTokenTest.config, CheckTokenTest.config.getToken());

        final boolean successResponse = checkToken.isResponseSuccess();

        Assert.assertThat("A known valid token should pass validation.",
                successResponse, CoreMatchers.is(true));
    }

    /**
     * Asserts that when an invalid authentication is given, {@link CheckToken}
     * returns failure.
     *
     * @since 1.0.0
     */
    @Test
    public void When_InvalidTokenIsGiven_Then_CheckTokenReturnsFail()
    {
        final CheckToken checkToken = new CheckToken(
                CheckTokenTest.config, "foobar");

        final boolean failResponse =
                checkToken.isResponseFailure();

        Assert.assertThat("A known invalid token should not validate.",
                failResponse, CoreMatchers.is(true));
    }
}
