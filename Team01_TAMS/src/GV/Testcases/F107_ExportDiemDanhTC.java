package GV.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import Commons.InitiationTest;
import GV.Pages.ImportPopup;
import GV.Pages.GVDashboardPage;
import GV.Pages.ClassSectionPage;
import GV.Pages.KetQuaDiemDanhPopup;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.0.7 – Test chức năng Xuất file Excel kết quả điểm danh.
 */
public class F107_ExportDiemDanhTC extends InitiationTest{

 	private GVDashboardPage dashboardPage;
    private ClassSectionPage classSectionPage;
    private ImportPopup importPopup;
    private ValidateUIHelpers validateUIHelpers;
    private authenSupport auth;
    private GVDashboardPage gvDashboardPage;
    private KetQuaDiemDanhPopup diemdanhPopup;

    @BeforeClass
    public void loginAsGV() throws InterruptedException {

        // KHÔNG được khai báo lại WebDriver driver
        // driver đã được tạo từ InitiationTest.initializeTestBaseSetup()

        validateUIHelpers = new ValidateUIHelpers(driver);

        // Login GV (OTP bạn nhập tay)
        auth = new authenSupport(driver);
        gvDashboardPage = auth.loginWithGV();

        validateUIHelpers.waitForPageLoaded();

        // Mở menu Lớp học phần
        gvDashboardPage.clickClassSectionMenu(validateUIHelpers);

        // Khởi tạo ClassSectionPage
        classSectionPage = new ClassSectionPage(driver, validateUIHelpers);
        importPopup = new ImportPopup(driver, validateUIHelpers);
        diemdanhPopup = new KetQuaDiemDanhPopup(driver, validateUIHelpers);
        
        // Đợi bảng xuất hiện
        classSectionPage.waitForTableLoaded();
        
        classSectionPage.selectHocKy("251");
        classSectionPage.selectNganh("Công Nghệ Thông Tin_TH0102");
        
        diemdanhPopup.clickBtnDiemDanhPopup();
        
        
    }

    /**
     * TC01 – Xuất file Excel kết quả điểm danh
     * Test Objective:
     *  - Khi người dùng bấm nút “Xuất”, hệ thống sinh file Excel tải về.
     */
    @Test(priority = 1)
    public void TC01_ExportAttendanceResultToExcel() throws InterruptedException {
    	

        // Bước 1: Bấm nút "Xuất"
        diemdanhPopup.clickExport();

        // Bước 2: (tùy chọn) chờ vài giây để file tải xong
        Thread.sleep(3000);

    }
}
