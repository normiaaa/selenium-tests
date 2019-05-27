package workshopExamples;

import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

public class AssertExamples {
    public static void main(String[] args) {
/**
 * TestNg Examples
 */
        Assert.assertTrue(true);
        Assert.assertEquals("Unu", "Unu");

        /**
         * JUnit Examples
         */
        String wire = "blue";
        String bluewire = "bluewireSowfware Solutions";
        Assert.assertFalse(bluewire.contains(wire), "The " + bluewire + " does not contain " + wire);
        Assert.assertTrue(bluewire.contains(wire), "The " + bluewire + " does not contain " + wire);


        /**
         * AssertJ example
         */
        String one = "Bojte1";
        String two = "Bojte2";

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat("bojte2").isEqualTo("bojte3");
        softAssertions.assertThat("bojte").isEqualTo("bojte");
        softAssertions.assertThat(one).overridingErrorMessage("Expected the first name to be: %s but it was: %s", one, two).isEqualTo(two);
        softAssertions.assertThat("bojte2").isEqualTo("bojte2");
        softAssertions.assertThat("bojte3").isEqualTo("bojte4");

        softAssertions.assertAll();
    }
}
