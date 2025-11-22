package TG.Testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import TG.Pages.LoginTGPage;

public class LoginTGTest {
    private WebDriver driver;
    private LoginTGPage login;

    // Hàm này được gọi trước khi thực thi Test
    @BeforeTest
    public void setup() {
        // Đường dẫn tới chromedriver.exe trong project
        String projectDir = System.getProperty("user.dir");
        String driverPath = projectDir + "\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", driverPath);
        
        // Mở rộng màn hình Web
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Tạo đối tượng mới của lớp LoginTGPage
        login = new LoginTGPage(driver);
    }
    //Test Objective : Đăng nhập vào hệ thiings thành công
    @Test
    public void testLogin() throws InterruptedException {
        // Mở trang web sau đó tiến hành nhấn Advanced sau đó Proceed link, Đăng nhập bằng Email
    	login.openLoginPage();
    	// Điền Email
        login.enterEmail("long.197ct09826@vanlanguni.vn");
        //Điền PassWord
        login.enterPassword("DuongHoangLong123");
        //Hộp thoại KeepMeSignIn hiển thị chọn Không nhắc lại và chọn No
        login.clickKeepMeSignIn();
        Thread.sleep(3000);
        // So sánh kết quả thực tế và mong đợi
        String expectedUrl = "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/DashBoard";
        String actualUrl   = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
        
    }

    @AfterTest(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
