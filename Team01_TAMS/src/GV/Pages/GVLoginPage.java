package GV.Pages;

import Commons.WebUI;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * GVLoginPage:
 *  - Đại diện cho màn hình Đăng nhập (Account/Login) dành cho Giảng viên
 *  - Nhập tài khoản, mật khẩu, xử lý màn hình nhập mã Authentication (OTP)
 */
public class GVLoginPage {

    private WebDriver driver; // WebDriver dùng chung trong page

    // Constructor nhận driver từ test truyền vào
    public GVLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ================= LOCATORS TRANG LOGIN =================

    // Ô nhập Username (Email VLU)
    private By txtUsername   = By.id("UserName");

    // Ô nhập Password
    private By txtPassword   = By.id("Password");

    // Nút "Đăng nhập"
    private By btnLogin      = By.id("btnLogIn");

    // ================= LOCATORS TRANG AUTHENTICATION =================

    // Ô nhập mã Authentication (OTP) – hiển thị sau khi bấm Đăng nhập
    private By txtAuthCode   = By.id("txtAuthenCode");

    // Nút xác nhận mã Authentication
    private By btnSubmitCode = By.id("btnSubmitCode");

    // Một element trên Dashboard sau login để xác nhận đăng nhập thành công
    // Ở đây dùng label hiển thị chữ "Giảng viên" trong header
    private By lblUserRole   = By.xpath("//span[contains(.,'Giảng viên')]");

    /**
     * Hàm loginWithGV:
     *  - Nhập email + password
     *  - Bấm Đăng nhập
     *  - Chờ người dùng tự nhập mã Authentication trên trình duyệt
     *  - Khi mã hợp lệ → vào Dashboard → trả về GVDashboardPage cho test sử dụng
     */
    public GVDashboardPage loginWithGV(String username, String password) {

        // Đợi ô Username hiển thị → tránh lỗi Element not interactable
        WebUI.waitForElementVisible(driver, txtUsername, 20);

        // Nhập Username
        driver.findElement(txtUsername).clear();
        driver.findElement(txtUsername).sendKeys(username);

        // Nhập Password
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);

        // Click nút "Đăng nhập"
        driver.findElement(btnLogin).click();

        // Sau khi click login, hệ thống chuyển sang màn hình nhập mã Authentication
        // Ta đợi ô txtAuthCode hiển thị
        WebUI.waitForElementVisible(driver, txtAuthCode, 30);
        System.out.println("➡ Vui lòng nhập mã Authentication trên web rồi bấm Xác nhận...");

        // Dùng WebDriverWait chờ đến khi ô nhập mã Authentication biến mất
        // (tức là người dùng đã nhập mã đúng và hệ thống chuyển trang)
        WebDriverWait wait = new WebDriverWait(driver, 300); // timeout tối đa 5 phút
        wait.until(ExpectedConditions.invisibilityOfElementLocated(txtAuthCode));

        // Khi ô Authentication biến mất, ta chờ thêm label "Giảng viên" hiển thị
        // để chắc chắn đang ở Dashboard của giảng viên
        WebUI.waitForElementVisible(driver, lblUserRole, 30);
        System.out.println("✔ Đăng nhập Giảng viên & xác thực OTP thành công!");

        // Trả về object GVDashboardPage cho các testcase dùng chung
        return new GVDashboardPage(driver);
    }
}
