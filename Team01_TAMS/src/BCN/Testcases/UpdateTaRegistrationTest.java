package BCN.Testcases;
import BCN.Pages.BcnDashboardPage;
import BCN.Pages.TaRemunerationPage;
import BCN.Pages.UpdateTaRegistrationPage;
import Commons.BaseTest;
import Commons.InitiationTest;
import General.Pages.LoginPage;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UpdateTaRegistrationTest extends InitiationTest {

    private LoginPage loginPage;
    private UpdateTaRegistrationPage taPage;
    private ValidateUIHelpers helper;
    private authenSupport auth;
    private BcnDashboardPage bcnDashboardPage;

    @BeforeClass
    public void loginAsBCN() {

        // InitiationTest đã tạo driver đầy đủ
    	helper = new ValidateUIHelpers(driver);

        // Login bằng tài khoản BCN
        auth = new authenSupport(driver);
        bcnDashboardPage = auth.loginWithBCN();

        // Mở trang thống kê thù lao
        taPage = new UpdateTaRegistrationPage(driver, helper);
        taPage.openPage();
    }

    // ==================== TC1 – Xem chi tiết đơn đăng ký trợ giảng ====================
    @Test(priority = 1)
    public void viewRegistrationDetail_success() {

        // Mở lại trang (cho chắc)
        taPage.openPage();

        // Chọn Học kỳ 251 + Ngành CNTT
        taPage.selectSemester("251");
        taPage.selectMajorFilter("Công Nghệ Thông Tin");

        // Mở modal chi tiết dòng đầu tiên
        taPage.openFirstRegistrationDetail();

        // Kiểm tra modal hiển thị thông tin
        Assert.assertTrue(
                taPage.isDetailInfoDisplayed(),
                "BCN: Modal chi tiết đơn trợ giảng không hiển thị đầy đủ thông tin."
        );
    }

    // ==================== TC2 – Duyệt đơn đăng ký trợ giảng ====================
    @Test(priority = 2)
    public void approveRegistration_success() {

        // Mở lại trang + set filter
        taPage.openPage();
        taPage.selectSemester("251");
        taPage.selectMajorFilter("Công Nghệ Thông Tin");

        // Trạng thái trước khi duyệt (ví dụ: 'Chưa được duyệt')
        String statusBefore = taPage.getFirstRowStatus();

        // Mở chi tiết + bấm duyệt
        taPage.openFirstRegistrationDetail();
        taPage.approveInDetailModal();

        // Kiểm tra toast
        String toast = taPage.getToastText();
        Assert.assertTrue(
                toast.contains("Duyệt thành công") || toast.contains("Đã được duyệt") ||
                toast.contains("Duyệt") || toast.contains("thành công"),
                "Toast duyệt không đúng nội dung. Thực tế: " + toast
        );

        // Trạng thái sau khi duyệt phải khác trước (thường là 'Đã được duyệt')
        String statusAfter = taPage.getFirstRowStatus();
        Assert.assertNotEquals(
                statusAfter, statusBefore,
                "BCN: Sau khi duyệt, trạng thái không thay đổi. Trước: "
                        + statusBefore + " | Sau: " + statusAfter
        );
    }
}
