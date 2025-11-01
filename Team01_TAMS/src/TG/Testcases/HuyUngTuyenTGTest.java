package TG.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import TG.Pages.UngTuyenTGPage;
import TG.Pages.LoginTGPage;

public class HuyUngTuyenTGTest {
	private WebDriver driver;
    private UngTuyenTGPage page;
    private String filePath;
    private LoginTGPage login;
    
    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login = new LoginTGPage(driver);
        login.openLoginPage();
        login.enterEmail("long.197ct09826@vanlanguni.vn");
        login.enterPassword("DuongHoangLong123");
        login.clickKeepMeSignIn();
        Thread.sleep(2000);
        page = new UngTuyenTGPage(driver);
    }
    // ✅ TC01 – Huỷ đăng ký chọn không huỷ và đóng form
    @Test(priority = 1)
    public void TC01_CancelbutNo() throws InterruptedException {
    	page.updateApplyFormNDB();
    	page.CancelbutNo();
    	page.CloseForm();
    	Thread.sleep(2000);
    	
    }
    
 // ✅ TC02 – Huỷ đăng ký chọn huỷ ngay và nhận thông báo
    @Test(priority = 2)
    public void TC02_Cancel() throws InterruptedException {
    	page.ReUpdate();
    	page.CancelbutYes();
    	//Thread.sleep(1000);
    	page.CancelSuccessMessageDisplayed();
    	Thread.sleep(3000);
    	
    }
    
    @AfterClass
    public void teardown () {
    	driver.quit();
    }

}
