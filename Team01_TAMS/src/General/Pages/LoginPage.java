package General.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import Commons.WebUI;

public class LoginPage {
    private WebDriver driver;

    private By ADVANCED_BUTTON = By.id("details-button");
    private By PROCEED_LINK = By.id("proceed-link");
    private By MAIN_LOGIN_BUTTON = By.id("submitlogin");

    private By USERNAME_INPUT = By.id("i0116");
    private By NEXT_BUTTON = By.xpath("//input[@id='idSIButton9' and @value='Next']");
    private By PASSWORD_INPUT = By.id("i0118");
    private By SIGNIN_BUTTON = By.xpath("//input[@id='idSIButton9' and @value='Sign in']");
    private By YES_BUTTON = By.xpath("//input[@id='idSIButton9' and @value='Yes']");
    private By REMEMBER_CHECKBOX = By.id("KmsiCheckboxField");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Ta2025/");
        handleSSLIfAny();
        WebUI.waitForElementClickable(driver, MAIN_LOGIN_BUTTON).click();
    }

    public void handleSSLIfAny() {
        try {
            WebUI.waitForElementClickable(driver, ADVANCED_BUTTON).click();
            WebUI.waitForElementClickable(driver, PROCEED_LINK).click();
        } catch (TimeoutException ignore) {}
    }

    public void enterEmail(String email) {
        WebUI.waitForElementClickable(driver, USERNAME_INPUT).sendKeys(email);
        WebUI.waitForElementClickable(driver, NEXT_BUTTON).click();
    }

    public void enterPassword(String password) {
        WebUI.waitForElementClickable(driver, PASSWORD_INPUT).sendKeys(password);
        WebUI.waitForElementClickable(driver, SIGNIN_BUTTON).click();
    }
    

    public void uncheckRememberIfVisible() {
        try {
            WebUI.waitForElementClickable(driver, REMEMBER_CHECKBOX).click();
        } catch (TimeoutException ignore) {}
    }

    public void clickYes() {
        try {
            WebUI.waitForElementClickable(driver, YES_BUTTON).click();
        } catch (TimeoutException ignore) {}
    }

    public void clickKeepMeSignIn() {
        uncheckRememberIfVisible();
        clickYes();
    }
    
    
}
