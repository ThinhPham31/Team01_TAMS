package BCN.Testcases;

import BCN.Pages.BcnDashboardPage;
import BCN.Pages.BcnUserListPage;
import BCN.Pages.TaEvaluationResultPage;
import Commons.BaseTest;
import Commons.InitiationTest;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.Page;

public class TaEvaluationResultTest extends InitiationTest {

	private authenSupport auth;
    private BcnDashboardPage bcnDashboardPage;
    private TaEvaluationResultPage evaluationResultPage;
    private ValidateUIHelpers helper;

    @BeforeClass
    public void loginAsBCN() {

        // InitiationTest đã tạo driver đầy đủ
        helper = new ValidateUIHelpers(driver);

        // Login bằng tài khoản BCN
        auth = new authenSupport(driver);
        bcnDashboardPage = auth.loginWithBCN();

        // Mở trang Kết quả đánh giá
        evaluationResultPage = new TaEvaluationResultPage(driver, helper);
        evaluationResultPage.openPage();
    }

    // TC1 – Xem chi tiết kết quả đánh giá trợ giảng
    @Test(priority = 1)
    public void viewEvaluationResult_success() {
        // Chọn học kỳ 251
        evaluationResultPage.selectSemester("251");

        // Chọn ngành – chỉnh lại text đúng 100% với combobox trên web
        evaluationResultPage.selectMajor("Công Nghệ Thông Tin_TH0101");

        // Đảm bảo có kết quả
        int rowCount = evaluationResultPage.getResultRowCount();
        Assert.assertTrue(rowCount > 0,
                "BCN: Bảng kết quả đánh giá đang trống (rowCount = " + rowCount + ")");

        // Mở chi tiết đánh giá dòng đầu tiên
        evaluationResultPage.openFirstEvaluationDetail();

        // Kiểm tra thông tin chi tiết hiển thị
        Assert.assertTrue(
                evaluationResultPage.isEvaluationInfoDisplayed(),
                "BCN: Modal xem kết quả đánh giá không hiển thị thông tin."
        );
    }
}
