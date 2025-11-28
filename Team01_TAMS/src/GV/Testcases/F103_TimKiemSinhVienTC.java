package GV.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import Commons.InitiationTest;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;
import GV.Pages.GVDashboardPage;
import GV.Pages.ClassSectionPage;
import GV.Pages.DiemDanhPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.0.3 – Test chức năng Tìm kiếm sinh viên trong popup điểm danh.
 */
public class F103_TimKiemSinhVienTC extends InitiationTest {
	private GVDashboardPage gvDashboardPage;
    private ClassSectionPage classSectionPage;
    private DiemDanhPopup diemDanhPopup;
    private ValidateUIHelpers validateUIHelpers;
    private authenSupport auth;

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

        classSectionPage.waitForTableLoaded();
    }

    /**
     * TC01 – Tìm kiếm theo Họ tên
     * Test Objective:
     *  - Khi nhập từ khóa là một phần Họ tên, danh sách sinh viên
     *    trong popup điểm danh phải được filter tương ứng.
     */
    @Test(priority = 1)
    public void TC01_SearchStudentByName() {
    	
    	String maLHP = "251_71ITBS10103_0102";

        // 1. Chọn filter
        classSectionPage.selectHocKy("251");
        classSectionPage.selectNganh("Công Nghệ Thông Tin_TH0102");

        // 2. Tìm đúng lớp
        classSectionPage.searchLHP(maLHP);

        // 3. Mở popup điểm danh
        classSectionPage.openDiemDanhPopup(maLHP);

        // 4. Khởi tạo popup đúng cách
        diemDanhPopup = new DiemDanhPopup(driver, validateUIHelpers);

        // 5. Chờ popup load
        diemDanhPopup.waitLoaded();

        // Từ khóa tìm kiếm (một phần họ tên)
        String keyword = "Nguyễn";

        // Bước 1: Nhập từ khóa vào ô "Tìm kiếm"
        diemDanhPopup.searchStudent(keyword);

        // Bước 2: Bạn có thể bổ sung assert:
        //   - Số dòng kết quả > 0
        //   - Mỗi dòng hiển thị chứa từ "Nguyễn" trong Họ tên
        // (Cài đặt chi tiết nằm trong DiemDanhPopup)
    }
}
