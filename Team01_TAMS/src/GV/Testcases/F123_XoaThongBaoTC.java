package GV.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import GV.Pages.GVLoginPage;
import GV.Pages.GVDashboardPage;
import GV.Pages.NotificationArea;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.2.3 – Test chức năng Xóa thông báo trong dropdown chuông Thông báo.
 */
public class F123_XoaThongBaoTC {

    private WebDriver       driver;
    private GVLoginPage     loginPage;
    private GVDashboardPage dashboardPage;
    private NotificationArea notificationArea;

    @BeforeTest
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Ta2025/Account/Login");

        loginPage = new GVLoginPage(driver);
        dashboardPage = loginPage.loginWithGV(
                "thinh.2174802010519@vanlanguni.vn",
                "VLU31032003"
        );

        notificationArea = new NotificationArea(driver);
    }

    /**
     * TC01 – Xóa 1 thông báo cụ thể
     * Test Objective:
     *  - Xác nhận khi click icon "X" ở thông báo, thông báo này biến mất khỏi danh sách.
     */
    @Test(priority = 1)
    public void TC01_DeleteNotification() throws InterruptedException {

        // ID thông báo muốn xóa 
        String notiId = "559";

        // Bước 1: Mở Thông báo
        notificationArea.openNotificationDropdown();

        // Bước 2: Xóa thông báo có Id tương ứng
        notificationArea.deleteNotification(notiId);

        Thread.sleep(2000);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
