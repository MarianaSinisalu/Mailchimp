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
import java.util.Scanner;

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

    private void waitSendKeys(WebDriver driver, By by, String text) {
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

        if (username.equals("tooLong")) {

            enterUser.sendKeys("randomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomStringrandomString");
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
    public void iClickSignUp()  {
        WebElement button = driver.findElement(By.id("create-account-enabled"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");
        button.click();
        button.click();


    }

    @Then("I can {string} an account")
    public void iCanAnAccount(String createAccount)  {


        if (createAccount.equals("yes")) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='margin-bottom--lv5']")));

                   String actual = driver.findElement(By.cssSelector("[class='!margin-bottom--lv3 no-transform center-on-medium ']")).getText();
                   String expected = "Check your email";


                   assertEquals(expected, actual);
               }

         if(createAccount.equalsIgnoreCase("no")){
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='invalid-error']")));
            String actual = driver.findElement(By.cssSelector("[class='invalid-error']")).getText();
            if(actual.equalsIgnoreCase("An email address must contain a single @.")) {
                String expected = "An email address must contain a single @.";
                assertEquals(expected, actual);
            }if(actual.equalsIgnoreCase("Great minds think alike - someone already has this username.")){
                String expected = "Great minds think alike - someone already has this username.";
                 assertEquals(expected, actual);
             }
            if(actual.equalsIgnoreCase("Enter a value less than 100 characters long")){
                String expected = "Enter a value less than 100 characters long";
                assertEquals(expected, actual);
            }

            System.out.println(actual);

            String actual1 = driver.getTitle();
            String expected1 = "Signup | Mailchimp";

            assertEquals(expected1, actual1);
        }

    }
}




