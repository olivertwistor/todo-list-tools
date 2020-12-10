package nu.olivertwistor.todolisttools.models;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

public class TaskTest
{
    @Test
    public void When_SmartAddTasksUsingAllSetters_Then_CorrectStringIsReturned()
    {
        // We need to create two targets, each with a permutation of the tags
        // because the SmartAddBuilder uses a Set for tags, and Sets are
        // unordered.
        final String[] targets = {
                "Buy milk https://grocerystore.com ~Tomorrow ^Saturday " +
                        "*After 1 week @Work !2 #Household #car #money " +
                        "=30 minutes //Pick low fat if available.",
                "Buy milk https://grocerystore.com ~Tomorrow ^Saturday " +
                        "*After 1 week @Work !2 #Household #money #car " +
                        "=30 minutes //Pick low fat if available."
        };

        final Task task = new Task("Buy milk");
        task.setUrl("https://grocerystore.com");
        task.setStart("Tomorrow");
        task.setDue("Saturday");
        task.setRepeat("After 1 week");
        task.setLocation("Work");
        task.setPriority("2");
        task.setList("Household");
        task.addTag("car");
        task.addTag("money");
        task.setTimeEstimate("30 minutes");
        task.setComment("Pick low fat if available.");

        Assert.assertThat(
                task.toSmartAdd(), anyOf(is(targets[0]), is(targets[1])));
    }

    @Test
    public void When_SmartAddTasksUsingSomeSetters_Then_CorrectStringIsReturned()
    {
        final String target = "Buy milk https://grocerystore.com ^4 days !2 " +
                "#Household =30 minutes";

        final Task task = new Task("Buy milk");
        task.setUrl("https://grocerystore.com");
        task.setDue("4 days");
        task.setPriority("2");
        task.setList("Household");
        task.setTimeEstimate("30 minutes");

        Assert.assertThat(task.toSmartAdd(), is(target));
    }
}
