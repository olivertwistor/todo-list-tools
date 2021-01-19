package nu.olivertwistor.todolisttools.rtmapi;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link Request} class.
 *
 * @since  1.0.0
 */
public final class RequestTest
{
    /**
     * Asserts that the following statement is true: "Given a known string, its
     * hash value is the MD5 hash."
     *
     * @since 1.0.0
     */
    @Test
    public void Given_KnownString_Then_ValidHash()
    {
        Assert.assertThat("MD5 hash of known string should be as expected.",
                Request.hash("hello.world"),
                CoreMatchers.is("18aa7764566d19e9a9afb6ea0bf1fa81"));
    }
}
