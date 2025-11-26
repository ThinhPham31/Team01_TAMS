package Helpers;

import org.openqa.selenium.WebDriver;
import General.Pages.LoginPage;
import Admin.Pages.AdminDashboardPage;

public class authenSupport 
{
    private WebDriver driver;

    public authenSupport(WebDriver driver) {
        this.driver = driver;
    }

    public AdminDashboardPage loginWithAdmin() 
    {
        String UserName = PropertiesHelper.getValue("ad-acc");
        String Password = PropertiesHelper.getValue("ad-pwd");

        LoginPage loginPage = new LoginPage(driver);

        // Xử lý lỗi SSL nếu có
        loginPage.handleSSLIfAny();

        // Mở trang login
        loginPage.openLoginPage();

        // Nhập email
        loginPage.enterEmail(UserName);

        // Nhập mật khẩu
        loginPage.enterPassword(Password);

        // Bỏ chọn ghi nhớ & nhấn "Yes" nếu có
        loginPage.clickKeepMeSignIn();

        // Trả về dashboard admin
        return new AdminDashboardPage(driver);
    }
    
}
