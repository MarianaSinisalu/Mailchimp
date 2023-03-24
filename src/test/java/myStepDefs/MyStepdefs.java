package myStepDefs;
import io.cucumber.java.After;
import org.openqa.selenium.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    WebDriver driver;
    private WebDriverWait wait;

    @After
    public void tearDown() {

        driver.quit();
    }

    @Given("I use {string}")
    public void iUse(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/Users/mariana/Documents/Selenium/chromedriver_mac64/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.get("https://login.mailchimp.com/signup/");
            driver.manage().window().maximize();
        } else if (browser.equals("safari")) {

            System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
            driver = new SafariDriver();
            driver.get("https://login.mailchimp.com/signup/");
            driver.manage().window().maximize();
        }
    }

    @When("I entered {string} address")
    public void iHaveEmailAddressString(String email) {
        waitSendKeys(driver, By.id(("email")), email);

    }
    private void waitSendKeys(WebDriver driver,By by, String text) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(text);
    }
    @When("I have {string}")
    public void iHaveNewUsername(String username) {
        WebElement enterUser = driver.findElement(By.cssSelector("#new_username"));
        enterUser.click();
        enterUser.clear();
        if (username.equals("newName")) {
            Random random = new Random();
            int randomNumber = random.nextInt(10000);
            enterUser.sendKeys(randomNumber + "marianajunx" + randomNumber);

        }

        if (username.equals("long")) {

            enterUser.sendKeys("randomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomString");
        }

        if (username.equals("taken")) {
            enterUser.sendKeys("maria");
        }
    }


    @When("I have entered {string}")
    public void iHaveEnteredPasswordString(String password) {
        WebElement enterPassword = driver.findElement(By.id("new_password"));
        enterPassword.sendKeys(password);
        

        

    }

    @When("I click Sign Up")
    public void iClickSignUp() {
        WebElement button = driver.findElement(By.id("create-account-enabled"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
        button.click();

    }

    @Then("I can {string} an account")
    public void iCanAnAccount(String createAccount)   {

         if (createAccount.equals("yes")) {
             
               if(driver.findElement(By.xpath("/html/body/div/div/div[2]")).isDisplayed()) {

                       String actual = driver.getTitle();
                       String expected = "Signup | Mailchimp";

                       assertEquals(expected, actual);
               } else {

                   wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                   wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='!margin-bottom--lv3 no-transform center-on-medium']")));

                   String actual = driver.findElement(By.cssSelector("[class='!margin-bottom--lv3 no-transform center-on-medium'")).getText();
                   String expected = "Check your email";


                   assertEquals(expected, actual);
               }
        }else if(createAccount.equalsIgnoreCase("no")){
            String actual = driver.getTitle();
            String expected = "Signup | Mailchimp";

            assertEquals(expected, actual);
        }



    }
}


