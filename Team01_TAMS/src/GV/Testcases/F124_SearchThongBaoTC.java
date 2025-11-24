package GV.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import GV.Pages.GVLoginPage;
import GV.Pages.GVDashboardPage;
import GV.Pages.NotificationArea;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.2.4 – Test chức năng Tìm kiếm thông báo.
 */
public class F124_SearchThongBaoTC {

    private WebDriver        driver;
    private GVLoginPage      loginPage;
    private GVDashboardPage  dashboardPage;
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
     * TC01 – Tìm kiếm theo từ khóa
     * Test Objective:
     *  - Khi nhập từ khóa vào ô tìm kiếm, danh sách thông báo phải được lọc
     *    để chỉ hiển thị các thông báo liên quan.
     */
    @Test(priority = 1)
    public void TC01_SearchNotificationByKeyword() throws InterruptedException {

        // Từ khóa cần tìm – ví dụ "trợ giảng"
        String keyword = "trợ giảng";

        // Bước 1: Mở Thông báo
        notificationArea.openNotificationDropdown();

        // Bước 2: Nhập từ khóa vào ô tìm kiếm và thực hiện search
        notificationArea.search(keyword);

        Thread.sleep(2000);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
