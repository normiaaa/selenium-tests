package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class TestSelenium {
    private final String userDirProperty = System.getProperty("user.dir");
    private ChromeDriver chromeDriver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", userDirProperty + "/src/main/resources/chromedriver");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        chromeDriver.quit();
    }

    @Test
    public void googleTest() throws InterruptedException {
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.get("https://www.google.com");

        chromeDriver.findElementByCssSelector("input[maxlength='2048']").sendKeys("Ana are mere");
        chromeDriver.findElementByCssSelector("div[class='VlcLAe'] input[type='submit'][name='btnK']").click();


        Thread.sleep(5000);
    }

    @Test
    public void maSimtNorocos() throws InterruptedException {
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.get("https://www.google.com");

        chromeDriver.findElementByCssSelector("input[maxlength='2048']").sendKeys("Ana are mere");
        chromeDriver.findElementByCssSelector("cssSelector de la ma simt norocos").click();


        Thread.sleep(5000);
    }

}
