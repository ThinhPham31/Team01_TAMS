package GV.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import GV.Pages.GVLoginPage;
import GV.Pages.GVDashboardPage;
import GV.Pages.ClassSectionPage;
import GV.Pages.DiemDanhPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.0.3 – Test chức năng Tìm kiếm sinh viên trong popup điểm danh.
 */
public class F103_TimKiemSinhVienTC {

    private WebDriver driver;
    private GVLoginPage      loginPage;
    private GVDashboardPage  dashboardPage;
    private ClassSectionPage classSectionPage;
    private DiemDanhPopup    diemDanhPopup;

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

        classSectionPage.openDiemDanhPopup("251_71ITBS10103_0102");

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("diemdanhsv"))
        );

        diemDanhPopup = new DiemDanhPopup(driver);
        diemDanhPopup.waitLoaded();
    }

    /**
     * TC01 – Tìm kiếm theo Họ tên
     * Test Objective:
     *  - Khi nhập từ khóa là một phần Họ tên, danh sách sinh viên
     *    trong popup điểm danh phải được filter tương ứng.
     */
    @Test(priority = 1)
    public void TC01_SearchStudentByName() {

        // Từ khóa tìm kiếm (một phần họ tên)
        String keyword = "Nguyễn";

        // Bước 1: Nhập từ khóa vào ô "Tìm kiếm"
        diemDanhPopup.searchStudent(keyword);

        // Bước 2: Bạn có thể bổ sung assert:
        //   - Số dòng kết quả > 0
        //   - Mỗi dòng hiển thị chứa từ "Nguyễn" trong Họ tên
        // (Cài đặt chi tiết nằm trong DiemDanhPopup)
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
