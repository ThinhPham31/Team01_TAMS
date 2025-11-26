package TG.Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import TG.Pages.ThongBaoPage;
import TG.Pages.UngTuyenTGPage;
import TG.Pages.LoginTGPage;

public class TKThongBaoTest {
	private WebDriver driver;
    private ThongBaoPage page;
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
        page = new ThongBaoPage(driver);
        
    }
    
    // ✅ TC01 – Tìm kiếm thông báo với nội dung "Dương Dương"
    // Mục đích: Kiểm tra Hệ thống có tìm kiếm đúng và hiển thị thông báo "Không tìm thấy kết quả phù hợp."
    @Test(priority = 1)
    public void TC01_SearchTBnoResult() throws InterruptedException {
    	// Mở bảng Thông Báo
    	page.OpenTB();
    	Thread.sleep(2000);
    	// Tìm kiếm với nội dung "Dương Dương"
    	page.fill("Dương Dương");
    	// Chọn nút Tìm Kiếm
    	page.Search();
    	Thread.sleep(2000);
    	// Kiểm tra Thông báo có được hiển thị không và hiển thị với nội dung có chính xác không
    	// Nếu không trả về Thông báo lỗi
    	page.checkSearchNoResult();
    	
    }
    // ✅ TC02 – Tìm kiếm thông báo với nội dung là tiêu đề thông báo
    // Mục đích: Kiểm tra Hệ thống có tìm kiếm đúng và hiển thị những thông báo có tiêu đề đúng với mong đợi
    @Test(priority = 2)
    public void TC02_SearchTitle() throws InterruptedException {
    	// Xoá nội dung Tìm kiếm cũ
    	page.ClearTK();
    	String expectedTT1 = "Đề xuất trợ giảng";
    	String expectedTT2 = "Chi tiết công việc được cập nhật";
    	String expectedTT3 = "Ứng tuyển trợ giảng";
    	// Tìm kiếm với nội dung expectedTT1
    	page.fill(expectedTT1);
    	// Chọn nút Tìm Kiếm
    	page.Search();
    	Thread.sleep(3000);
    	// Kiểm tra Thông báo có được hiển thị không và hiển thị với nội dung có chính xác không
    	// Nếu không trả về Thông báo lỗi
    	page.checkSearchTT(expectedTT1);
    	// Xoá nội dung Tìm kiếm cũ
    	page.ClearTK();
    	// Tìm kiếm với nội dung expectedTT2
    	page.fill(expectedTT2);
    	// Chọn nút Tìm Kiếm
    	page.Search();
    	Thread.sleep(3000);
    	// Kiểm tra Thông báo có được hiển thị không và hiển thị với nội dung có chính xác không
    	// Nếu không trả về Thông báo lỗi
    	page.checkSearchTT(expectedTT2);
    	// Xoá nội dung Tìm kiếm cũ
    	page.ClearTK();
    	// Tìm kiếm với nội dung expectedTT3
    	page.fill(expectedTT3);
    	// Chọn nút Tìm Kiếm
    	page.Search();
    	Thread.sleep(3000);
    	// Kiểm tra Thông báo có được hiển thị không và hiển thị với nội dung có chính xác không
    	// Nếu không trả về Thông báo lỗi
    	page.checkSearchTT(expectedTT3);
    	
    }
 // ✅ TC03 – Tìm kiếm thông báo với nội dung là nội dung thông báo
    // Mục đích: Kiểm tra Hệ thống có tìm kiếm đúng và hiển thị những thông báo có nội dung đúng với mong đợi
    @Test(priority = 3)
    public void TC03_SearchND() throws InterruptedException {
    	// Xoá nội dung Tìm kiếm cũ
    	page.ClearTK();
    	String expectedND1 = "Dương Hoàng Long";
    	String expectedND2 = "ứng tuyển";
    	String expectedND3 = "cập nhật";
    	String expectedND4 = "Ứng tuyển trợ giảng học kỳ 251 năm học 2025-2026. Thời gian ứng tuyển từ ngày 30/11/2025 đến ngày 31/12/2025.";

    	// Tìm kiếm với nội dung expectedND1
    	page.fill(expectedND1);
    	// Chọn nút Tìm Kiếm
    	page.Search();
    	// Kiểm tra Thông báo có được hiển thị không và hiển thị với nội dung có chính xác không
    	// Nếu không trả về Thông báo lỗi
    	page.checkSearchND(expectedND1);
    	// Xoá nội dung Tìm kiếm cũ
    	page.ClearTK();
    	// Tìm kiếm với nội dung expectedND2
    	page.fill(expectedND2);
    	// Chọn nút Tìm Kiếm
    	page.Search();
    	Thread.sleep(3000);
    	// Kiểm tra Thông báo có được hiển thị không và hiển thị với nội dung có chính xác không
    	// Nếu không trả về Thông báo lỗi
    	page.checkSearchND(expectedND2);
    	// Xoá nội dung Tìm kiếm cũ
    	page.ClearTK();
    	// Tìm kiếm với nội dung expectedND3
    	page.fill(expectedND3);
    	// Chọn nút Tìm Kiếm
    	page.Search();
    	Thread.sleep(3000);
    	// Kiểm tra Thông báo có được hiển thị không và hiển thị với nội dung có chính xác không
    	// Nếu không trả về Thông báo lỗi
    	page.checkSearchND(expectedND3);
    	// Xoá nội dung Tìm kiếm cũ
    	page.ClearTK();
    	// Tìm kiếm với nội dung expectedND4
    	page.fill(expectedND4);
    	// Chọn nút Tìm Kiếm
    	page.Search();
    	Thread.sleep(3000);
    	// Kiểm tra Thông báo có được hiển thị không và hiển thị với nội dung có chính xác không
    	// Nếu không trả về Thông báo lỗi
    	page.checkSearchND(expectedND4);
    	
    }
    // Hàm này được gọi sau khi thực thi class này
    // Mục đích thoát khỏi trình duyệt
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
