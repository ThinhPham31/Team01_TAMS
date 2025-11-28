package BCN.Testcases;

import BCN.Pages.BcnUserListPage;
import Commons.InitiationTest;
import Helpers.authenSupport;
import Helpers.ValidateUIHelpers;
import BCN.Pages.BcnDashboardPage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BcnUserListTest extends InitiationTest {

    private authenSupport auth;
    private BcnDashboardPage bcnDashboardPage;
    private BcnUserListPage userListPage;
    private ValidateUIHelpers helper;

    @BeforeClass
    public void loginAsBCN() {

        // InitiationTest đã tạo driver đầy đủ
        helper = new ValidateUIHelpers(driver);

        // Login bằng tài khoản BCN
        auth = new authenSupport(driver);
        bcnDashboardPage = auth.loginWithBCN();

        // Mở trang Người dùng
        userListPage = new BcnUserListPage(driver, helper);
        userListPage.open();
    }

    @Test(priority = 1)
    public void viewUserList_asBCN() {
        int count = userListPage.getUserCount();
        Assert.assertTrue(count > 0, "Danh sách người dùng trống hoặc không load đúng");
    }

    @Test(priority = 2)
    public void searchUser_flow_asBCN() throws InterruptedException {

        // Tìm user không tồn tại
        userListPage.searchUser("UserKhongTonTai_123456");

        Assert.assertTrue(userListPage.isEmptyMessageDisplayed());
        Assert.assertEquals(userListPage.getUserCount(), 0);

        Thread.sleep(1500);

        // Tìm user tồn tại
        String keyword = "2174802010704";
        userListPage.searchUser(keyword);

        List<String> names = userListPage.getAllUserNames();
        boolean found = names.stream().anyMatch(n -> n.contains(keyword));

        Assert.assertTrue(found, "Không tìm thấy user: " + keyword);

        Thread.sleep(1500);
    }
}
