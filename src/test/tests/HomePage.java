package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class HomePage {

    private Utils utilsFunction = new Utils();

    private final String userDirProperty = System.getProperty("user.dir");
    private ChromeDriver chromeDriver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", userDirProperty + "/src/main/resources/chromedriver");
        chromeDriver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        chromeDriver.quit();
    }

    @Test(description = "Display Elements", priority = 1)
    public void contentTest() throws InterruptedException {
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.get("http://automationpractice.com/index.php");
        Assert.assertTrue(chromeDriver.getTitle().contains("My Store"));
        chromeDriver.findElementById("search_query_top").isDisplayed();
        chromeDriver.findElementByClassName("logo").isDisplayed();
        chromeDriver.findElementById("homepage-slider").isDisplayed();


        Thread.sleep(3000);
    }

    @Test(description = "Valid Search Query")
    public void searchTest() throws InterruptedException {

        chromeDriver.get("http://automationpractice.com/index.php");
        chromeDriver.findElementById("search_query_top").sendKeys("dress");
        chromeDriver.findElementByClassName("button-search").click();
        Assert.assertTrue(chromeDriver.findElementByClassName("product-name").getText().contains("dress"));


        Thread.sleep(3000);
    }

    @Test(description = "Invalid Search Query", priority = 2)
    public void searchTestnegative() throws InterruptedException {

        String searchQuery = new String("asdf");
        chromeDriver.findElementById("search_query_top").clear();
        chromeDriver.findElementById("search_query_top").sendKeys("asdf");
        chromeDriver.findElementByClassName("button-search").click();
        chromeDriver.findElementByClassName("alert-warning").isDisplayed();
        Assert.assertTrue(chromeDriver.findElementByClassName("alert-warning").getText().contains("asdf"));


        Thread.sleep(3000);
    }

    @Test(description = "Add To Cart", priority = 3)
    public void addTocart() throws InterruptedException {

        chromeDriver.get("http://automationpractice.com/index.php");
        WebElement cardWrapper = chromeDriver.findElement(By.cssSelector("#homefeatured > .ajax_block_product:nth-child(2)"));
        Actions hover = new Actions(chromeDriver);
        hover.moveToElement(cardWrapper).build().perform();

        WebElement addToCartBtn = chromeDriver.findElement(By.cssSelector("[data-id-product='2']"));
        addToCartBtn.isDisplayed();
        addToCartBtn.click();

        Thread.sleep(2000);

        WebElement cartWrapperHeader = chromeDriver.findElement(By.cssSelector(".layer_cart_product > h2"));
        cartWrapperHeader.isDisplayed();
        Assert.assertTrue(cartWrapperHeader.getText().contains("Product successfully added to your shopping cart"));


        Thread.sleep(3000);

    }

    @Test(description = "New User Newsletter", priority = 4)
    public void newUser() throws InterruptedException {

        chromeDriver.get("http://automationpractice.com/index.php");
        chromeDriver.findElementById("newsletter-input").sendKeys(utilsFunction.getRandomEmailAddress());
        chromeDriver.findElementByName("submitNewsletter").click();

        WebElement newUser = chromeDriver.findElement(By.cssSelector(".alert-success"));
        newUser.isDisplayed();
        Assert.assertTrue(newUser.getText().contains("Newsletter : You have successfully subscribed to this newsletter."));


        Thread.sleep(3000);

    }

    @Test(description = "Previous User Newsletter", priority = 5)
    public void previousUser() throws InterruptedException {

        chromeDriver.get("http://automationpractice.com/index.php");
        chromeDriver.findElementById("newsletter-input").sendKeys("nsg@dada.com");
        chromeDriver.findElementByName("submitNewsletter").click();

        WebElement previousUser = chromeDriver.findElement(By.cssSelector(".alert-danger"));
        previousUser.isDisplayed();
        Assert.assertTrue(previousUser.getText().contains("Newsletter : This email address is already registered."));


        Thread.sleep(3000);


    }

    @Test(description = "Redirect", priority = 6)
    public void redirect() throws InterruptedException {

        chromeDriver.get("http://automationpractice.com/index.php");
        //Facebook Redirect
        chromeDriver.findElementByClassName("facebook").click();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(chromeDriver.findElementByClassName("facebook").getAttribute("href")).isEqualTo("https://www.facebook.com/groups/525066904174158/");




        //Twitter

        chromeDriver.findElementByClassName("twitter").click();
        softAssertions.assertThat(chromeDriver.findElementByClassName("twitter").getAttribute("href")).isEqualTo("https://twitter.com/seleniumfrmwrk");

        //Youtube

        chromeDriver.findElementByClassName("youtube").click();
        softAssertions.assertThat(chromeDriver.findElementByClassName("youtube").getAttribute("href")).isEqualTo("https://www.youtube.com/channel/UCHl59sI3SRjQ-qPcTrgt0tA");

        //Google Plus

        chromeDriver.findElementByClassName("google-plus").click();
        softAssertions.assertThat(chromeDriver.findElementByClassName("google-plus").getAttribute("href")).isEqualTo("https://plus.google.com/111979135243110831526/posts");


        // Close all tabs

        String originalHandle = chromeDriver.getWindowHandle();
        for(String handle : chromeDriver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                chromeDriver.switchTo().window(handle);
                chromeDriver.close();
            }
        }

        chromeDriver.switchTo().window(originalHandle);


        Thread.sleep(3000);
    }


 @Test(description = "Contact Us", priority = 7)
    public void contactUs() throws InterruptedException {


        //Subject Heading

        chromeDriver.get("http://automationpractice.com/index.php");
        chromeDriver.findElementById("contact-link").click();

        Select subject = new Select(chromeDriver.findElement(By.id("id_contact")));
        subject.selectByVisibleText("Customer service");

       //Email address

        chromeDriver.findElementById("email").sendKeys("ntest12795@gmail.com");

        //Order Reference

//
        chromeDriver.findElementByName("id_order").sendKeys("113819");

        //Message

        chromeDriver.findElementById("message").sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");

        chromeDriver.findElementById("submitMessage").click();



     Thread.sleep(3000);





}





}
