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
import java.util.NoSuchElementException;

/**
 * A REST response takes a {@link Request} and reads the XML response, storing
 * the {@link Element root element} for later use.
 *
 * @author Johan Nilsson
 * @since  0.1.0
 */
public class Response
{
    @NonNls
    private static final String attrib_error_code = "code";

    @NonNls
    private static final String attrib_error_message = "msg";

    /**
     * XML attribute name for response status.
     *
     * @since 0.1.0
     */
    @NonNls
    public static final String ATTRIB_STATUS = "stat";

    @NonNls
    private static final String tag_error = "err";

    @NonNls
    private static final String tag_response = "rsp";

    @NonNls
    private static final String val_status_failure = "fail";

    @NonNls
    private static final String val_status_success = "ok";

    /**
     * The root element of the XML response.
     *
     * @since 0.1.0
     */
    protected final Element rootElement;

    private final Document document;

    /**
     * Creates a REST response object based off of a REST request. The XML
     * response is parsed into a {@link Element} tree, from where the caller
     * may retrieve the elements they wish.
     *
     * @param request the {@link Request}, which XML response to parse
     *
     * @throws IOException       if there were any problem with reading either
     *                           the request or the response
     * @throws DocumentException if the response couldn't be parsed into XML
     * @throws MalformedURLException
     * @throws NoSuchAlgorithmException
     *
     * @since 0.1.0
     */
    @SuppressWarnings("JavaDoc")
    public Response(final Request request)
            throws MalformedURLException, NoSuchAlgorithmException,
            IOException, DocumentException
    {
        // Make an HTTP request to get the response.
        final URLConnection connection = request.toUrl().openConnection();
        final InputStream contentStream = connection.getInputStream();

        // Try to read the XML response.
        final SAXReader reader = new SAXReader();
        this.document = reader.read(contentStream);
        this.rootElement = this.document.getRootElement();
    }

    /**
     * Determines whether this Response was successful.
     *
     * @return True if successful; false otherwise.
     *
     * @throws NoSuchElementException if {@link #ATTRIB_STATUS} couldn't be
     *                                found in the response
     *
     * @since 0.1.0
     */
    public boolean isResponseSuccess() throws NoSuchElementException
    {
        final String status = this.rootElement.attributeValue(ATTRIB_STATUS);
        if (status == null)
        {
            throw new NoSuchElementException(
                    "Failed to find a status attribute on the root element.");
        }

        return val_status_success.equals(status);
    }

    /**
     * Determines whether this Response returned a failure code.
     *
     * @return True if a failure; false otherwise.
     *
     * @throws NoSuchElementException if {@link #ATTRIB_STATUS} couldn't be
     *                                found in the response
     *
     * @since 0.1.0
     */
    public boolean isResponseFailure() throws NoSuchElementException
    {
        final String status = this.rootElement.attributeValue(ATTRIB_STATUS);
        if (status == null)
        {
            throw new NoSuchElementException(
                    "Failed to find a status attribute on the root element.");
        }

        return val_status_failure.equals(status);
    }

    /**
     * Returns the element with the specified XML tags in two levels.
     *
     * @param tag       the second level XML tag
     * @param parentTag the first level XML tag
     *
     * @return An element.
     *
     * @throws NoSuchElementException if the element could not be found.
     *
     * @since 0.1.0
     */
    public Element getElement(final String tag, final String parentTag)
            throws NoSuchElementException
    {
        Element element = this.rootElement.element(parentTag);

        element = element.element(tag);

        if (element != null)
        {
            return element;
        }

        throw new NoSuchElementException(
                "Could not find element " + parentTag + "\\" + tag);
    }

    /**
     * Returns the element with the specified XML tag directly under the root
     * node.
     *
     * @param tag the XML tag
     *
     * @return An element.
     *
     * @throws NoSuchElementException if the element could not be found.
     *
     * @since 0.1.0
     */
    public Element getElement(final String tag) throws NoSuchElementException
    {
        return this.getElement(tag, this.rootElement.getName());
    }

    /**
     * Returns this response's entire XML tree.
     *
     * @return The XML tree as a string.
     *
     * @since 0.1.0
     */
    public String toXmlString()
    {
        return this.document.asXML();
    }

    @Override
    public String toString()
    {
        return "Response{rootElement=" + this.rootElement +
                ", document=" + this.document + "}";
    }
}
