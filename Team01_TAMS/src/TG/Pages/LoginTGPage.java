package TG.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTGPage {
	
	WebDriver driver;
	
	// ====== Các phần tử trên trang ======
    private By advanceButton = By.id("details-button");
    private By proceedLink = By.id("proceed-link");
    private By submitLogin = By.id("submitlogin");
    private By emailField = By.id("i0116");
    private By nextButton = By.id("idSIButton9");
    private By passwordField = By.id("i0118");
    private By keepMeSignInCheckbox = By.xpath("//*[@id='KmsiCheckboxField']");
    private By signInButton = By.id("idSIButton9");
    private By NoButton = By.id("idBtn_Back");

    // ====== Constructor ======
    public LoginTGPage(WebDriver driver) {
        this.driver = driver;
    }

    // ====== Các hàm thao tác ======
    public void openLoginPage() throws InterruptedException {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Ta2025/");
        Thread.sleep(2000);
        driver.findElement(advanceButton).click();
        Thread.sleep(1000);
        driver.findElement(proceedLink).click();
        Thread.sleep(1000);
        driver.findElement(submitLogin).click();
        Thread.sleep(3000);
    }

    public void enterEmail(String email) throws InterruptedException {
        driver.findElement(emailField).sendKeys(email);
        Thread.sleep(1000);
        driver.findElement(nextButton).click();
        Thread.sleep(2000);
    }

    public void enterPassword(String password) throws InterruptedException {
        driver.findElement(passwordField).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(signInButton).click();
        Thread.sleep(12000);
    }

    public void clickKeepMeSignIn() throws InterruptedException {
        driver.findElement(keepMeSignInCheckbox).click();
        Thread.sleep(500);
        driver.findElement(NoButton).click();
        Thread.sleep(500);
    }
}
