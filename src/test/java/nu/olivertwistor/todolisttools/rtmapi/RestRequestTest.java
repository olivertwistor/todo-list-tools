package nu.olivertwistor.todolisttools.rtmapi;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the {@link RestRequest} class.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class RestRequestTest
{
    /**
     * Asserts that the following statement is true: "Given a known string, its
     * hash value is the MD5 hash."
     *
     * @throws Exception if something goes wrong
     *
     * @since 0.1.0
     */
    @SuppressWarnings("HardCodedStringLiteral")
    @Test
    public void Given_KnownString_Then_ValidHash() throws Exception
    {
        Assert.assertThat("MD5 hash of known string should be as expected.",
                Request.hash("hello.world"),
                CoreMatchers.is("18aa7764566d19e9a9afb6ea0bf1fa81"));
    }
}
