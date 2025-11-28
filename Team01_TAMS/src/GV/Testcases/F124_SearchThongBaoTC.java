package GV.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import Commons.InitiationTest;
import GV.Pages.GVDashboardPage;
import GV.Pages.NotificationArea;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.2.4 – Test chức năng Tìm kiếm thông báo.
 */
public class F124_SearchThongBaoTC extends InitiationTest {

	private GVDashboardPage dashboardPage;
    private NotificationArea notificationArea;
    private ValidateUIHelpers validateUIHelpers;
    private authenSupport auth;
    private GVDashboardPage gvDashboardPage;

    @BeforeClass
    public void setUp() throws InterruptedException {

    	validateUIHelpers = new ValidateUIHelpers(driver);

        // Login GV (OTP bạn nhập tay)
        auth = new authenSupport(driver);
        gvDashboardPage = auth.loginWithGV();

        validateUIHelpers.waitForPageLoaded();

        notificationArea = new NotificationArea(driver, validateUIHelpers);
        
        notificationArea.openNotificationDropdown();
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

}
