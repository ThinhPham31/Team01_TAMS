package GV.Pages;

import Commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GVLoginPage {

    private WebDriver driver;

    // ====== LOCATORS (dựa trên site VLU) ======
    private By inputUsername   = By.id("UserName");
    private By inputPassword   = By.id("Password");
    private By btnLogin        = By.id("btnLogIn");

    // Màn hình nhập mã xác thực (OTP)
    private By inputOTP        = By.id("txtAuthenCode");    // ô nhập mã
    private By btnConfirmOTP   = By.id("btnSubmitCode");    // nút xác nhận

    // Element để nhận biết đã login thành công (sau khi nhập mã)
    // Ở đây mình dùng link menu "Lớp học phần"
    private By menuLopHocPhan  = By.id("child-classsection-index");

    public GVLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ====== HÀM HÀNH ĐỘNG ======

    /**
     * Nhập tài khoản + mật khẩu và bấm Đăng nhập
     */
    public void enterCredentialAndSubmit(String username, String password) {
        WebUI.waitForElementVisible(driver, inputUsername, 20);
        driver.findElement(inputUsername).clear();
        driver.findElement(inputUsername).sendKeys(username);

        driver.findElement(inputPassword).clear();
        driver.findElement(inputPassword).sendKeys(password);

        driver.findElement(btnLogin).click();
    }

    /**
     * Chờ user nhập mã authentic và hệ thống login thành công
     * (Bạn nhập mã trên giao diện, code chỉ đứng đợi)
     */
    public void waitForAuthenticationSuccess() {
        // đợi popup/ô OTP hiện ra (tránh trường hợp mạng chậm)
        WebUI.waitForElementVisible(driver, inputOTP, 20);
        System.out.println("➡ Vui lòng nhập mã Authentication trên website...");

        // Vòng lặp: khi nào ô OTP còn tồn tại thì vẫn chờ
        while (WebUI.checkElementExist(driver, inputOTP)) {
            WebUI.sleep(1); // nghỉ 1 giây rồi kiểm tra lại
        }

        // khi ô OTP biến mất, đợi menu Lớp học phần xuất hiện
        WebUI.waitForElementVisible(driver, menuLopHocPhan, 30);
        System.out.println("✔ Đăng nhập + xác thực thành công!");
    }

    /**
     * Hàm tiện dụng: login full flow (nhập tk/mk + chờ OTP + vào hệ thống)
     */
    public void loginFull(String username, String password) {
        enterCredentialAndSubmit(username, password);
        waitForAuthenticationSuccess();
    }
}
