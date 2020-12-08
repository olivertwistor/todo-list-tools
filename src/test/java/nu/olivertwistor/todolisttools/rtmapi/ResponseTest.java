package nu.olivertwistor.todolisttools.rtmapi;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Unit tests for the {@link Response} class.
 *
 * @since  0.1.0
 */
public class ResponseTest
{
    private static Response response;

    @BeforeClass
    public static void setUp() throws DocumentException
    {
        final ClassLoader classLoader = ResponseTest.class.getClassLoader();
        final File file = new File(
                classLoader.getResource("response.xml").getFile());

        response = new Response(file);
    }

    @Test
    public void When_GivenSingleValidTag_Then_ElementIsFound()
            throws NoSuchElementException
    {
        final String frobTag = "frob";
        response.getElement(frobTag);
    }

    @Test
    public void When_GivenNestedValidTags_Then_ElementIsFound()
            throws NoSuchElementException
    {
        final Deque<String> tags = new LinkedList<>();
        tags.add("lists");
        tags.add("list");
        tags.add("task");

        response.getElement(tags);
    }
}
