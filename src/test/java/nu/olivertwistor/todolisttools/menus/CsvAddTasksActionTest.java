package nu.olivertwistor.todolisttools.menus;

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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CsvAddTasksActionTest
{
    @Test
    public void When_CsvFileIsGiven_Then_CorrespondingMapIsReturned()
            throws Exception
    {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final File file = new File(
                classLoader.getResource("tasks.csv").getFile());

        final CsvAddTasksAction csvAddTasksAction = new CsvAddTasksAction();
        final Map<String, List<String>> actualMap =
                csvAddTasksAction.parseCsvFile(file, ";");

        final Map<String, List<String>> expectedMap = new HashMap<>();

        final List<String> nameList = new LinkedList<>();
        nameList.add("Buy milk");
        nameList.add("Play guitar");
        nameList.add("Call my boss");
        expectedMap.put("Name", nameList);

        final List<String> dueList = new LinkedList<>();
        dueList.add("Tomorrow");
        dueList.add("Today");
        dueList.add("Friday");
        expectedMap.put("Due", dueList);

        final List<String> listList = new LinkedList<>();
        listList.add("Household");
        listList.add("Fun");
        listList.add("Work");
        expectedMap.put("List", listList);

        final List<String> commentsList = new LinkedList<>();
        commentsList.add("Buy low fat if available.");
        commentsList.add("");
        commentsList.add("Apologise for spilling coffee on her blouse.");
        expectedMap.put("Comments", commentsList);

        Assert.assertThat(actualMap, equalTo(expectedMap));
    }
}
