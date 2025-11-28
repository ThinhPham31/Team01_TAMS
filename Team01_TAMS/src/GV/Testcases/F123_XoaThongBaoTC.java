package GV.Testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
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
 * F1.2.3 – Test chức năng Xóa thông báo trong dropdown chuông Thông báo.
 */
public class F123_XoaThongBaoTC extends InitiationTest {

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
     * TC01 – Xóa 1 thông báo cụ thể
     * Test Objective:
     *  - Xác nhận khi click icon "X" ở thông báo, thông báo này biến mất khỏi danh sách.
     */
    @Test(priority = 1)
    public void TC01_DeleteNotification() throws InterruptedException {

        // Tìm thông báo theo từ khóa (tùy bạn)
        notificationArea.search("251");

        // Lấy ID thông báo đầu tiên
        String notiId = notificationArea.getFirstNotificationId();

        // Xóa theo ID động
        notificationArea.clickDeleteById(notiId);

        // Xác nhận xoá
        notificationArea.confirmDelete();

        Thread.sleep(2000);
    }
}
