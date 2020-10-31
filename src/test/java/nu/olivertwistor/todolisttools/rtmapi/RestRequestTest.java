package nu.olivertwistor.todolisttools.rtmapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestRequestTest
{
    @Test
    public void When_AddingParameters_Then_UrlWillShowThemInOrder()
    {
        final RestRequest request = new RestRequest(
                "https://api.rememberthemilk.com/services/rest/");
        request.addParameter("api_key", "abc123");
        request.addParameter("method", "rtm.test");
        request.addParameter("contacts", "true");
        final String url = request.toUrl();

        assertEquals(
                url,
                "https://api.rememberthemilk.com/services/rest/" +
                        "?api_key=abc123&contacts=true&method=rtm.test");
    }
}
