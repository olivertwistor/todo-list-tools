package nu.olivertwistor.todolisttools;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;

/**
 * This class contains methods for communication with the Remember The Milk
 * API.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public final class RtmApi
{
    /**
     * Empty constructor to prevent instantiation.
     *
     * @since 0.1.0
     */
    private RtmApi() { }

    /**
     * Takes a sorted map of URL parameters and concatenates all keys and
     * values.
     *
     * @param parameters map of key/value pairs; for example
     *                   [["api_key", "abc123"], ["perm", "delete"]]
     *
     * @return A concatenated string of all parameters, sorted
     *         alphanumerically; for example "api_keyabc123permdelete".
     */
    @SuppressWarnings("TypeMayBeWeakened")
    public static String prepareParameters(
            final SortedMap<String, String> parameters)
    {
        final StringBuilder concatenatedString = new StringBuilder();

        parameters.forEach(
                (key, value) -> concatenatedString.append(key).append(value));

        return concatenatedString.toString();
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
