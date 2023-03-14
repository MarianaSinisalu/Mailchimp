package myStepDefs;
import io.cucumber.java.After;
import org.openqa.selenium.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyStepdefs {
    WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/mariana/Documents/Selenium/chromedriver_mac64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(2000);
    }
    @After
    public void tearDown(){
         driver.quit();
    }
    @Given("I entered {string} address")
    public void iHaveEmailAddressString(String email) {
        WebElement enterEmail = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/main/div/div/form/fieldset/div[1]/div/input"));
        enterEmail.sendKeys(email);

    }

    @Given("I have {string}")
    public void iHaveUsernameString(String username) {
        WebElement enterUser = driver.findElement(By.id("new_username"));
        enterUser.clear();
        enterUser.sendKeys(username);
    }

    @Given("I have entered {string}")
    public void iHaveEnteredPasswordString(String password) throws InterruptedException {
        WebElement enterPassword = driver.findElement(By.id("new_password"));
        enterPassword.sendKeys(password);
        Thread.sleep(3000);
    }

    @When("I click Sign Up")
    public void iClickSignUp() {
        WebElement button = driver.findElement(By.id("create-account-enabled"));
        button.click();
    }

    @Then("I can create an account")
    public void iCanCreateAnAccount() {

    }
}
