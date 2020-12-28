package nu.olivertwistor.todolisttools.rtmapi;

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
public final class ResponseTest
{
    private static Response response;

    /**
     * Loads the resource file "response.xml" and creates a Response object
     * based off that.
     *
     * @throws Exception if anything at all went wrong
     *
     * @since 0.1.0
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
     * @throws NoSuchElementException if the element couldn't be found
     *
     * @since 0.1.0
     */
    @SuppressWarnings("HardCodedStringLiteral")
    @Test
    public void When_GivenSingleValidTag_Then_ElementIsFound()
            throws NoSuchElementException
    {
        final String frobTag = "frob";
        ResponseTest.response.getElement(frobTag);
    }

    /**
     * Asserts that an element in a tag list is found in the Response.
     *
     * @throws NoSuchElementException if the element couldn't be found
     *
     * @since 0.1.0
     */
    @Test
    public void When_GivenNestedValidTags_Then_ElementIsFound()
            throws NoSuchElementException
    {
        final Deque<String> tags = new LinkedList<>();
        tags.add("lists"); //NON-NLS
        tags.add("list"); //NON-NLS
        tags.add("task"); //NON-NLS

        ResponseTest.response.getElement(tags);
    }
}
