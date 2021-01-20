package nu.olivertwistor.todolisttools.menus;

import nu.olivertwistor.todolisttools.models.Task;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the {@link CsvAddTasksAction} class.
 *
 * @since 1.0.0
 */
public final class CsvAddTasksActionTest
{
    /**
     * Asserts that when a CSV file is read, a correct task list is returned.
     *
     * @throws Exception if anything goes wrong
     *
     * @since 1.0.0
     */
    @Test
    public void When_CsvFileIsRead_Then_CorrespondingTaskListIsReturned()
            throws Exception
    {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final File file = new File(
                classLoader.getResource("tasks.csv").getFile());

        final List<Task> actual = CsvAddTasksAction.parseCsvFile(file, ";");

        final List<Task> expected = new ArrayList<>();
        expected.add(new Task("Buy milk")); //NON-NLS
        expected.add(new Task("Play guitar")); //NON-NLS
        expected.add(new Task("Call my boss")); //NON-NLS

        Assert.assertThat("Task list not parsed correctly from file.",
                actual, CoreMatchers.equalTo(expected));
    }
}
