package BCN.Testcases;

import BCN.Pages.Login;
import BCN.Pages.UpdateTaRegistrationPage;
import Commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UpdateTaRegistrationTest extends BaseTest {

    private Login loginPage;
    private UpdateTaRegistrationPage taPage;

    @BeforeTest
    public void loginAsBCN() {
        // 1. Mở browser
        setupDriver();

        // 2. Khởi tạo page object
        loginPage = new Login(driver, wait);
        taPage    = new UpdateTaRegistrationPage(driver, wait);

        // 3. Login bằng tài khoản **BCN**
        String email    = "tan.207ct68670@vanlanguni.vn";
        String password = "VLU20102002";
        loginPage.login(email, password);

        // 4. Sau khi login xong, mở trang “Danh Sách Sinh Viên Đăng Ký Trợ Giảng”
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
