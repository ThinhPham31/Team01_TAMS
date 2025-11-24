package GV.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import GV.Pages.GVLoginPage;
import GV.Pages.GVDashboardPage;
import GV.Pages.ClassSectionPage;
import GV.Pages.KetQuaDiemDanhPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.0.6 – Test chức năng Xem kết quả điểm danh lớp học phần.
 */
public class F106_KetQuaDiemDanhTC {

    private WebDriver driver;
    private GVLoginPage          loginPage;
    private GVDashboardPage      dashboardPage;
    private ClassSectionPage     classSectionPage;
    private KetQuaDiemDanhPopup  ketQuaPopup;

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

        classSectionPage = dashboardPage.clickClassSectionMenu();

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("dataTableBasic"))
        );

        // Mở “Kết quả điểm danh lớp học”
        classSectionPage.openKetQuaDiemDanhPopup("251_71ITBS10103_0102");

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ketquadiemdanhmodal"))
        );

        ketQuaPopup = new KetQuaDiemDanhPopup(driver);
        ketQuaPopup.waitLoaded();
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
        int rowCount = ketQuaPopup.getStudentRowCount();

        // Bước 2: Lấy số cột buổi học (các cột ngày)
        int sessionColCount = ketQuaPopup.getSessionColumnCount();

        // Bước 3: Tùy ý assert – ví dụ phải có ít nhất 1 sinh viên & 1 buổi học
        if (rowCount == 0 || sessionColCount == 0) {
            throw new AssertionError("Bảng kết quả điểm danh không có dữ liệu!");
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
