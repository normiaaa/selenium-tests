package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Order {

    private Utils utilsFunction = new Utils();

    private final String userDirProperty = System.getProperty("user.dir");
    private ChromeDriver chromeDriver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", userDirProperty + "/src/main/resources/chromedriver");
        chromeDriver = new ChromeDriver();
        chromeDriver.get("http://automationpractice.com/index.php");
    }

    @AfterTest
    public void tearDown() {
        chromeDriver.quit();
    }

    @Test(description = "Sign Up", priority = 1)
    public void signUp() {


        //Sign Up


        chromeDriver.findElementByClassName("login").click();
        chromeDriver.findElementById("email_create").sendKeys(utilsFunction.getRandomEmailAddress());
        chromeDriver.findElementByClassName("icon-user").click();

        //Title

        WebDriverWait wait = new WebDriverWait(chromeDriver, 10);

        WebElement gender = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2")));
        gender.click();


        //First and Last name

        chromeDriver.findElementById("customer_firstname").sendKeys("N");
        chromeDriver.findElementById("customer_lastname").sendKeys("Test");

        //Pass

        chromeDriver.findElementById("passwd").sendKeys("testing");

        //Date of Birth

        Select day = new Select(chromeDriver.findElement(By.id("days")));
        day.selectByValue("10");

        Select month = new Select(chromeDriver.findElement(By.id("months")));
        month.selectByValue("11");

        Select year = new Select(chromeDriver.findElement(By.id("years")));
        year.selectByValue("1995");

        //Checkboxes

        chromeDriver.findElementById("newsletter").click();
        chromeDriver.findElementById("optin").click();

        //Company, Address, City, State, Zip, Phone


        chromeDriver.findElementById("company").sendKeys("Test");
        chromeDriver.findElementById("address1").sendKeys("Testing");
        chromeDriver.findElementById("city").sendKeys("Chicago");

        Select state = new Select(chromeDriver.findElement(By.id("id_state")));
        state.selectByValue("13");

        chromeDriver.findElementById("postcode").sendKeys("00000");
        chromeDriver.findElementById("phone").sendKeys("111111");

        chromeDriver.findElementById("submitAccount").click();

        //Verify

        WebElement account = chromeDriver.findElementByClassName("info-account");
        account.isDisplayed();
        Assert.assertTrue(account.getText().contains("Welcome to your account. Here you can manage all of your personal information and orders."));






    }

    @Test(description = "Order", priority = 2)
    public void order() {


        //Order

        WebDriverWait wait = new WebDriverWait(chromeDriver, 10);

        chromeDriver.get("http://automationpractice.com/index.php");
        chromeDriver.findElementByClassName("sf-with-ul").click();

        WebElement cardWrapper = chromeDriver.findElement(By.cssSelector(".product_list > .ajax_block_product:nth-child(3)"));
        Actions hover = new Actions(chromeDriver);
        hover.moveToElement(cardWrapper).build().perform();

        WebElement addToCartBtn = chromeDriver.findElement(By.cssSelector("[data-id-product='3']"));
        addToCartBtn.isDisplayed();
        addToCartBtn.click();

        WebElement mediumButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("button-medium")));

        //Proceed to checkout

        mediumButton.click();

        WebElement standardCheckout = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("standard-checkout")));

        //Summary

        standardCheckout.click();

        WebElement processAddress = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("processAddress")));

        //Address

        processAddress.click();


        WebElement cgv = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv")));

        //Shipping

        cgv.click();

        WebElement processCarrier = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("processCarrier")));

        processCarrier.click();

        WebElement bankWire = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("bankwire")));

        //Payment

        bankWire.click();

        WebElement form = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".center_column > form")));

//        Submit

        form.submit();

        WebElement order = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".box > .cheque-indent > .dark")));

        //Verify


        order.isDisplayed();
        Assert.assertTrue(order.getText().contains("Your order on My Store is complete."));








    }

}
