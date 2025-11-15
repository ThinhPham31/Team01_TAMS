package Commons;

import GV.Pages.GVLoginPage;
import org.openqa.selenium.WebDriver;

public class GVBaseTest extends InitiationTest {

    // Driver dùng chung cho tất cả testcase con
    protected WebDriver driver;

    // Page Object trang login
    protected GVLoginPage loginPage;

    @Override
    public void initializeTestBaseSetup(String browserType, String appURL) {

        // Gọi hàm setup driver từ class InitiationTest (có sẵn trong project của bạn)
        super.initializeTestBaseSetup(browserType, appURL);

        // Lấy driver đã khởi tạo ở InitiationTest
        driver = getDriver();

        // Khởi tạo Page Object cho màn hình Login
        loginPage = new GVLoginPage(driver);

        // B1: Đăng nhập bằng tài khoản GV
        loginPage.login(
                "thinh.2174802010519@vanlanguni.vn",
                "VLU31032003"
        );

        // B2: Chờ bạn nhập mã Authentication trên web → đăng nhập hoàn tất
        loginPage.waitForAuthenticationSuccess();
    }
}
