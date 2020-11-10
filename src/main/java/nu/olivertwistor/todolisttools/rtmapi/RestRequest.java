package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.util.Config;
import nu.olivertwistor.todolisttools.util.Constants;
import org.apache.http.client.utils.URIBuilder;

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
 * This is the base class for a REST request to the Remember the Milk API.
 *
 * A RestRequest consists of parameters represented by key/value pairs stored
 * in a {@link SortedMap}. When all desired parameters have been added, call
 * {@link #toUri()} to construct the REST request as a URL string.
 *
 * Different API methods could be inheriting from this class in order to
 * simplify calls, for example by adding known parameters in their constructors
 * so the client doesn't have to manually add them later.
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
     * @param methodName name of the method to call
     * @param parameters a sorted map of additional parameters
     *
     * @since 0.1.0
     */
    public RestRequest(final Config config,
                       final String methodName,
                       final SortedMap<String, String> parameters)
    {
        this.config = config;
        this.parameters = new TreeMap<>(parameters);
        this.parameters.put(Constants.METHOD_PARAM, methodName);
    }

    /**
     * Creates a REST request object.
     *
     * @param config     Config object for access to API key etc.
     * @param methodName name of the method to call
     *
     * @since 0.1.0
     */
    public RestRequest(final Config config, final String methodName)
    {
        this(config, methodName, new TreeMap<>());
    }

    public String generateSignature() throws NoSuchAlgorithmException
    {
        final StringBuilder beforeHash = new StringBuilder(
                this.config.getSharedSecret());
        this.parameters.forEach(
                (key, value) -> beforeHash.append(key).append(value));

        System.out.println(beforeHash);

        return hash(beforeHash.toString());
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
    public URI toUri() throws URISyntaxException, NoSuchAlgorithmException
    {
        final URIBuilder builder = new URIBuilder(Constants.ENDPOINT);
        this.parameters.forEach(
                (key, value) -> builder.addParameter(key, value));
        builder.addParameter(Constants.API_SIG_PARAM, this.generateSignature());

        return builder.build();
    }

    /**
     * Creates a URL object based on the REST endpoint and added parameters.
     *
     * @return URL object needed for making the REST request.
     *
     * @throws MalformedURLException if the resulting URL is malformed.
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

    @Override
    public String toString()
    {
        return "RestRequest{config=" + this.config +
                ", parameters=" + this.parameters + "}";
    }
}
