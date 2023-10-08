import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {


    private By loginFieldLocator = By.id("login_field");
    private By passwordFieldLocator = By.id("password");
    private By submitLoginButton = By.xpath("//input[value=\"Sign in\"]");
    private By submitLoginButtonLocator = By.xpath("//input[@value=\"Sign in\"]");
    private By logoLocator = By.className("header-logo");
    private By errorTextLocator = By.xpath("//div[contains(text(), 'Incorrect username or password.')]");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public WebElement getLogo() {
        return driver.findElement(logoLocator);
    }
    public MainPage loginSuccessful(String login, String password) {
        driver.findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitLoginButtonLocator));
        driver.findElement(submitLoginButtonLocator).click();
        return new MainPage(driver);
    }
    public LoginPage negativeLogin(String login, String password) {
        driver.findElement(loginFieldLocator).sendKeys(login);
        driver.findElement(passwordFieldLocator).sendKeys(password);
        driver.findElement(submitLoginButtonLocator).click();
        return this;
    }
    public LoginPage validateErrorMessage(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, driver.findElement(errorTextLocator).getText(),
                "Actual error text doesn't equal to expected message");
        return this;
    }
    public LoginPage validateAuthFieldsAreDisplayed() {
        Assertions.assertTrue(driver.findElement(loginFieldLocator).isDisplayed());
        Assertions.assertTrue(driver.findElement(loginFieldLocator).isDisplayed());
        Assertions.assertTrue(driver.findElement(loginFieldLocator).isDisplayed());
        return this;
    }
}