package GV.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import Helpers.authenSupport;
import Admin.Pages.AdminDashboardPage;
import BCN.Pages.BcnDashboardPage;
import GV.Pages.GVDashboardPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    private WebDriver driver;
    private authenSupport authen;
    private GVDashboardPage gvDashboardPage;

    @BeforeClass
    public void setup() {
        // Tự động setup ChromeDriver đúng version với Chrome hiện tại
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Khởi tạo authenSupport với driver
        authen = new authenSupport(driver);
    }

    @Test
    public void testLoginAsGv() throws InterruptedException {
        // Login bằng tài khoản admin qua authenSupport
        gvDashboardPage = authen.loginWithGV();

        // Có thể assert page title, URL hoặc text để kiểm tra login thành công
        String expectedTitle = "GV DashBoard"; // thay bằng title thực tế
        String actualTitle = driver.getTitle();
        assert actualTitle.contains(expectedTitle) : "Login failed! Actual title: " + actualTitle;
    }
    
    @AfterClass
    public void teardown() {
        if(driver != null) {
//            driver.quit();
        }
    }
    
}
