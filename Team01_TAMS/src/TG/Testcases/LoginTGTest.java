package TG.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TG.Pages.LoginTGPage;

public class LoginTGTest {
    private WebDriver driver;
    private LoginTGPage login;

    @BeforeTest
    public void setup() {
        // Đường dẫn tới chromedriver.exe trong project
        String projectDir = System.getProperty("user.dir");
        String driverPath = projectDir + "\\Team01_TAMS\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login = new LoginTGPage(driver);
    }

    @Test
    public void testLogin() throws InterruptedException {
        login.openLoginPage();
        login.enterEmail("tan.207ct68670@vanlanguni.vn");
        login.enterPassword("VLU20102002");
        login.clickKeepMeSignIn();
        Thread.sleep(3000);

        String expectedUrl = "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/DashBoard";
        String actualUrl   = driver.getCurrentUrl();
    }

    @AfterTest(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            
        }
    }
}
