package TG.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import TG.Pages.UngTuyenTGPage;
import TG.Pages.LoginTGPage;

public class UpdateUngTuyenTGTest {
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

    // ✅ TC01 – Lưu thông tin thành công ngành đặc biệt
    @Test(priority = 4)
    public void TC01_applyTeachingAssistant_success_NDB() throws InterruptedException {
        page.clearScores();
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
    	// Mở Form Cập nhật ứng tuyển Trợ Giảng ngành Đặc Biệt
    	page.updateApplyFormNDB();
    	// Xoá tất cả các trường điểm và hình ảnh
    	page.clearAllFields();
    	// Chọn Lưu Thông Tin
    	page.clickSave();
        Thread.sleep(1000);
        // So sánh Nếu Hệ thống hiển thị lỗi
        Assert.assertTrue(page.hasErrorMessage("Vui lòng không bỏ trống Điểm TB tích lũy.","Vui lòng không bỏ trống Điểm rèn luyện.","Vui lòng không bỏ trống Điểm tổng kết môn.","Vui lòng không bỏ trống Hình ảnh minh chứng."), "Không hiển thị lỗi thiếu trường bắt buộc");
        
    }

    // ✅ TC03 – Nhập ký tự không phải số
    @Test(priority = 2)
    public void TC03_invalidCharacterInNumberField() throws InterruptedException {
    	//Điền Form ứng tuyển với ký tự không phải số và bỏ trống hình ảnh
    	page.fillForm("abc", "xyz", "ghi", null);
    	//Chọn Lưu Thông Tin
        page.clickSave();
        //So sánh Nếu hệ thống hiển thị lỗi
        Assert.assertTrue(page.hasErrorMessage("Vui lòng không bỏ trống Điểm TB tích lũy.","Vui lòng không bỏ trống Điểm rèn luyện.","Vui lòng không bỏ trống Điểm tổng kết môn.","Vui lòng không bỏ trống Hình ảnh minh chứng."), "Cho phép nhập chữ");
        
    }

    // ✅ TC04 – Điểm trung bình < 7 , Điểm rèn luyện < 65 , Điểm tổng kết < 7 ngành đặc biệt
    @Test(priority = 3)
    public void TC04_LowerScoresNĐB() throws InterruptedException {
    	//Điền Form ứng tuyển với điểm trung bình = 6.9 , điểm rèn luyện = 64 , điểm tổng kết = 6.9, hình ảnh bỏ trống
    	page.fillForm("6.9", "64", "6.9", null);
    	//Chọn Lưu Thông Tin
        page.clickSave();
        //So sánh Nếu hệ thống hiển thị lỗi
        Assert.assertTrue(page.hasErrorMessage("Điểm TB tích lũy phải đạt từ 7.0 - 10.0 điểm","Điểm rèn luyện phải đạt từ 65 - 100 điểm","Điểm TK phải đạt từ 7.0 - 10.0 điểm","Vui lòng không bỏ trống Hình ảnh minh chứng."), "Không hiển thị lỗi ");
        
    }

    // ✅ TC05 – Điểm trung bình < 7 , Điểm rèn luyện < 65 , Điểm tổng kết < 8 ngành khác
    @Test(priority = 5)
    public void TC05_LowerScoresNK() throws InterruptedException {
    	// Mở Form Cập nhật ứng tuyển Trợ Giảng ngành khác
        page.updateApplyFormNK();
      //Điền Form Ứng Tuyển với Điểm trung bình = 6.9 , Điểm rèn luyện = 64 , Điểm tổng kết = 7.9, hình ảnh bỏ trống
        page.fillForm("6.9", "64", "7.9", null);
        //Chọn Lưu Thông Tin
        page.clickSave();
        //So sánh Nếu hệ thống hiển thị lỗi
        Assert.assertTrue(page.hasErrorMessage("Điểm TB tích lũy phải đạt từ 7.0 - 10.0 điểm","Điểm rèn luyện phải đạt từ 65 - 100 điểm","Điểm TK phải đạt từ 8.0 - 10.0 điểm","Vui lòng không bỏ trống Hình ảnh minh chứng."), "Không hiển thị lỗi");
    }

    // ✅ TC06 – Lưu thông tin thành công ngành khác
    @Test(priority = 6)
    public void TC06_applyTeachingAssistant_success_NK() throws InterruptedException {
    	//Xoá các trường điểm
    	page.clearScores();
    	// Đường dẫn tới PLT.png trong project
        String projectDir = System.getProperty("user.dir");
        String filePath = projectDir + "\\resources\\PLT.png";
        // Điền các trường Điểm Trung Bình = 8.0 , Điểm rèn luyện = 80 , Điểm Tổng Kết = 9.0 , Đính kèm hình ảnh minh chứng
        page.fillForm("8.0", "80", "9.0", filePath);
        // nhấn chọn Lưu thông tin
        page.clickSave();
        Thread.sleep(1000);
        // Kiểm tra nếu thông báo lưu thông tin thành công hiển thị
        page.isSuccessMessageDisplayed();
        Thread.sleep(3000);
    }
    
    // Hàm này được gọi sau khi thực thi class này
    // Mục đích thoát khỏi trình duyệt
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
