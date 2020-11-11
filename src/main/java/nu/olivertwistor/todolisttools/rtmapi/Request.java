package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.Constants;

import javax.xml.bind.DatatypeConverter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * This is the base class for a request to any of the services Remember the
 * Milk provides.
 *
 * A Request consists of parameters represented by key/value pairs stored in a
 * {@link SortedMap}. When all desired parameters have been added, call
 * {@link #toUri()} or {@link #toUrl()} to construct the request as a URI/URL
 * string.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public abstract class Request
{
    protected final Config config;
    protected final SortedMap<String, String> parameters;

    /**
     * Creates a request.
     *
     * @param config     Config object for access to API key etc.
     * @param parameters a sorted map of additional parameters
     *
     * @since 0.1.0
     */
    protected Request(final Config config,
                      final SortedMap<String, String> parameters)
    {
        this.config = config;
        this.parameters = new TreeMap<>(parameters);
        this.parameters.put(Constants.API_KEY_PARAM, config.getApiKey());
    }

    /**
     * Creates a request.
     *
     * @param config     Config object for access to API key etc.
     *
     * @since 0.1.0
     */
    protected Request(final Config config)
    {
        this(config, new TreeMap<>());
    }

    /**
     * Adds a URL parameter.
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
     * Creates a URI object based on an endpoint, added parameters and a hashed
     * api_sig parameter using {@link #hash(String)}.
     *
     * @return URI object needed for making the request.
     *
     * @throws URISyntaxException       if the resulting URI is malformed.
     * @throws NoSuchAlgorithmException if the hashing algorith used by
     *                                  {@link #hash(String)} doesn't exist
     *
     * @since 0.1.0
     */
    public abstract URI toUri()
            throws URISyntaxException, NoSuchAlgorithmException;

    /**
     * Creates a URL object based on an endpoint, added parameters and a hashed
     * api_sig parameter using {@link #hash(String)}.
     *
     * @return URL object needed for making the request.
     *
     * @throws MalformedURLException    if the resulting URL is malformed.
     * @throws NoSuchAlgorithmException if the hashing algorith used by
     *                                  {@link #hash(String)} doesn't exist
     *
     * @since 0.1.0
     */
    public URL toUrl() throws MalformedURLException, NoSuchAlgorithmException
    {
        try
        {
            return this.toUri().toURL();
        }
        catch (final URISyntaxException e)
        {
            throw new MalformedURLException(e.getMessage());
        }
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
    static String hash(final String message) throws NoSuchAlgorithmException
    {
        final Charset charset = StandardCharsets.UTF_8;

        final MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(message.getBytes(charset));
        final byte[] digest = md.digest();

        return DatatypeConverter.printHexBinary(digest)
                .toLowerCase(Locale.ENGLISH);
    }

    /**
     * Generates a API signature to be used with the api_sig URL parameter. The
     * signature is made up by a hashed concatenation of the shared secret and
     * each URL parameter key/value pair.
     *
     * @return An API signature.
     *
     * @throws NoSuchAlgorithmException if the hashing algorith used by
     *                                  {@link #hash(String)} doesn't exist
     *
     * @since 0.1.0
     */
    String generateSignature() throws NoSuchAlgorithmException
    {
        final StringBuilder beforeHash = new StringBuilder(
                this.config.getSharedSecret());
        this.parameters.forEach(
                (key, value) -> beforeHash.append(key).append(value));

        System.out.println(beforeHash);

        return hash(beforeHash.toString());
    }

    @Override
    public String toString()
    {
        return "RestRequest{config=" + this.config +
                ", parameters=" + this.parameters + "}";
    }
}
