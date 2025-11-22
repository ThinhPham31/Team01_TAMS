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
    
  //Hàm này được gọi trước kkhi thực thi class này
    //Mục đích là để chuẩn bị môi trường chạy  các TCs kiểm tra chức năng Ứng Tuyển Trợ Giảng
    @BeforeClass
    
    public void setup() throws InterruptedException {
    	// Đường dẫn tới chromedriver.exe trong project
        String projectDir = System.getProperty("user.dir");
        String driverPath = projectDir + "\\resources\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        
        // Mở rộng màn hình Web
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Tạo đối tượng mới của lớp LoginTGPage
        login = new LoginTGPage(driver);
        // Mở trang web tiến hành nhấn Advanced sau đó Proceed link, Đăng nhập bằng Email
    	login.openLoginPage();
    	// Điền Email
        login.enterEmail("long.197ct09826@vanlanguni.vn");
        //Điền PassWord
        login.enterPassword("DuongHoangLong123");
        //Hộp thoại KeepMeSignIn hiển thị chọn Không nhắc lại và chọn No
        login.clickKeepMeSignIn();
        Thread.sleep(3000);
        page = new UngTuyenTGPage(driver);
        
    }
    
    
    // ✅ TC01 – Huỷ đăng ký chọn không huỷ và đóng form
    // Mục đích: Kiểm tra Hệ thống có yêu cầu người dùng xác nhận Huỷ đăng ký không và chọn không sau đó đóng form
    @Test(priority = 1)
    public void TC01_CancelbutNo() throws InterruptedException {
    	//Mở form Cập Nhật Ứng Tuyển Trựo Giảng Ngành Đặc Biệt
    	page.updateApplyFormNDB();
    	// Chọn Huỷ Đăng Ký sau đó chọn Không Huỷ
    	page.CancelbutNo();
    	// Đóng Form
    	page.CloseForm();
    	Thread.sleep(2000);
    	
    }
    
    // ✅ TC02 – Huỷ đăng ký chọn huỷ ngay và nhận thông báo
    // Mục Đích: Kiểm tra hệ thống cho phép huỷ Đăng ký ứng tuyển và hiển thị thông báo Huỷ thành công
    @Test(priority = 2)
    public void TC02_Cancel() throws InterruptedException {
    	//Mở form Cập Nhật Ứng Tuyển Trựo Giảng Ngành Đặc Biệt
    	page.ReUpdate();
    	// Chọn Huỷ Đăng Ký sau đó chọn Huỷ ngay
    	page.CancelbutYes();
    	// Kiểm tra nếu Thông báo Huỷ thành công được hiển thị
    	page.CancelSuccessMessageDisplayed();
    	Thread.sleep(3000);
    	
    }
    
    // Hàm này được gọi sau khi thực thi class này
    // Mục đích thoát khỏi trình duyệt
    @AfterClass
    public void teardown () {
    	driver.quit();
    }

}
