package nu.olivertwistor.todolisttools.util;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

public class SmartAddBuilderTest
{
    @Test
    public void When_BuildingSmartAddUsingAllSetters_Then_CorrectStringIsReturned()
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

        final SmartAddBuilder builder = new SmartAddBuilder("Buy milk")
                .setUrl("https://grocerystore.com")
                .setStart("Tomorrow")
                .setDue("Saturday")
                .setRepeat("After 1 week")
                .setLocation("Work")
                .setPriority("2")
                .setList("Household")
                .addTag("car")
                .addTag("money")
                .setTimeEstimate("30 minutes")
                .setComment("Pick low fat if available.");

        Assert.assertThat(
                builder.build(), anyOf(is(targets[0]), is(targets[1])));
    }

    @Test
    public void When_BuildingSmartAddUsingSomeSetters_Then_CorrectStringIsReturned()
    {
        final String target = "Buy milk https://grocerystore.com ^4 days !2 " +
                "#Household =30 minutes";

        final SmartAddBuilder builder = new SmartAddBuilder("Buy milk")
                .setUrl("https://grocerystore.com")
                .setDue("4 days")
                .setPriority("2")
                .setList("Household")
                .setTimeEstimate("30 minutes");

        Assert.assertThat(builder.build(), is(target));
    }
}
