package nu.olivertwistor.todolisttools.rtmapi;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NonNls;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A REST response takes a {@link RestRequest REST request} and reads the XML
 * response, storing all its elements in a map.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class RestResponse
{
    static final String success_status = "ok";
    static final String failure_status = "fail";

    @NonNls
    private final String status;
    private final Map<String, Element> elements;

    /**
     * Creates a REST response object based off of a REST request. The XML
     * response is parsed into a status ("ok" or "fail") and elements. Which
     * elements will be parsed depends on the request.
     *
     * @param request the {@link RestRequest}, which XML response to parse
     *
     * @throws IOException            if there were any problem with reading
     *                                either the request or the response
     * @throws DocumentException      if the response couldn't be parsed into
     *                                XML
     * @throws NoSuchElementException if the root element of the response isn't
     *                                "rsp"; in that case, we know something
     *                                has gone wrong
     *
     * @since 0.1.0
     */
    public RestResponse(final RestRequest request)
            throws MalformedURLException, IOException, DocumentException,
            NoSuchElementException, NoSuchAlgorithmException
    {
        this.elements = new HashMap<>();

        // Make an HTTP request to get the response.
        final URLConnection connection = request.toUrl().openConnection();
        final InputStream contentStream = connection.getInputStream();

        // Try to read the XML response.
        final SAXReader reader = new SAXReader();
        final Document document = reader.read(contentStream);

        // The root element contains the status string.
        final Element rootElement = document.getRootElement();
        this.status = rootElement.attributeValue("stat");
        if (this.status == null)
        {
            throw new NoSuchElementException("An <rsp> element with the " +
                    "attribute \"stat\" couldn't be found.");
        }

        // Store the rest of the elements at strings in a map.
        for (final Element childElement : rootElement.elements())
        {
            this.elements.put(childElement.getName(), childElement);
        }
    }

    /**
     * Gets the status of this REST response, i.e. &lt;rsp stat="xxx"&gt;.
     *
     * @return Status string, either "ok" or "fail".
     *
     * @since 0.1.0
     */
    public String getStatus()
    {
        return this.status;
    }

    /**
     * Determines whether the REST response status is "ok".
     *
     * @return boolean
     *
     * @since 0.1.0
     */
    public boolean isStatusOk()
    {
        return success_status.equals(this.status);
    }

    /**
     * Determines whether the REST response status is "ok".
     *
     * @return boolean
     *
     * @since 0.1.0
     */
    public boolean isStatusFail()
    {
        return failure_status.equals(this.status);
    }

    public String getErrorMessage()
    {
        if (!this.isStatusFail())
        {
            return "";
        }

        final StringBuilder errorMessage = new StringBuilder();
        final Element errorElement = this.elements.get("err");
        if (errorElement != null)
        {
            final String code = errorElement.attributeValue("code", "");
            final String message = errorElement.attributeValue("msg", "");

            errorMessage.append(code).append(" ").append(message);
        }

        return errorMessage.toString();
    }

    public Element getElement(final String xmlAttribute)
    {
        return this.elements.get(xmlAttribute);
    }

    @Override
    public String toString()
    {
        return "RestResponse{status='" + this.status + "', " +
                "elements=" + this.elements + '}';
    }
}
