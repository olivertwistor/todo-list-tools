package nu.olivertwistor.todolisttools.rtmapi.responses;

import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.requests.GetFrobRequest;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

public class GetFrobResponse extends Response
{
    public GetFrobResponse(final GetFrobRequest request)
            throws MalformedURLException, NoSuchAlgorithmException,
            IOException, DocumentException
    {
        super(request);
    }

    public String getFrob() throws NoSuchElementException
    {
        final Element frobElement = this.rootElement.element(TAG_FROB);
        if (frobElement == null)
        {
            throw new NoSuchElementException("Failed to find the frob tag.");
        }

        return frobElement.getText();
    }
}
