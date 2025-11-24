package BCN.Testcases;

import Commons.BaseTest;

import BCN.Pages.Login;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private Login loginPage;

    @BeforeTest
    public void setup() {
        setupDriver();
        loginPage = new Login(driver, wait);
    }

    @Test
    public void login_As_BCN() {
        String email = "tan.207ct68670@vanlanguni.vn";
        String password = "VLU20102002";

        loginPage.login(email, password);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("DashBoard"),
                "Không vào được trang DashBoard, URL hiện tại: " + currentUrl);
    }
}

