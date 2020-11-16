package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.rtmapi.requests.GetFrobRequest;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.core.Is.is;

public class ResponseTest
{
    @Test
    @SuppressWarnings("OverlyBroadCatchBlock")
    public void Given_GetFrobRequest_Then_ResponseStatusIsOk()
    {
        try
        {
            final Config config = new Config("dev-config.ini");
            final GetFrobRequest getFrob = new GetFrobRequest(config);
            System.out.println(getFrob.toUrl());
            final Response response = new Response(getFrob);
            System.out.println(response);
            Assert.assertThat(response.isResponseSuccess(), is(true));
        }
        catch (final IOException | DocumentException |
                NoSuchAlgorithmException e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
