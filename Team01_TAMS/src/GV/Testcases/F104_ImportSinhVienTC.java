package GV.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import Commons.InitiationTest;
import GV.Pages.GVDashboardPage;
import GV.Pages.ClassSectionPage;
import GV.Pages.DiemDanhPopup;
import GV.Pages.ImportPopup;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.0.4 – Test chức năng Import danh sách sinh viên từ file Excel.
 */
public class F104_ImportSinhVienTC extends InitiationTest {
    private GVDashboardPage  dashboardPage;
    private ClassSectionPage classSectionPage;
    private ImportPopup importPopup;
    private DiemDanhPopup diemDanhPopup;
    private ValidateUIHelpers validateUIHelpers;
    private authenSupport auth;
    private GVDashboardPage gvDashboardPage;

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
        
        // Đợi bảng xuất hiện
        classSectionPage.waitForTableLoaded();
        
        classSectionPage.selectHocKy("251");
        classSectionPage.selectNganh("Công Nghệ Thông Tin_TH0102");
    }

    /**
     * TC01 – Import thành công với file Excel hợp lệ
     * Test Objective:
     *  - Xác nhận khi chọn đúng file Excel mẫu, hệ thống import thành công
     *    và hiển thị thông báo “Đã import danh sách sinh viên”.
     */
    @Test(priority = 1)
    public void TC01_ImportStudentListSuccessfully() throws InterruptedException {
    	
    	// Đường dẫn file
        String filePath = "D:\\251-KiemThuTuDong-01\\DS.xlsx";

        // Mở popup ImportSV
        importPopup.clickImportSV();

        // Upload file
        importPopup.uploadFile(filePath);

        // Bấm Import
        importPopup.clickImport();
        
        importPopup.clickButtonCapNhat();

        Thread.sleep(3000);
    }
}
