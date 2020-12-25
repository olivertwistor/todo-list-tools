package nu.olivertwistor.todolisttools.util;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Configuration values from config files are read via this class.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
@SuppressWarnings({"ClassWithoutLogger", "PublicMethodWithoutLogging"})
public final class Config
{
    @NonNls
    private static final String GROUP_API = "api";

    @NonNls
    private static final String PROP_KEY = "key";

    @NonNls
    private static final String PROP_SHARED_SECRET = "shared-secret";

    @NonNls
    private static final String PROP_AUTH_TOKEN = "auth-token";

    private final Wini ini;

    /**
     * Creating a new instance of this object based on a certain config file.
     *
     * @param filePath path to the config file to use
     *
     * @throws InvalidFileFormatException if the given config file isn't
     *                                    formatted correctly
     * @throws IOException                if the given config file couldn't be
     *                                    found or read
     *
     * @since 0.1.0
     */
    public Config(final @NonNls String filePath)
            throws InvalidFileFormatException, IOException
    {
        this.ini = new Wini(new File(filePath));
    }

    /**
     * Creating a new instance of this object based on a certain URL pointing
     * to configuration.
     *
     * @param url URL pointing to configuration
     *
     * @throws InvalidFileFormatException if the configuration isn't formatted
     *                                    correctly
     * @throws IOException                if the given URL couldn't be found or
     *                                    read
     *
     * @since 0.1.0
     */
    public Config(final URL url) throws InvalidFileFormatException, IOException
    {
        this.ini = new Wini(url);
    }

    /**
     * Gets the API key from the config file.
     *
     * @return The API key; or null if the config file key couldn't be found.
     *
     * @since 0.1.0
     */
    public String getApiKey()
    {
        return this.ini.get(Config.GROUP_API, Config.PROP_KEY);
    }

    /**
     * Gets the shared secret from the config file.
     *
     * @return The shared secret; or null if the config file key couldn't be
     *         found.
     *
     * @since 0.1.0
     */
    public String getSharedSecret()
    {
        return this.ini.get(Config.GROUP_API, Config.PROP_SHARED_SECRET);
    }

    /**
     * Gets the authentication token from the config file.
     *
     * @return The authentication token; or null if the config file key
     *         couldn't be found.
     *
     * @since 0.1.0
     */
    public String getToken()
    {
        return this.ini.get(Config.GROUP_API, Config.PROP_AUTH_TOKEN);
    }

    /**
     * Sets the authentication token and writes it to the config file.
     *
     * @param token the authentication token
     *
     * @throws IOException if the config file couldn't be written to
     *
     * @since 0.1.0
     */
    public void setToken(final String token) throws IOException
    {
        this.ini.put(Config.GROUP_API, Config.PROP_AUTH_TOKEN, token);
        this.ini.store();
    }

    @Override
    public @NonNls String toString()
    {
        return "Config{ini=" + this.ini + '}';
    }
}
