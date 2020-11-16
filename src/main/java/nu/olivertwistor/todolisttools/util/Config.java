package nu.olivertwistor.todolisttools.util;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

/**
 * Configuration values from config files are read via this class.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class Config
{
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
    public Config(final String filePath)
            throws InvalidFileFormatException, IOException
    {
        this.ini = new Wini(new File(filePath));
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
        return this.ini.get("api", "key");
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
        return this.ini.get("api", "shared-secret");
    }

    public String getToken()
    {
        return this.ini.get("api", "auth-token");
    }

    public void setToken(final String token) throws IOException
    {
        this.ini.put("api", "auth-token", token);
        this.ini.store();
    }

    @Override
    public String toString()
    {
        return "Config{ini=" + this.ini + "}";
    }
}
