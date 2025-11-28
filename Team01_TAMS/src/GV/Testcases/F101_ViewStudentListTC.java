package GV.Testcases;

import GV.Pages.*;
import Commons.InitiationTest;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;
import Commons.WebUI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BCN.Pages.BcnUserListPage;

/**
 * F1.0.1 – Xem danh sách sinh viên theo lớp học phần
 *  - Dùng để kiểm tra filter theo Mã LHP trên màn hình "Quản lý lớp học phần"
 */
public class F101_ViewStudentListTC extends InitiationTest {
    // Dùng để chờ trang tải xong (hàm waitForPageLoaded trong Helpers)
    private ValidateUIHelpers validateUIHelpers;

    // Các Page Object dùng chung
    private GVDashboardPage gvDashboardPage;
    private ClassSectionPage classSectionPage;
    private authenSupport auth;

    /**
     *  - Các bước:
     *      1. Khởi tạo driver 
     *      2. Mở URL login
     *      3. Login bằng tài khoản Giảng viên, chờ nhập mã Authentication
     *      4. Chờ Dashboard load xong
     *      5. Click menu "Lớp học phần" để mở trang ClassSection
     *      6. Chờ bảng dữ liệu LHP hiển thị
     * @throws InterruptedException 
     */
    @BeforeClass
    public void loginAsGV() throws InterruptedException {

        validateUIHelpers = new ValidateUIHelpers(driver);

        auth = new authenSupport(driver);
        gvDashboardPage = auth.loginWithGV();

        // Wait Page Loaded
        validateUIHelpers.waitForPageLoaded();

        // Tạo classSectionPage SAU khi click menu
        gvDashboardPage.clickClassSectionMenu(validateUIHelpers);

        // Quan trọng: KHỞI TẠO ĐÚNG PAGE OBJECT
        classSectionPage = new ClassSectionPage(driver, validateUIHelpers);

        // Đợi bảng hiện
        validateUIHelpers.waitForPageLoaded();
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
