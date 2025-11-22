package Admin.Testcases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Commons.InitiationTest;
import Admin.Pages.AdminDashboardPage;
import Admin.Pages.ViewTermListPage;
import Helpers.ExcelHelper;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SearchTermTC extends InitiationTest {

    private AdminDashboardPage adminDashboardPage;
    private ViewTermListPage viewTermListPage;
    private ValidateUIHelpers validateUIHelpers;

    @BeforeClass
    public void setUp() {

        // Khởi tạo driver nếu chưa có
        driver = getDriver();
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        // Đăng nhập tài khoản admin
        authenSupport authen = new authenSupport(driver);
        adminDashboardPage = authen.loginWithAdmin();

        // Helper để đợi load page và validate dữ liệu
        validateUIHelpers = new ValidateUIHelpers(driver);
        validateUIHelpers.waitForPageLoaded();

        // Điều hướng đến trang Term List
        viewTermListPage = adminDashboardPage.clickViewTermList();
        validateUIHelpers.waitForPageLoaded();
    }


    /*
     * TC01 - Exact Match (search "271")
     * B1 – Đọc Excel sheet ExactMatch
     * B2 – Thực tế từ UI với keyword "271"
     * B3 – So sánh dữ liệu
     */
    @Test(priority = 1)
    public void TC01_Search_ExactMatch() throws IOException {

        ExcelHelper excel = new ExcelHelper();

        // B1: dữ liệu kỳ vọng
        List<Map<String, String>> expected =
                excel.readExcelAsListOfMap("resources/data/SearchData.xlsx", "ExactMatch");

        // B2: dữ liệu UI
        viewTermListPage.enterSearchTerm("271");
        List<Map<String, String>> actual = viewTermListPage.getWebTable();

        // B3: so sánh
        validateUIHelpers.compareList(expected, actual);
    }


    /*
     * TC02 - No Result (search "99999")
     * Kiểm tra search không trả về dòng nào
     */
    @Test(priority = 2)
    public void TC02_Search_NoResult() throws IOException {

        ExcelHelper excel = new ExcelHelper();

        // B1
        List<Map<String, String>> expected =
                excel.readExcelAsListOfMap("resources/data/SearchData.xlsx", "NoResult");

        // B2
        viewTermListPage.enterSearchTerm("99999");
        List<Map<String, String>> actual = viewTermListPage.getWebTable();

        // B3
        validateUIHelpers.compareList(expected, actual);
    }


    /*
     * TC03 - Partial Match (search "2")
     * Kiểm tra search trả về các dòng có chứa ký tự "2"
     */
    @Test(priority = 3)
    public void TC03_Search_Partial() throws IOException {

        ExcelHelper excel = new ExcelHelper();

        // B1
        List<Map<String, String>> expected =
                excel.readExcelAsListOfMap("resources/data/SearchData.xlsx", "Partial");

        // B2
        viewTermListPage.enterSearchTerm("2");
        List<Map<String, String>> actual = viewTermListPage.getWebTable();

        // B3
        validateUIHelpers.compareList(expected, actual);
    }


    /*
     * TC04 - Reset search → bảng trả về đầy đủ
     * Khi search rỗng thì phải hiển thị toàn bộ danh sách
     */
    @Test(priority = 4)
    public void TC04_Search_Reset() throws IOException {

        ExcelHelper excel = new ExcelHelper();

        // B1: full list từ Excel
        List<Map<String, String>> expectedFull =
                excel.readExcelAsListOfMap("resources/data/SearchData.xlsx", "FullList");

        // B2: reset search
        viewTermListPage.enterSearchTerm("");
        List<Map<String, String>> actualFull = viewTermListPage.getWebTable();

        // B3
        validateUIHelpers.compareList(expectedFull, actualFull);
    }
}
