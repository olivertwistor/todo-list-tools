package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.Session;
import nu.olivertwistor.todolisttools.models.Task;
import nu.olivertwistor.todolisttools.util.Config;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

/**
 * Unit tests for the {@link CsvAddTasksAction} class.
 *
 * @since 0.1.0
 */
public class CsvAddTasksActionTest
{
    private static Config config;
    private static Session session;

    /**
     * Sets up the foundation for all the tests in this class. A config file
     * and a session is loaded.
     *
     * @throws IOException if the config file couldn't be loaded
     *
     * @since 0.1.0
     */
    @BeforeClass
    public static void setUp() throws IOException
    {
        config = new Config("config.ini");
        session = new Session(config);
    }

    /**
     * Asserts that when a CSV file is read, a correct task list is returned.
     *
     * @throws Exception if anything at all goes wrong
     *
     * @since 0.1.0
     */
    @Test
    public void When_CsvFileIsRead_Then_CorrespondingTaskListIsReturned()
            throws Exception
    {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final File file = new File(
                classLoader.getResource("tasks.csv").getFile());

        final CsvAddTasksAction csvAddTasksAction =
                new CsvAddTasksAction(config, session);
        final List<Task> actual = csvAddTasksAction.parseCsvFile(file, ";");

        final List<Task> expected = new ArrayList<>();
        expected.add(new Task("Buy milk"));
        expected.add(new Task("Play guitar"));
        expected.add(new Task("Call my boss"));

        Assert.assertThat(actual, equalTo(expected));
    }
}
