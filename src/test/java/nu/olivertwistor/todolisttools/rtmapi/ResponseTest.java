package nu.olivertwistor.todolisttools.rtmapi;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Unit tests for the {@link Response} class.
 *
 * @since  1.0.0
 */
public final class ResponseTest
{
    private static Response response;

    /**
     * Loads the resource file "response.xml" and creates a Response object
     * based off that.
     *
     * @throws Exception if anything went wrong.
     *
     * @since 1.0.0
     */
    @BeforeClass
    public static void setUp() throws Exception
    {
        final ClassLoader classLoader = ResponseTest.class.getClassLoader();
        final File file = new File(
                classLoader.getResource("response.xml").getFile());

        ResponseTest.response = new Response(file);
    }

    /**
     * Asserts that an element with the desired tag is found in the Response.
     *
     * @since 1.0.0
     */
    @Test
    public void When_GivenSingleValidTag_Then_ElementIsFound()
    {
        final String frobTag = "frob";
        ResponseTest.response.getElement(frobTag);
    }

    /**
     * Asserts that an element in a tag list is found in the Response.
     *
     * @since 1.0.0
     */
    @Test
    public void When_GivenNestedValidTags_Then_ElementIsFound()
    {
        final Deque<String> tags = new LinkedList<>();
        tags.add("lists");
        tags.add("list");
        tags.add("task");

        ResponseTest.response.getElement(tags);
    }
}
