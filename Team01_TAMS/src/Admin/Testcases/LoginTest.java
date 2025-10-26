package Admin.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import Admin.Pages.LoginPage;

public class LoginTest {
    WebDriver driver;
    LoginPage login;
    

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login = new LoginPage(driver);
    }
    
    @Test
    public void testLogin() throws InterruptedException {
        login.openLoginPage();
        login.enterEmail("hong.207ct58543@vanlanguni.vn");
        login.enterPassword("VH22022110@@");
        login.clickKeepMeSignIn();
    }
    
    @AfterTest
    public void teardown() {
//        driver.quit();
    }
}
