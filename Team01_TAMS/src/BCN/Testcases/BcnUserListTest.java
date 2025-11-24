package BCN.Testcases;

import BCN.Pages.BcnUserListPage;
import BCN.Pages.Login;
import Commons.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class BcnUserListTest extends BaseTest {

    private Login loginPage;
    private BcnUserListPage userListPage;

    @BeforeTest
    public void loginAsBCN() {
        // 1. Mở driver
        setupDriver();

        // 2. Khởi tạo page objects
        loginPage = new Login(driver, wait);
        userListPage = new BcnUserListPage(driver, wait);

        // 3. Login BCN
        String email = "tan.207ct68670@vanlanguni.vn";   // <-- đổi theo tài khoản thật
        String password = "VLU20102002";                 // <-- đổi theo mật khẩu thật
        loginPage.login(email, password);

        // 4. Vào menu Người dùng
        userListPage.open();
    }

    // TC1 – Xem danh sách người dùng
    @Test(priority = 1)
    public void viewUserList_asBCN() {
        int count = userListPage.getUserCount();
        Assert.assertTrue(count > 0,
                "BCN: Danh sách người dùng đang trống hoặc không load được (count = " + count + ")");
    }

    // TC2 – Tìm kiếm user TỒN TẠI
    @Test(priority = 2)
    public void searchUser_flow_asBCN() throws InterruptedException {
        // --- Bước 1: tìm user KHÔNG TỒN TẠI ---
        String keywordNotExist = "UserKhongTonTai_123456"; // chuỗi chắc chắn không trùng
        userListPage.searchUser(keywordNotExist);

        Assert.assertTrue(userListPage.isEmptyMessageDisplayed(),
                "BCN: Khi không có kết quả thì không thấy message 'Không tìm thấy dữ liệu nào'.");
        Assert.assertEquals(userListPage.getUserCount(), 0,
                "BCN: Khi không có kết quả, số dòng phải = 0.");

        // (tuỳ chọn) dừng 3s để nhìn màn hình không có dữ liệu
        Thread.sleep(3000);

        // --- Bước 2: tìm user TỒN TẠI ---
        String keywordExist = "2174802010704"; // mã có tồn tại
        userListPage.searchUser(keywordExist);

        List<String> names = userListPage.getAllUserNames();
        boolean found = names.stream().anyMatch(n -> n.contains(keywordExist));
        Assert.assertTrue(found,
                "BCN: Không tìm thấy user chứa keyword: " + keywordExist + ". Kết quả: " + names);

        // Dừng 5s để bạn quan sát kết quả tìm thấy
        Thread.sleep(5000);
    }
}

