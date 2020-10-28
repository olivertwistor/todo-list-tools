package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.util.Config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This is the base class for a REST request to the Remember the Milk API.
 */
public class RestRequest
{
    private final Config config;
    private final String method;
    private final String apiKey;
    private final String responseFormat;
    private final String apiVersion;

    public RestRequest(final Config config,
                       final String method,
                       final String apiKey,
                       final String responseFormat,
                       final String apiVersion)
    {
        this.config = config;
        this.method = method;
        this.apiKey = apiKey;
        this.responseFormat = responseFormat;
        this.apiVersion = apiVersion;
    }

    public RestRequest(final Config config,
                       final String method,
                       final String apiKey)
    {
        this(config, method, apiKey, null, null);
    }

    /**
     * Hashes a message with a certain character set, using a certain hashing
     * algorithm.
     *
     * @param message   the message string to hash
     * @param charset   character set of the message
     * @param algorithm hashing algorithm to use
     *
     * @return The message, hashed using the given hashing algorithm.
     *
     * @throws NoSuchAlgorithmException if the given hashing algorithm couldn't
     *                                  be found
     *
     * @since 0.1.0
     */
    public static String hash(final String message,
                              final Charset charset,
                              final String algorithm)
            throws NoSuchAlgorithmException
    {
        final MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(message.getBytes(charset));
        final byte[] digest = md.digest();

        return new String(digest, charset);
    }

    /**
     * Hashes a UTF-8 encoded message, using the MD5 hashing algorithm.
     *
     * @param message   the message string to hash
     *
     * @return The message, hashed using MD5.
     *
     * @throws NoSuchAlgorithmException if the MD5 hashing algorithm couldn't
     *                                  be found
     *
     * @since 0.1.0
     */
    public static String hash(final String message)
            throws NoSuchAlgorithmException
    {
        return hash(message, StandardCharsets.UTF_8, "MD5");
    }
}
