package nu.olivertwistor.todolisttools.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ini4j.Ini;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Configuration values from config files are read via this class.
 *
 * @since  1.0.0
 */
@SuppressWarnings({"StringConcatenationMissingWhitespace", "ClassUnconnectedToPackage"})
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
     * Creating a new instance of this object based on a config file.
     *
     * @param file a file containing configuration properties in key/value pairs
     *
     * @throws FileNotFoundException if the provided file doesn't exist
     * @throws IOException           if a configuration object couldn't be
     *                               created from the provided file
     *
     * @since 1.0.0
     */
    public Config(final File file) throws FileNotFoundException, IOException
    {
        LOG.trace("Entering Config(File)...");

        if (!file.exists())
        {
            throw new FileNotFoundException(file + "does not exist.");
        }

        this.file = file;
        this.ini = new Ini(file);
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
