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
 * F1.0.7 – Test chức năng Xuất file Excel kết quả điểm danh.
 */
public class F107_ExportDiemDanhTC {

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

        classSectionPage.openKetQuaDiemDanhPopup("251_71ITBS10103_0102");

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ketquadiemdanhmodal"))
        );

        ketQuaPopup = new KetQuaDiemDanhPopup(driver);
        ketQuaPopup.waitLoaded();
    }

    /**
     * TC01 – Xuất file Excel kết quả điểm danh
     * Test Objective:
     *  - Khi người dùng bấm nút “Xuất”, hệ thống sinh file Excel tải về.
     */
    @Test(priority = 1)
    public void TC01_ExportAttendanceResultToExcel() throws InterruptedException {

        // Bước 1: Bấm nút "Xuất"
        ketQuaPopup.clickExport();

        // Bước 2: (tùy chọn) chờ vài giây để file tải xong
        Thread.sleep(3000);

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
