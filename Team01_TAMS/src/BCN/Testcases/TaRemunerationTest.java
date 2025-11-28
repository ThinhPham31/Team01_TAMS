package BCN.Testcases;

import BCN.Pages.BcnDashboardPage;
import BCN.Pages.BcnUserListPage;
import BCN.Pages.TaRemunerationPage;
import Commons.BaseTest;
import General.Pages.LoginPage;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TaRemunerationTest extends BaseTest {

    private LoginPage loginPage;
    private TaRemunerationPage taRemPage;
    private authenSupport auth;
    private BcnDashboardPage bcnDashboardPage;
    private ValidateUIHelpers helper;

    @BeforeClass
    public void loginAsBCN() {

        // InitiationTest đã tạo driver đầy đủ
        helper = new ValidateUIHelpers(driver);

        // Login bằng tài khoản BCN
        auth = new authenSupport(driver);
        bcnDashboardPage = auth.loginWithBCN();

        // Mở trang thống kê thù lao
        taRemPage = new TaRemunerationPage(driver, helper);
        taRemPage.openPage();
    }

    @Test
    public void viewAndExportRemuneration_asBCN() {
        // chọn Học kỳ + Ngành
        taRemPage.selectSemester("251");
        taRemPage.selectMajor("Công Nghệ Thông Tin_TH0101");

        // kiểm tra có ít nhất 1 dòng TA
        int rowCount = taRemPage.getTaRowCount();
        Assert.assertTrue(rowCount > 0,
                "Không có dữ liệu trợ giảng trong bảng, rowCount = " + rowCount);

        // click Export
        taRemPage.clickExport();

        // kiểm tra trang vẫn load OK (ví dụ kiểm tra title / header)
        Assert.assertTrue(taRemPage.isPageLoaded(),
                "Sau khi Export, trang thống kê thù lao không còn hiển thị đúng.");
    }
}
