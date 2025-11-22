package TG.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import TG.Pages.XemUngTuyenTGPage;
import TG.Pages.LoginTGPage;

public class TKUngTuyenTest {
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
    // ✅ TC01 – Hệ thống cho phép lọc Học kì 251 ngành CNTT và tìm kiếm Mã LHP "251_71ITBS10103_0201"
    //Mục đích: Kiểm tra rằng hệ thống tìm kiếm mã LHP có chính xác không
    @Test(priority = 1)
    public void TC01_search_01() throws InterruptedException {
        // Mở Dropdown list Trợ Giảng
    	page.TGDropdown();
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 251
    	page.chonHK251();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin
    	page.chonNganhCNTT();
    	// Nhập dữ liệu tìm kiếm Mã LHP : "251_71ITBS10103_0201"
    	page.fill("251_71ITBS10103_0201");
    	// Kiểm tra hệ thống có hiển thị kết quả không và có đúng với Mã LHP "251_71ITBS10103_0201"
    	page.checkMLHP();
    	Thread.sleep(2000);
    } 
    
    // ✅ TC02 - Hệ thống cho phép lọc Học kì 251 ngành CNTT và tìm kiếm  "xyz"
    // Mục Đích: Kiểm tra rằng hệ thống thông báo "Không Tìm Thấy dữ liệu nào"
    @Test(priority = 2)
    public void TC02_search_02() throws InterruptedException {
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 251
    	page.chonHK251();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin
    	page.chonNganhCNTT();
    	// Nhập dữ liệu tìm kiếm : "xyz"
    	page.fill("xyz");
    	// Kiểm tra hệ thống có hiển thị "Không Tìm thấy dữ liệu nào"
    	page.checkSearchNoData();
    	Thread.sleep(2000);
    } 
    // ✅ TC03 - Hệ thống cho phép lọc HK251 ngành CNTT và tìm kiếm Tên HP "Nhập môn công nghệ thông tin"
    // Mục Đích: Kiểm tra rằng hệ thống tìm kiếm Tên HP có chính xác không
    @Test(priority = 3)
    public void TC03_search_03() throws InterruptedException {
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 251
    	page.chonHK251();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin
    	page.chonNganhCNTT();
    	// Nhập dữ liệu tìm kiếm Tên HP : "Nhập môn công nghệ thông tin"
    	page.fill("Nhập môn công nghệ thông tin");
    	// Kiểm tra hệ thống có hiển thị kết quả không và có đúng với TênHP "Nhập môn công nghệ thông tin"
    	page.checkTHP();
    	Thread.sleep(2000);
    } 
    // ✅ TC04 - Hệ thống cho phép lọc HK251 ngành CNTT và tìm kiếm Lịch Học "Thứ Năm; Tiết 10 - 12; Tuần 3 - 12; Phòng CS3.F.12.05"
    // Mục Đích: Kiểm tra rằng hệ thống tìm kiếm Lịch Học có chính xác không
    @Test(priority = 4)
    public void TC04_search_04() throws InterruptedException {
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 251
    	page.chonHK251();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin
    	page.chonNganhCNTT();
    	// Nhập dữ liệu tìm kiếm Lịch Học : "Thứ Năm; Tiết 10 - 12; Tuần 3 - 12; Phòng CS3.F.12.05"
    	page.fill("Thứ Năm; Tiết 10 - 12; Tuần 3 - 12; Phòng CS3.F.12.05");
    	// Kiểm tra hệ thống có hiển thị kết quả không và có đúng với Lịch Học "Thứ Năm; Tiết 10 - 12; Tuần 3 - 12; Phòng CS3.F.12.05"
    	page.checkLH();
    	Thread.sleep(2000);
    } 
    // ✅ TC05 - Hệ thống cho phép lọc HK251 ngành CNTT và tìm kiếm Số TC "3"
    // Mục Đích: Kiểm tra rằng hệ thống tìm kiếm Số Tín Chỉ có chính xác không
    @Test(priority = 5)
    public void TC05_search_05() throws InterruptedException {
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 251
    	page.chonHK251();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin
    	page.chonNganhCNTT();
    	// Nhập dữ liệu tìm kiếm Số TC : "3"
    	page.fill("3");
    	// Kiểm tra hệ thống có hiển thị kết quả không và có đúng với Số TC "3"
    	page.checkSTC();
    	Thread.sleep(2000);
    } 
    
    // ✅ TC06 - Hệ thống cho phép lọc HK251 ngành CNTT và tìm kiếm Trạng Thái "Chưa phân công"
    // Mục Đích: Kiểm tra rằng hệ thống tìm kiếm Trạng Thái có chính xác không
    @Test(priority = 6)
    public void TC06_search_06() throws InterruptedException {
    	// Chọn Kết quả đăng ký
    	page.ClickKQDangKy();
    	// Mở Dropdown list học kỳ chọn học kỳ 251
    	page.chonHK251();
    	// Mở Dropdown list Ngành chọn ngành Công Nghệ Thông Tin
    	page.chonNganhCNTT();
    	// Nhập dữ liệu tìm kiếm Trạng Thái : "Chưa phân công"
    	page.fill("Chưa phân công");
    	// Kiểm tra hệ thống có hiển thị kết quả không và có đúng với Trạng Thái : "Chưa phân công"
    	page.checkTT();
    	Thread.sleep(2000);
    } 
    
    // Hàm này được gọi sau khi thực thi class này
    // Mục đích thoát khỏi trình duyệt
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
