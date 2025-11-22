package Admin.Testcases;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Commons.InitiationTest;
import Admin.Pages.AdminDashboardPage;
import Admin.Pages.ViewTermListPage;
import Helpers.ExcelHelper;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateTermTC extends InitiationTest {

    private AdminDashboardPage adminDashboardPage;
    private ViewTermListPage viewTermListPage;
    private ValidateUIHelpers validateUIHelpers;

    @BeforeClass
    public void setUp() {

        driver = getDriver();
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        // Đăng nhập
        authenSupport authen = new authenSupport(driver);
        adminDashboardPage = authen.loginWithAdmin();

        // Chờ load trang
        validateUIHelpers = new ValidateUIHelpers(driver);
        validateUIHelpers.waitForPageLoaded();

        // Điều hướng sang trang danh sách học kỳ
        viewTermListPage = adminDashboardPage.clickViewTermList();
        validateUIHelpers.waitForPageLoaded();
    }

    /*
     * Test Objective (TC01):
     * Xác nhận hệ thống cập nhật thành công "Ngày bắt đầu" của học kỳ
     * khi người dùng mở form chỉnh sửa, thay đổi giá trị ngày mới và nhấn Lưu.
     *
     * Quy trình kiểm thử:
     * 1. Search học kỳ cần cập nhật (ví dụ: 271)
     * 2. Mở giao diện sửa học kỳ
     * 3. Thay đổi ngày bắt đầu thành giá trị mới bằng code
     * 4. Nhấn Lưu và đợi modal đóng
     * 5. Xác minh dữ liệu hiển thị trên bảng trùng khớp với ExpectedDate trong Excel
     */
    @Test(priority = 1)
    public void TC01_UpdateTerm_ByExcel() throws Exception {

        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("resources/data/UpdateTermData.xlsx", "Update");

        String termId = excel.getCellData("HocKy", 1); 
        String expectedDate = excel.getCellData("ExpectedDate", 1);

        // Tìm đúng học kỳ cần update
        viewTermListPage.safeEnterSearchTerm(termId);
        validateUIHelpers.waitForPageLoaded();

        // Mở form edit
        viewTermListPage.openEditTermForm(termId);
        validateUIHelpers.waitForPageLoaded();

        // Cập nhật ngày bắt đầu
        viewTermListPage.setEditStartDateDirect("2027-03-05");

        // Lưu thay đổi
        driver.findElement(By.id("btnEditSubmit")).click();

        // Chờ modal đóng, table load lại DOM
        viewTermListPage.waitAfterSave();

        // Lấy data hiển thị
        String actualDate = viewTermListPage.getStartDate(termId);

        // Kiểm tra đúng ngày
        Assert.assertEquals(actualDate, expectedDate, "Sai dữ liệu sau update");
    }



    /*
     * Test Objective (TC02):
     * Xác nhận toàn bộ dữ liệu hiển thị trên UI (bảng danh sách học kỳ)
     * khớp với dữ liệu mong đợi trong Excel (sheet Verify).
     *
     * Quy trình kiểm thử:
     * 1. Lấy danh sách dữ liệu mong đợi từ Excel (List<Map<String,String>>)
     * 2. Search đúng học kỳ cần đối chiếu
     * 3. Lấy dữ liệu trên bảng UI (actualList)
     * 4. So sánh expectedList và actualList từng dòng
     */
    @Test(priority = 2)
    public void TC03_CompareUI_With_Excel() throws Exception {

        ExcelHelper excel = new ExcelHelper();
        List<Map<String, String>> expectedList = excel.readExcelAsListOfMap(
                "resources/data/UpdateTermData.xlsx",
                "Verify"
        );

        // Lấy học kỳ cần validate trong Excel
        String termId = expectedList.get(0).get("HocKy");

        // Filter bảng để lấy đúng dòng
        viewTermListPage.safeEnterSearchTerm(termId);
        validateUIHelpers.waitForPageLoaded();

        // Lấy toàn bộ dữ liệu trên UI
        List<Map<String, String>> actualList = viewTermListPage.getWebTable();

        // So sánh với Excel
        validateUIHelpers.compareList(expectedList, actualList);
    }
}
