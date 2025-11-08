package BCN.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    private WebDriver driver;
    private WebDriverWait wait;

    // ====== HTTPS warning ======
    private By advanceButton = By.id("details-button");   // Nâng cao
    private By proceedLink   = By.id("proceed-link");      // Tiếp tục truy cập…

    // ====== nút "Đăng nhập bằng Email VLU" ======
    private By emailLoginBtn = By.id("submitlogin");

    // ====== Microsoft login ======
    private By emailField    = By.id("i0116");
    private By nextButton    = By.id("idSIButton9");
    private By passwordField = By.id("i0118");
    private By signInButton  = By.id("idSIButton9");
    private By noButton      = By.id("idBtn_Back");        // “Không” (stay signed in)

    public Login(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // 1. Mở site + vượt HTTPS + bấm "Đăng nhập bằng Email VLU"
    public void openLoginPage() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Ta2025/");

        // cố gắng vượt qua màn HTTPS (nếu có)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(advanceButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(proceedLink)).click();
        } catch (Exception e) {
            // nếu không thấy thì coi như đã qua, bỏ qua lỗi
        }

        // bấm nút "Đăng nhập bằng Email VLU"
        wait.until(ExpectedConditions.elementToBeClickable(emailLoginBtn)).click();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(nextButton).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }

    public void clickKeepMeSignIn() {
        // màn "Stay signed in?"
        wait.until(ExpectedConditions.elementToBeClickable(noButton)).click();
    }

    // Hàm login gói gọn
    public void login(String email, String password) {
        openLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickKeepMeSignIn();
    }
}
