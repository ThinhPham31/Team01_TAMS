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
import GV.Pages.DiemDanhPopup;
import GV.Pages.KetQuaDiemDanhPopup;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.0.6 – Test chức năng Xem kết quả điểm danh lớp học phần.
 */
public class F106_KetQuaDiemDanhTC extends InitiationTest {

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
     * TC01 – Kiểm tra bảng kết quả điểm danh hiển thị dữ liệu
     * Test Objective:
     *  - Xác nhận popup kết quả điểm danh có danh sách sinh viên
     *    và các cột ngày học.
     */
    @Test(priority = 1)
    public void TC01_ViewAttendanceResult() {

        // Bước 1: Lấy số dòng sinh viên
        int rowCount = diemdanhPopup.getStudentRowCount();
        
        // IN RA SỐ LƯỢNG SINH VIÊN
        System.out.println("Số sinh viên trong lớp học phần: " + rowCount);

        // Bước 2: Lấy số cột buổi học (các cột ngày)
        int sessionColCount = diemdanhPopup.getSessionColumnCount();
        
        

        // Bước 3: Tùy ý assert – ví dụ phải có ít nhất 1 sinh viên & 1 buổi học
        if (rowCount == 0 || sessionColCount == 0) {
            throw new AssertionError("Bảng kết quả điểm danh không có dữ liệu!");
        }
    }

}
