package nu.olivertwistor.todolisttools.rtmapi;

import nu.olivertwistor.todolisttools.rtmapi.requests.GetFrob;
import nu.olivertwistor.todolisttools.util.Config;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.core.Is.is;

public class RestResponseTest
{
    @Test
    @SuppressWarnings("OverlyBroadCatchBlock")
    public void Given_GetFrobRequest_Then_ResponseStatusIsOk()
    {
        try
        {
            final Config config = new Config("dev-config.ini");
            final GetFrob getFrob = new GetFrob(config);
            System.out.println(getFrob.toUrl());
            final RestResponse response = new RestResponse(getFrob);
            System.out.println(response);
            Assert.assertThat(
                    response.getStatus(), is(RestResponse.success_status));
        }
        catch (final IOException | DocumentException | NoSuchAlgorithmException e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
