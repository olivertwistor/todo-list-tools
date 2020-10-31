package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.util.Config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

/**
 * This is the base class for a REST request to the Remember the Milk API.
 *
 * A RestRequest consists of parameters represented by key/value pairs stored
 * in a {@link SortedMap}. When all desired parameters have been added, call
 * {@link #toUrl()} to construct the REST request as a URL string.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class RestRequest
{
    private final String endpoint;
    private final SortedMap<String, String> parameters;

    /**
     * Creates a REST request.
     *
     * @param endpoint   the endpoint URL for REST requests
     * @param parameters a sorted map of parameters
     *
     * @since 0.1.0
     */
    public RestRequest(final String endpoint,
                       final SortedMap<String, String> parameters)
    {
        this.endpoint = endpoint;
        this.parameters = new TreeMap<>(parameters);
    }

    /**
     * Creates a REST request object.
     *
     * @param endpoint the endpoint URL for REST requests
     *
     * @since 0.1.0
     */
    public RestRequest(final String endpoint)
    {
        this(endpoint, new TreeMap<>());
    }

    /**
     * Adds a REST parameter.
     *
     * @param key   the parameter key
     * @param value the parameter value
     *
     * @since 0.1.0
     */
    public void addParameter(final String key, final String value)
    {
        this.parameters.put(key, value);
    }

    /**
     * Creates a URL string based on the REST endpoint and added parameters.
     *
     * @return The complete URL string needed for making the REST request.
     *
     * @since 0.1.0
     */
    public String toUrl()
    {
        String url;

        final StringBuilder sb = new StringBuilder(this.endpoint).append("?");
        this.parameters.forEach((key, value) ->
                        sb.append(key).append("=").append(value).append("&"));

        // Strip out the last ampersand.
        url = sb.toString();
        url = url.substring(0, url.length() - 1);

        return url;
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

    @Override
    public String toString()
    {
        return "RestRequest{endpoint=" + this.endpoint +
                ", parameters=" + this.parameters + "}";
    }
}
