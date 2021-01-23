package nu.olivertwistor.todolisttools.rtmapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A REST response takes a {@link Request} and reads the XML response, storing
 * the {@link Element root element} for later use.
 *
 * @since 1.0.0
 */
@SuppressWarnings("StringConcatenation")
public class Response
{
    private static final @NonNls Logger LOG = LogManager.getLogger(
            Response.class);

    @NonNls
    private static final String ATTRIB_STATUS = "stat";

    @NonNls
    private static final String VAL_STATUS_SUCCESS = "ok";

    /**
     * The root element of the XML response.
     *
     * @since 1.0.0
     */
    private final Element rootElement;

    private final Document document;

    /**
     * Creates a REST response object based off of a REST request. The XML
     * response is parsed into a {@link Element} tree, from where the caller
     * may retrieve the elements they wish.
     *
     * @param contentStream an InputStream with the XML response to parse
     *
     * @throws IOException if communication with Remember The Milk failed.
     *
     * @since 1.0.0
     */
    protected Response(final InputStream contentStream) throws IOException
    {
        LOG.trace("Entering Response(InputStream)...");

        try
        {
            final SAXReader reader = new SAXReader();
            this.document = reader.read(contentStream);
            this.rootElement = this.document.getRootElement();

            LOG.debug("Loaded XML document: {}", this.document.asXML());
        }
        catch (final DocumentException e)
        {
            LOG.error("Failed to load XML document.", e);
            throw new IOException(e);
        }
    }

    /**
     * Creates a REST response object based off of an XML file. The XML is
     * parsed into a {@link Element} tree, from where the caller may retrieve
     * the elements they wish.
     *
     * @param file a file containing the XML response to parse
     *
     * @throws IOException if communication with Remember The Milk failed.
     *
     * @since 1.0.0
     */
    Response(final File file) throws IOException
    {
        LOG.trace("Entering Response(File)...");

        try
        {
            final SAXReader reader = new SAXReader();
            this.document = reader.read(file);
            this.rootElement = this.document.getRootElement();

            LOG.debug("Loaded XML document: {}", this.document.asXML());
        }
        catch (final DocumentException e)
        {
            LOG.error("Failed to load XML document.", e);
            throw new IOException(e);
        }
    }

    /**
     * Creates a REST response object based off of a REST request. The XML
     * response is parsed into a {@link Element} tree, from where the caller
     * may retrieve the elements they wish.
     *
     * @param request the {@link Request}, which XML response to parse
     *
     * @return The created Response object.
     *
     * @throws IOException if communication with Remember The Milk failed.
     *
     * @since 1.0.0
     */
    public static Response createResponse(final Request request)
            throws IOException
    {
        LOG.trace("Entering createResponse(Request)...");

        // Make an HTTP request to get the response.
        final URL url;
        try
        {
            url = request.toUrl();
        }
        catch (final MalformedURLException e)
        {
            LOG.error("Failed to form a URL out of the Request.", e);
            throw new IOException(e);
        }

        final URLConnection connection = url.openConnection();
        final InputStream contentStream = connection.getInputStream();

        return new Response(contentStream);
    }

    /**
     * Determines whether this Response was successful.
     *
     * @return True if successful; false otherwise.
     *
     * @throws NullPointerException if the response doesn't include a status
     *                              attribute.
     *
     * @since 1.0.0
     */
    public final boolean isResponseSuccess()
    {
        LOG.trace("Entering isResponseSuccess()...");

        final String status = this.rootElement.attributeValue(ATTRIB_STATUS);
        Objects.requireNonNull(status,
                "Failed to find a status attribute on the root element.");

        return VAL_STATUS_SUCCESS.equals(status);
    }

    /**
     * Searches for an element in the XML tree based on a tag name in a tag
     * tree.
     *
     * @param base the base element for which to start search
     * @param tags a list of tags describing the tree in which to search, based
     *             in the base; it's the last tag in the list that is the
     *             desired tag
     *
     * @return The element that is found after starting at the base and
     *         traversing the tag tree.
     *
     * @since 1.0.0
     */
    private Element getElement(final Element base, final Deque<String> tags)
    {
        LOG.trace("Entering getElement(Element, Deque<String>)...");

        // Take a look at the first tag in the list. We also remove it from the
        // list, to prepare for the recursion further on.
        final String firstTag = tags.pop();
        final Element element = base.element(firstTag);
        LOG.debug("Found intermediate element: {}", element);

        // If the remaining list is empty, it means that we have reached our
        // final level in the hierarchy and can stop the recursion.
        if (tags.isEmpty())
        {
            LOG.debug("Found the target element: {}", element);
            return element;
        }

        // We have more levels in the hierarchy, so let's do one more recursive
        // step. We use the previously fetched Element as our new base.
        return this.getElement(element, tags);
    }

    /**
     * Searches for an element in the XML tree based on a tag name in a tag
     * tree.
     *
     * @param base the base element for which to start search
     * @param tags a list of tags describing the tree in which to search, based
     *             in the base; it's the last tag in the list that is the
     *             desired tag
     *
     * @return The element that is found after starting at the base and
     *         traversing the tag tree.
     *
     * @since 1.0.0
     */
    private Element getElement(final String base, final Deque<String> tags)
    {
        LOG.trace("Entering getElement(String, Deque<String>)...");

        final Element element = this.rootElement.element(base);
        LOG.debug("Found intermediate element: {}", element);

        return this.getElement(element, tags);
    }

    /**
     * Gets an element in the XML tree based on a parent tag and a child tag.
     *
     * @param base the parent tag; the tag directly above the desired tag in
     *             the XML tree
     * @param tag  the desired (child) tag
     *
     * @return The element identified by the parent tag and the child tag.
     *
     * @since 1.0.0
     */
    protected final Element getElement(final String base, final String tag)
    {
        LOG.trace("Entering getElement(String, String)...");

        final Deque<String> tags = new LinkedList<>();
        tags.add(tag);

        return this.getElement(base, tags);
    }

    /**
     * Searches for an element in the XML tree based on a tag name in a tag
     * tree.
     *
     * @param tags a list of tags describing the tree in which to search, based
     *             in the root node; it's the last tag in the list that is the
     *             desired tag
     *
     * @return The element that is found after starting at the root node and
     *         traversing the tag tree.
     *
     * @since 1.0.0
     */
    final Element getElement(final Deque<String> tags)
    {
        LOG.trace("Entering getElement(Deque<String>)...");

        return this.getElement(this.rootElement, tags);
    }

    /**
     * Gets an element based on a tag name directly under the root name.
     *
     * @param tag the desired tag
     *
     * @return The element identified by the desired tag.
     *
     * @throws NoSuchElementException if no element identified by the desired
     *                                tag couldn't be found.
     *
     * @since 1.0.0
     */
    public final Element getElement(final String tag)
    {
        LOG.trace("Entering getElement(String)...");

        final Element element = this.rootElement.element(tag);
        if (element == null)
        {
            throw new NoSuchElementException("Failed to find " + tag);
        }

        LOG.debug("Found element: {}", element);
        return element;
    }

    @Override
    public String toString()
    {
        return "Response{" +
                "rootElement=" + this.rootElement +
                ", document=" + this.document +
                '}';
    }
}
