package Helpers;

import org.openqa.selenium.WebDriver;
import General.Pages.LoginPage;
import Admin.Pages.AdminDashboardPage;
import BCN.Pages.BcnDashboardPage;
import BCN.Pages.BcnUserListPage;
import GV.Pages.GVDashboardPage;


public class authenSupport

{
	private ValidateUIHelpers helper;
	private LoginPage loginPage;
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
    
    public BcnDashboardPage loginWithBCN() 
    {
        String UserName = PropertiesHelper.getValue("bcn-acc");
        String Password = PropertiesHelper.getValue("bcn-pwd");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.handleSSLIfAny();
        loginPage.openLoginPage();
        loginPage.enterEmail(UserName);
        loginPage.enterPassword(Password);
        loginPage.clickKeepMeSignIn();

        return new BcnDashboardPage(driver);
    }
    
    
    // test loginwithgv
    public GVDashboardPage loginWithGV() throws InterruptedException 
    {
        String UserName = PropertiesHelper.getValue("fac-acc");
        String Password = PropertiesHelper.getValue("fac-pwd");
        String oldUrl = driver.getCurrentUrl();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.handleSSLIfAny();
        loginPage.openLoginPage();
        loginPage.enterEmail(UserName);
        loginPage.enterPassword(Password);
        
        // Chờ bạn xác thực, đến khi URL thay đổi
        while (driver.getCurrentUrl().equals(oldUrl)) {
            helper.waitForPageLoaded();
            Thread.sleep(1000);
        }
        
        loginPage.clickKeepMeSignIn();

        return new GVDashboardPage(driver);
    }

    
}
