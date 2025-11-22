package Admin.Testcases;

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

public class HideTermTC extends InitiationTest {

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

        authenSupport authen = new authenSupport(driver);
        adminDashboardPage = authen.loginWithAdmin();

        validateUIHelpers = new ValidateUIHelpers(driver);
        validateUIHelpers.waitForPageLoaded();

        viewTermListPage = adminDashboardPage.clickViewTermList();
        validateUIHelpers.waitForPageLoaded();
    }

    @Test(priority = 1)
    public void TC01_HideTerm_ByExcel() throws Exception {

        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("resources/data/UpdateTermData.xlsx", "Hide");

        String termId = excel.getCellData("HocKy", 1);
        String expectedStatus = excel.getCellData("ExpectedStatus", 1);

        viewTermListPage.safeEnterSearchTerm(termId);
        validateUIHelpers.waitForPageLoaded();

        viewTermListPage.toggleTermStatus(termId, false);
        Thread.sleep(400);

        boolean isActive = viewTermListPage.isTermStatusActive(termId);
        String actual = isActive ? "Active" : "Inactive";

        Assert.assertEquals(actual, expectedStatus, "Sai trạng thái");
    }
}
