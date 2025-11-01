package TG.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import TG.Pages.UngTuyenTGPage;
import TG.Pages.LoginTGPage;

public class UngTuyenTGTest {
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

    // ✅ TC01 – Lưu thông tin thành công ngành đặc biệt
    @Test(priority = 4)
    public void TC01_applyTeachingAssistant_success_NDB() throws InterruptedException {
        page.clearAllFields();
    	filePath = "D:\\PLT.png";
        page.fillForm("8.0", "80", "9.0", filePath);
        page.clickSave();
        Thread.sleep(1000);
        page.isSuccessMessageDisplayed();
        Thread.sleep(3000);
    }

    // ✅ TC02 – Thiếu thông tin bắt buộc
    @Test(priority = 1)
    public void TC02_missingRequiredFields() throws InterruptedException {
    	page.openApplyFormNDB();
    	page.clickSave();
        Thread.sleep(1000);
        Assert.assertTrue(page.hasErrorMessage("Vui lòng không bỏ trống Điểm TB tích lũy.","Vui lòng không bỏ trống Điểm rèn luyện.","Vui lòng không bỏ trống Điểm tổng kết môn.","Vui lòng không bỏ trống Hình ảnh minh chứng."), "Không hiển thị lỗi thiếu trường bắt buộc");
    }

    // ✅ TC03 – Nhập ký tự không phải số
    @Test(priority = 2)
    public void TC03_invalidCharacterInNumberField() throws InterruptedException {
        page.clearAllFields();
    	filePath = "D:\\PLT.png";
        page.fillForm("abc", "xyz", "ghi", null);
        page.clickSave();
        Assert.assertTrue(page.hasErrorMessage("Vui lòng không bỏ trống Điểm TB tích lũy.","Vui lòng không bỏ trống Điểm rèn luyện.","Vui lòng không bỏ trống Điểm tổng kết môn.","Vui lòng không bỏ trống Hình ảnh minh chứng."), "Cho phép nhập chữ");
    }

    // ✅ TC04 – Điểm trung bình < 7 , Điểm rèn luyện < 65 , Điểm tổng kết < 7 ngành đặc biệt
    @Test(priority = 3)
    public void TC04_LowerScoresNĐB() throws InterruptedException {
    	page.clearAllFields();
        filePath = "D:\\PLT.png";
        page.fillForm("6.0", "60", "6.9", null);
        page.clickSave();
        Assert.assertTrue(page.hasErrorMessage("Điểm TB tích lũy phải đạt từ 7.0 - 10.0 điểm","Điểm rèn luyện phải đạt từ 65 - 100 điểm","Điểm TK phải đạt từ 7.0 - 10.0 điểm","Vui lòng không bỏ trống Hình ảnh minh chứng."), "Không hiển thị lỗi ");
    }

    // ✅ TC05 – Điểm trung bình < 7 , Điểm rèn luyện < 65 , Điểm tổng kết < 8 ngành khác
    @Test(priority = 5)
    public void TC05_LowerScoresNK() throws InterruptedException {
    	// Giả lập ngành khác bằng điểm 7.9
        page.openApplyFormNK();
        filePath = "D:\\PLT.png";
        page.fillForm("6.0", "60", "7.9", filePath);
        page.clickSave();
        Assert.assertTrue(page.hasErrorMessage("Điểm TB tích lũy phải đạt từ 7.0 - 10.0 điểm","Điểm rèn luyện phải đạt từ 65 - 100 điểm","Điểm TK phải đạt từ 8.0 - 10.0 điểm","Vui lòng không bỏ trống Hình ảnh minh chứng."), "Không hiển thị lỗi");
    }

    // ✅ TC06 – Lưu thông tin thành công ngành khác
    @Test(priority = 6)
    public void TC06_applyTeachingAssistant_success_NK() throws InterruptedException {
    	page.clearAllFields();
    	filePath = "D:\\PLT.png";
        page.fillForm("7.0", "65", "8", filePath);
        page.clickSave();
        Thread.sleep(1000);
        page.isSuccessMessageDisplayed();
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
