package nu.olivertwistor.todolisttools.util;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

public class Config
{
    private final Wini ini;

    public Config(final String filePath)
            throws InvalidFileFormatException, IOException
    {
        this.ini = new Wini(new File(filePath));
    }

    public String getApiKey()
    {
        return this.ini.get("api", "key");
    }

    public String getSharedSecret()
    {
        return this.ini.get("api", "shared-secret");
    }
}
