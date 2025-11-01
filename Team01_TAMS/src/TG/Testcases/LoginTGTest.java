package TG.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import TG.Pages.LoginTGPage;

public class LoginTGTest {
    WebDriver driver;
    LoginTGPage login;
    

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login = new LoginTGPage(driver);
    }
    
    @Test
    public void testLogin() throws InterruptedException {
        login.openLoginPage();
        login.enterEmail("long.197ct09826@vanlanguni.vn");
        login.enterPassword("DuongHoangLong123");
        login.clickKeepMeSignIn();
    }
    
    @AfterTest
    public void teardown() {
//        driver.quit();
    }
}