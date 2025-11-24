package BCN.Testcases;

import BCN.Pages.Login;
import BCN.Pages.TaRemunerationPage;
import Commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TaRemunerationTest extends BaseTest {

    private Login loginPage;
    private TaRemunerationPage taRemPage;

    @BeforeTest
    public void loginAsBCN() {
        setupDriver();
        loginPage = new Login(driver, wait);
        taRemPage = new TaRemunerationPage(driver, wait);

        String email    = "tan.207ct68670@vanlanguni.vn";
        String password = "VLU20102002";
        loginPage.login(email, password);

        taRemPage.openPage();   // mở trang Thống kê thù lao
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
