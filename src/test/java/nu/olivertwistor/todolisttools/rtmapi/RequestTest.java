package nu.olivertwistor.todolisttools.rtmapi;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.is;

/**
 * Unit tests for the {@link Request} class.
 *
 * @since  0.1.0
 */
public class RequestTest
{
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
