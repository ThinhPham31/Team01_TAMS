package GV.Testcases;

import GV.Pages.*;
import Commons.InitiationTest;
import Helpers.ValidateUIHelpers;
import Commons.WebUI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * F1.0.1 – Xem danh sách sinh viên theo lớp học phần
 *  - Dùng để kiểm tra filter theo Mã LHP trên màn hình "Quản lý lớp học phần"
 */
public class F101_ViewStudentListTC extends InitiationTest {

    // Biến driver dùng cho riêng test này
    private WebDriver driver;

    // Dùng để chờ trang tải xong (hàm waitForPageLoaded trong Helpers)
    private ValidateUIHelpers validateUIHelpers;

    // Các Page Object dùng chung
    private GVLoginPage gvLoginPage;
    private GVDashboardPage gvDashboardPage;
    private ClassSectionPage classSectionPage;

    /**
     *  - Các bước:
     *      1. Khởi tạo driver 
     *      2. Mở URL login
     *      3. Login bằng tài khoản Giảng viên, chờ nhập mã Authentication
     *      4. Chờ Dashboard load xong
     *      5. Click menu "Lớp học phần" để mở trang ClassSection
     *      6. Chờ bảng dữ liệu LHP hiển thị
     */
    @BeforeClass
    public void setUp() {

        driver = getDriver();

        // Nếu driver chưa được khởi tạo → tự khởi tạo mới
        if (driver == null) {
            WebDriverManager.chromedriver().setup();   // tải ChromeDriver tương thích version
            driver = new ChromeDriver();               // mở instance Chrome
            driver.manage().window().maximize();       // phóng to cửa sổ
            setDriver(driver);                         // lưu lại driver vào InitiationTest
        }

        // Điều hướng tới trang login của hệ thống TA2025
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Ta2025/Account/Login");

        // Khởi tạo PageObject login
        gvLoginPage = new GVLoginPage(driver);

        // Gọi hàm loginWithGV:
        //  - Nhập email + password
        //  - Đợi user nhập mã Authentication
        //  - Sau khi login thành công → trả về GVDashboardPage
        gvDashboardPage = gvLoginPage.loginWithGV(
                "thinh.2174802010519@vanlanguni.vn",
                "VLU31032003"
        );

        // Chờ trang Dashboard load hoàn tất 
        validateUIHelpers = new ValidateUIHelpers(driver);
        validateUIHelpers.waitForPageLoaded();

        // Từ Dashboard → click menu "Lớp học phần"
        classSectionPage = gvDashboardPage.clickClassSectionMenu();

        // Đợi bảng chứa danh sách LHP hiển thị
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("dataTableBasic"))
        );
    }

    private void setDriver(WebDriver driver2) {
		// TODO Auto-generated method stub
		
	}

	/*
     * Test Objective:
     *  - Xác nhận hệ thống hiển thị đúng danh sách lớp học phần.
     *  - Khi nhập Mã LHP vào ô Tìm kiếm → bảng chỉ hiển thị dòng tương ứng.
     */
    @Test(priority = 1)
    public void TC01_ViewStudentListByClassSection() {

        // Mã LHP dùng để test 
        String maLHP = "251_71ITBS10103_0102";

        // 1. Nhập Mã LHP vào ô tìm kiếm 
        classSectionPage.searchLHP(maLHP);

        // 2. Lấy số dòng hiện có trong bảng 
        int rowCount = classSectionPage.getRowCount();

        // 3. Kiểm tra 
        Assert.assertTrue(
                rowCount > 0,
                "Không tìm thấy lớp học phần nào với mã: " + maLHP
        );
    }
}
