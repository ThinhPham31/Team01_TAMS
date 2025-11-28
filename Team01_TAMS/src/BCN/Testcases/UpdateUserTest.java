package BCN.Testcases;

import BCN.Pages.BcnDashboardPage;
import BCN.Pages.BcnUserListPage;
import BCN.Pages.UpdateTaRegistrationPage;
import BCN.Pages.UpdateUserPage;
import Commons.BaseTest;
import Commons.InitiationTest;
import General.Pages.LoginPage;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UpdateUserTest extends InitiationTest {

	private LoginPage loginPage;
    private UpdateUserPage updateUserPage;
    private authenSupport auth;
    private BcnDashboardPage bcnDashboardPage;
    private BcnUserListPage userListPage;
    private ValidateUIHelpers helper;
    
    private static final String OLD_USER_CODE = "2174802010704";   // mã cũ cần update
    private static final String NEW_USER_CODE = "2174802010704A";  // mã mới sau khi update


    @BeforeClass
    public void loginAsBCN() {

        // InitiationTest đã tạo driver đầy đủ
    	helper = new ValidateUIHelpers(driver);

        // Login bằng tài khoản BCN
        auth = new authenSupport(driver);
        bcnDashboardPage = auth.loginWithBCN();

        // Mở trang cập nhật người dùng
        userListPage = new BcnUserListPage(driver, helper);
        userListPage.open();
    }

    @Test
    public void updateUser_success_showToast_andChangeCode() {

        helper = new ValidateUIHelpers(driver);

        // 1. Mở danh sách người dùng trước
        userListPage = new BcnUserListPage(driver, helper);
        userListPage.open();

        // 2. Tìm user theo mã cũ
        userListPage.searchUser(OLD_USER_CODE);

        // 3. Mở form cập nhật dòng đầu tiên
        userListPage.openEditFormFirstRow();

        // 4. Form UpdateUserPage hiển thị -> tạo object UpdateUserPage
        updateUserPage = new UpdateUserPage(driver, helper);
        updateUserPage.waitForFormVisible();

        // 5. Kiểm tra field readonly
        Assert.assertFalse(updateUserPage.isEmailEnabled());
        Assert.assertFalse(updateUserPage.isFullNameEnabled());

        // 6. Cập nhật dữ liệu
        updateUserPage.setCode(NEW_USER_CODE);
        updateUserPage.setPhone("0912345678");
        updateUserPage.setBirthdayAnyDay();
        updateUserPage.setGender("Nam");
        updateUserPage.setMajor("Công Nghệ Thông Tin");

        // 7. Lưu
        updateUserPage.clickSave();

        // 8. Kiểm tra toast
        String toast = updateUserPage.getToastText();
        Assert.assertTrue(toast.contains("Đã cập nhật thông tin"));

        // 9. Tìm lại theo mã mới
        userListPage.searchUser(NEW_USER_CODE);
        Assert.assertEquals(userListPage.getUserCount(), 1);
    }
}

