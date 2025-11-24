package GV.Testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import GV.Pages.GVLoginPage;
import GV.Pages.GVDashboardPage;
import GV.Pages.ClassSectionPage;
import GV.Pages.ImportPopup;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * F1.0.4 – Test chức năng Import danh sách sinh viên từ file Excel.
 */
public class F104_ImportSinhVienTC {

    private WebDriver driver;
    private GVLoginPage      loginPage;
    private GVDashboardPage  dashboardPage;
    private ClassSectionPage classSectionPage;
    private ImportPopup      importPopup;

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

        // Mở popup Import cho lớp muốn test
        classSectionPage.openImportPopup("251_71ITBS10103_0102");

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("importsv"))
        );

        importPopup = new ImportPopup(driver);
        importPopup.waitLoaded();
    }

    /**
     * TC01 – Import thành công với file Excel hợp lệ
     * Test Objective:
     *  - Xác nhận khi chọn đúng file Excel mẫu, hệ thống import thành công
     *    và hiển thị thông báo “Đã import danh sách sinh viên”.
     */
    @Test(priority = 1)
    public void TC01_ImportStudentListSuccessfully() throws InterruptedException {

        // Đường dẫn file test 
        String filePath = System.getProperty("DS.xlsx") + "D:\\HK1 25-26\\Kiemthutudong\\DS.xlsx";

        // Bước 1: Chọn file
        importPopup.uploadFile(filePath);

        // Bước 2: Bấm nút "Import"
        importPopup.clickImport();

        // Bước 3: Có thể chờ toast thành công
        // importPopup.waitForSuccessToast();
        Thread.sleep(3000);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
