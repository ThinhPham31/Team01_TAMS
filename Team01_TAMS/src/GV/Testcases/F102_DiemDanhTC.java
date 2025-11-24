package GV.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import GV.Pages.GVLoginPage;
import GV.Pages.GVDashboardPage;
import GV.Pages.ClassSectionPage;
import GV.Pages.DiemDanhPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.0.2 – Test chức năng Điểm danh sinh viên cho lớp học phần.
 */
public class F102_DiemDanhTC {

    private WebDriver driver;
    private GVLoginPage      loginPage;
    private GVDashboardPage  dashboardPage;
    private ClassSectionPage classSectionPage;
    private DiemDanhPopup    diemDanhPopup;

    /**
     * Hàm này được gọi TRƯỚC khi chạy các @Test trong class.
     * Mục đích:
     *  - Khởi tạo WebDriver
     *  - Mở trang Login của TA2025
     *  - Đăng nhập bằng tài khoản Giảng viên
     *  - Mở màn hình “Lớp học phần” và popup “Điểm danh lớp học”
     */
    @BeforeTest
    public void setUp() {

        // 1. Khởi tạo ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 2. Mở trang login
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Ta2025/Account/Login");

        // 3. Đăng nhập với role Giảng viên
        loginPage = new GVLoginPage(driver);
        dashboardPage = loginPage.loginWithGV(
                "thinh.2174802010519@vanlanguni.vn",
                "VLU31032003"
        );

        // 4. Từ Dashboard → vào menu “Lớp học phần”
        classSectionPage = dashboardPage.clickClassSectionMenu();

        // 5. Đợi bảng LHP load xong
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("dataTableBasic"))
        );

        // 6. Mở popup Điểm danh cho 1 lớp cụ thể
        String maLHP = "251_71ITBS10103_0102"; // bạn có thể đổi mã khác
        classSectionPage.openDiemDanhPopup(maLHP);

        // 7. Đợi modal “Điểm danh LHP …” hiển thị
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("diemdanhsv"))
        );

        diemDanhPopup = new DiemDanhPopup(driver);
        diemDanhPopup.waitLoaded();
    }

    /**
     * TC01 – Cập nhật trạng thái điểm danh cho 1 sinh viên
     * Test Objective:
     *  - Xác nhận GV có thể thay đổi trạng thái điểm danh (Có mặt / Vắng / Có phép)
     *    và lưu lại thành công.
     */
    @Test(priority = 1)
    public void TC01_UpdateAttendanceStatus() throws InterruptedException {

        // MSSV dùng để test (phải có trong danh sách lớp)
        String mssv = "2174802010489";

        // Bước 1: Tìm sinh viên theo MSSV
        diemDanhPopup.searchStudent(mssv);

        // Bước 2: Chọn trạng thái điểm danh "Có mặt"
        diemDanhPopup.selectStatus(mssv, "Có mặt");

        // Bước 3: Nhập ghi chú (nếu cần)
        diemDanhPopup.inputNote(mssv, "Điểm danh bằng script GV – TC01");

        // Bước 4: Bấm nút "Lưu thông tin"
        diemDanhPopup.clickSave();

        // Có thể chờ toast thông báo xuất hiện nếu bạn đã viết locator trong Page
        // diemDanhPopup.waitForSuccessToast();
        Thread.sleep(2000);
    }

    /**
     * Hàm này được gọi SAU khi chạy xong tất cả @Test.
     * Mục đích: Đóng trình duyệt, giải phóng tài nguyên.
     */
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
