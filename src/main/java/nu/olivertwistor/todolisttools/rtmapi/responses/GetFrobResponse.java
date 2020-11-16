package nu.olivertwistor.todolisttools.rtmapi.responses;

import nu.olivertwistor.todolisttools.rtmapi.Request;
import nu.olivertwistor.todolisttools.rtmapi.Response;
import nu.olivertwistor.todolisttools.rtmapi.requests.GetFrobRequest;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

/**
 * A GetFrobResponse takes a {@link GetFrobRequest} and reads the XML response,
 * storing the {@link Element root element} for later use.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class GetFrobResponse extends Response
{
    /**
     * Creates a GetFrobResponse object based off of a GetFrobRequest. The XML
     * response is parsed into a {@link Element} tree, from where the caller
     * may retrieve the elements they wish.
     *
     * @param request the {@link Request}, which XML response to parse
     *
     * @throws IOException       if there were any problem with reading either
     *                           the request or the response
     * @throws DocumentException if the response couldn't be parsed into XML
     * @throws MalformedURLException
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    public GetFrobResponse(final GetFrobRequest request)
            throws MalformedURLException, NoSuchAlgorithmException,
            IOException, DocumentException
    {
        super(request);
    }

    /**
     * Gets the FROB string.
     *
     * @return The FROB string.
     *
     * @throws NoSuchElementException if the FROB couldn't be found in the
     *                                response.
     *
     * @since 0.1.0
     */
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
