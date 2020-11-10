package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.Constants;

import java.net.URI;
import java.net.URISyntaxException;
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
 * {@link #toUri()} to construct the REST request as a URL string.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class RestRequest
{
    private final Config config;
    private final SortedMap<String, String> parameters;

    /**
     * Creates a REST request.
     *
     * @param config     Config object for access to API key etc.
     * @param parameters a sorted map of parameters
     *
     * @since 0.1.0
     */
    public RestRequest(final Config config,
                       final SortedMap<String, String> parameters)
    {
        this.config = config;
        this.parameters = new TreeMap<>(parameters);
    }

    /**
     * Creates a REST request object.
     *
     * @param config Config object for access to API key etc.
     *
     * @since 0.1.0
     */
    public RestRequest(final Config config)
    {
        this(config, new TreeMap<>());
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
     * Creates a URI object based on the REST endpoint and added parameters.
     *
     * @return URI object needed for making the REST request.
     *
     * @throws URISyntaxException if the resulting URI is malformed.
     *
     * @since 0.1.0
     */
    public URI toUri() throws URISyntaxException
    {
        final URIBuilder builder = new URIBuilder(
                this.config.getRestEndPoint());
        this.parameters.forEach(
                (key, value) -> builder.addParameter(key, value));

        return builder.build();
    }

    /**
     * Hashes a UTF-8 encoded message, using the MD5 hashing algorithm.
     *
     * @param message the message string to hash
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
        final Charset charset = StandardCharsets.UTF_8;

        final MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(message.getBytes(charset));
        final byte[] digest = md.digest();

        return new String(digest, charset);
    }

    @Override
    public String toString()
    {
        return "RestRequest{config=" + this.config +
                ", parameters=" + this.parameters + "}";
    }
}
