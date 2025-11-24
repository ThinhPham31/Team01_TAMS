package TG.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import TG.Pages.XemUngTuyenTGPage;
import TG.Pages.LoginTGPage;

public class XemUngTuyenTGTest {
    private WebDriver driver;
    private XemUngTuyenTGPage page;
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
        page = new XemUngTuyenTGPage(driver);
        
    }
    // ✅ TC01 – Hệ thống cho phép lọc Học kì 253 ngành CNTT TH101 và hiển thị thông báo không có đăng ký nào
    //Mục đích: Kiểm tra hiển thị thông báo "Không có dữ liệu để hiển thị" khi Học kỳ và Ngành không có lớp
    @Test(priority = 1)
    public void TC01_HK253_CNTT101_NoData() throws InterruptedException {
        // Mở Dropdown list Trợ Giảng
    	page.TGDropdown();
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 253
    	page.chonHK253();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin TH101
    	page.chonNganh101();
    	// Kiểm tra thông báo "Không có dữ liệu để hiển thị" có được hiển thị không
    	page.checkNoData();
    	Thread.sleep(2000);
    } 
    
    // ✅ TC02 - Hệ thống cho phép lọc HK251 ngành CNTT TH101 và hiển thị các LHP thuộc HK251 ngành CNTT TH101
    // Mục Đích: Kiểm tra rằng hệ thống cho phép lọc theo HK và Ngành và kiểm tra xem các lớp có phải thuộc HK251 ngành CNTT TH101 hay không
    @Test(priority = 2)
    public void TC02_HK251_CNTT102_checkLHP() throws InterruptedException {
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 251
    	page.chonHK251();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin TH101
    	page.chonNganh101();
    	// Kiểm tra các lớp học phần có đúng HK251 ngành CNTT thông qua Mã LHP có chứa "251" "IT"
    	page.checkData();
    	Thread.sleep(2000);
    } 
    // ✅ TC03 - Hệ thống cho phép lọc HK251 ngành CNTT TH101 và hiển thị Đầy đủ thông tin của LHP : Mã LHP, Tên HP, Lịch học, Số Tín Chỉ, Trạng Thái
    // Mục Đích: Kiểm tra hệ thống có hiển thị đầy đủ thông tin của Lớp Học Phần không. Nếu Không báo lỗi và kiểm tra các thông tin có đúng với kết quả mong đợi không
    @Test(priority = 3)
    public void TC03_HK251_CNTT102_checkInfo() throws InterruptedException {
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 251
    	page.chonHK251();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin TH101
    	page.chonNganh101();
    	// Kiểm tra các thông tin Mã LHP, Tên HP, Lịch học, Số Tín Chỉ, Trạng Thái có được hiển thị không và có hiển thị đúng với kết quả mong đợi:
    	// Mã LHP : 251_71ITBS10103_01 , Tên HP : Nhập môn công nghệ thông tin , Lịch Học : Thứ Ba; Tiết 1 - 3; Tuần 2 - 11; Phòng CS3.F.03.06; Phòng CS3.F.12.05
    	// Số TC : 3 , Trạng Thái : chưa duyệt
    	// Nếu Không hiển thị hoặc hiển thị sai thì báo lỗi
    	page.checkInfo();
    	Thread.sleep(2000);
    } 
 // Hàm này được gọi sau khi thực thi class này
    // Mục đích thoát khỏi trình duyệt
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
