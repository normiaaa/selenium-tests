package workshopExamples;

import org.testng.annotations.*;

public class TestNgAnnotations {

    @BeforeMethod
    public void setup() {
        System.out.println(" Before method execution");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println(" After method execution");
    }

    @BeforeClass
    public void setupClass() {
        System.out.println(" Before class execution");
    }

    @AfterClass
    public void ciao() {
        System.out.println(" After class execution");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("after test");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before test");
    }

    @Test
    public void firstTest() {
        System.out.println("This is the first test");
        System.out.println(returnFirstString());

    }

    @Test
    public void secondTest() {
        System.out.println("This is the second test");

    }

    public String returnFirstString() {
        return "String";
    }
}
