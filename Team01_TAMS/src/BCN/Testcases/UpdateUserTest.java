package BCN.Testcases;

import BCN.Pages.BcnUserListPage;
import BCN.Pages.Login;
import BCN.Pages.UpdateUserPage;
import Commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UpdateUserTest extends BaseTest {

    private Login loginPage;
    private BcnUserListPage userListPage;
    private UpdateUserPage updateUserPage;

    // Mã cũ và mã mới để test
    private final String OLD_USER_CODE = "2174802010704";
    private final String NEW_USER_CODE = "11122222";

    @BeforeTest
    public void loginAsBCN() {
        // 1. Mở browser, init driver + wait
        setupDriver();

        // 2. Khởi tạo các Page Object
        loginPage      = new Login(driver, wait);
        userListPage   = new BcnUserListPage(driver, wait);
        updateUserPage = new UpdateUserPage(driver, wait);

        // 3. Login bằng tài khoản BCN
        String email    = "tan.207ct68670@vanlanguni.vn";   // đổi lại nếu cần
        String password = "VLU20102002";
        loginPage.login(email, password);

        // 4. Vào màn "Người dùng"
        userListPage.open();
    }

    @Test
    public void updateUser_success_showToast_andChangeCode() {

        // B1 – tìm user theo MÃ CŨ
        userListPage.searchUser(OLD_USER_CODE);

        // B2 – mở form Cập nhật của dòng đầu tiên
        userListPage.openEditFormFirstRow();

        // B3 – đợi form hiển thị
        updateUserPage.waitForFormVisible();

        // B4 – kiểm tra Email, Họ & Tên không cho sửa
        Assert.assertFalse(updateUserPage.isEmailEnabled(),
                "Email vẫn enable – yêu cầu là chỉ xem (readonly).");
        Assert.assertFalse(updateUserPage.isFullNameEnabled(),
                "Họ & Tên vẫn enable – yêu cầu là chỉ xem (readonly).");

        // B5 – cập nhật các field cho phép sửa (mỗi hàm bên Page đã sleep ~5s cho bạn nhìn)
        updateUserPage.setCode(NEW_USER_CODE);                // đổi Mã
        updateUserPage.setPhone("0912345678");                 // số điện thoại mới
        updateUserPage.setBirthdayAnyDay();                    // chọn 1 ngày sinh bất kỳ
        updateUserPage.setGender("Nam");                       // đúng text option Combobox
        updateUserPage.setMajor("Công Nghệ Thông Tin");        // đúng text option Combobox

        // B6 – lưu thông tin
        updateUserPage.clickSave();

        // B7 – kiểm tra toast message
        String toast = updateUserPage.getToastText();
        Assert.assertTrue(toast.contains("Đã cập nhật thông tin"),
                "Không thấy toast 'Đã cập nhật thông tin'. Thực tế: " + toast);

        // B8 – verify lại trên danh sách: tìm theo MÃ MỚI phải ra 1 dòng
        userListPage.searchUser(NEW_USER_CODE);
        int count = userListPage.getUserCount();
        Assert.assertEquals(count, 1,
                "Sau khi đổi mã, tìm theo mã mới phải ra đúng 1 dòng, hiện tại count = " + count);
    }
}

