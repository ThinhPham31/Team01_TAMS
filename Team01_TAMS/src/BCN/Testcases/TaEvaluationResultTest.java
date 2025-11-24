package BCN.Testcases;

import BCN.Pages.Login;
import BCN.Pages.TaEvaluationResultPage;
import Commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TaEvaluationResultTest extends BaseTest {

    private Login loginPage;
    private TaEvaluationResultPage evalPage;

    @BeforeTest
    public void loginAsBCN() {
        // 1. Mở browser
        setupDriver();

        // 2. Khởi tạo page objects
        loginPage = new Login(driver, wait);
        evalPage  = new TaEvaluationResultPage(driver, wait);

        // 3. Login bằng tài khoản **BCN** của bạn
        String email    = "tan.207ct68670@vanlanguni.vn";
        String password = "VLU20102002";
        loginPage.login(email, password);

        // 4. Vào trang Kết Quả Đánh Giá
        evalPage.openPage();
    }

    // TC1 – Xem chi tiết kết quả đánh giá trợ giảng
    @Test(priority = 1)
    public void viewEvaluationResult_success() {
        // Chọn học kỳ 251
        evalPage.selectSemester("251");

        // Chọn ngành – chỉnh lại text đúng 100% với combobox trên web
        evalPage.selectMajor("Công Nghệ Thông Tin_TH0101");

        // Đảm bảo có kết quả
        int rowCount = evalPage.getResultRowCount();
        Assert.assertTrue(rowCount > 0,
                "BCN: Bảng kết quả đánh giá đang trống (rowCount = " + rowCount + ")");

        // Mở chi tiết đánh giá dòng đầu tiên
        evalPage.openFirstEvaluationDetail();

        // Kiểm tra thông tin chi tiết hiển thị
        Assert.assertTrue(
                evalPage.isEvaluationInfoDisplayed(),
                "BCN: Modal xem kết quả đánh giá không hiển thị thông tin."
        );
    }
}
