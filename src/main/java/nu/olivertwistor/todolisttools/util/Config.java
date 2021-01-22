package nu.olivertwistor.todolisttools.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ini4j.Ini;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Configuration values from config files are read via this class.
 *
 * @since  1.0.0
 */
public final class Config
{
    private static final @NonNls Logger LOG = LogManager.getLogger(
            Config.class);

    @NonNls
    private static final String GROUP_API = "api";

    @NonNls
    private static final String PROP_KEY = "key";

    @NonNls
    private static final String PROP_SHARED_SECRET = "shared-secret";

    @NonNls
    private static final String PROP_AUTH_TOKEN = "auth-token";

    private final File file;
    private final Ini ini;

    /**
     * Creating a new instance of this object based on a certain URL pointing
     * to configuration.
     *
     * @param url URL pointing to configuration
     *
     * @throws IOException if a configuration object couldn't be created from
     *                     the provided URL
     *
     * @since 1.0.0
     */
    public Config(final URL url) throws IOException
    {
        LOG.trace("Entering Config(URL)...");

        final URI uri;
        try
        {
            uri = url.toURI();
        }
        catch (final URISyntaxException e)
        {
            throw new IOException(e);
        }

        this.file = new File(uri);
        this.ini = new Ini(url);
    }

    /**
     * Gets the API key from the config file.
     *
     * @return The API key; or null if the config file key couldn't be found.
     *
     * @since 1.0.0
     */
    public String getApiKey()
    {
        LOG.trace("Entering getApiKey()...");

        return this.ini.get(Config.GROUP_API, Config.PROP_KEY);
    }

    /**
     * Sets the API key and writes it to the config file.
     *
     * @param apiKey the API key
     *
     * @throws IOException if the API key couldn't be written to the config
     *                     file.
     *
     * @since 1.0.0
     */
    public void setApiKey(final String apiKey) throws IOException
    {
        LOG.trace("Entering setApiKey(String)...");

        this.ini.put(Config.GROUP_API, Config.PROP_KEY, apiKey);
        this.ini.store(this.file);
    }

    /**
     * Gets the shared secret from the config file.
     *
     * @return The shared secret; or null if the config file key couldn't be
     *         found.
     *
     * @since 1.0.0
     */
    public String getSharedSecret()
    {
        LOG.trace("Entering getSharedSecret()...");

        return this.ini.get(Config.GROUP_API, Config.PROP_SHARED_SECRET);
    }

    /**
     * Sets the shared secret and writes it to the config file.
     *
     * @param sharedSecret the shared secret
     *
     * @throws IOException if the shared secret couldn't be written to the
     *                     config file.
     *
     * @since 1.0.0
     */
    public void setSharedSecret(final String sharedSecret) throws IOException
    {
        LOG.trace("Entering setSharedSecret(String)...");

        this.ini.put(Config.GROUP_API, Config.PROP_SHARED_SECRET, sharedSecret);
        this.ini.store(this.file);
    }

    /**
     * Gets the authentication token from the config file.
     *
     * @return The authentication token; or null if the config file key
     *         couldn't be found.
     *
     * @since 1.0.0
     */
    public String getToken()
    {
        LOG.trace("Entering getToken()...");

        return this.ini.get(Config.GROUP_API, Config.PROP_AUTH_TOKEN);
    }

    /**
     * Sets the authentication token and writes it to the config file.
     *
     * @param token the authentication token
     *
     * @throws IOException if the token value couldn't be written to the config
     *                     file.
     *
     * @since 1.0.0
     */
    public void setToken(final String token) throws IOException
    {
        LOG.trace("Entering setToken(String)...");

        this.ini.put(Config.GROUP_API, Config.PROP_AUTH_TOKEN, token);
        this.ini.store(this.file);
    }

    @Override
    public String toString()
    {
        return "Config{" +
                "file=" + this.file +
                ", ini=" + this.ini +
                '}';
    }
}
