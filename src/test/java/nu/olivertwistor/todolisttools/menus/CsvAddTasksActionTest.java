package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.Session;
import nu.olivertwistor.todolisttools.models.Task;
import nu.olivertwistor.todolisttools.util.Config;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CsvAddTasksActionTest
{
    private static Config config;
    private static Session session;

    @BeforeClass
    public static void setUp() throws IOException
    {
        config = new Config("config.ini");
        session = new Session(config);
    }
    @Test
    public void When_CsvFileIsGiven_Then_CorrespondingTaskListIsReturned()
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
