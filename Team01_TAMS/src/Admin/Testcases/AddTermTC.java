package Admin.Testcases;

import static org.testng.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.devtools.v139.runtime.model.ExceptionThrown;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Commons.InitiationTest;
import Admin.Pages.AddTermPage;
import Admin.Pages.AdminDashboardPage;
import Admin.Pages.ViewTermListPage;
import Helpers.authenSupport;
import io.github.bonigarcia.wdm.WebDriverManager;
import Helpers.ValidateUIHelpers;

public class AddTermTC extends InitiationTest {
	private WebDriver driver;
	private AddTermPage addtermPage;
	private AdminDashboardPage adminDashboardPage;
	private ViewTermListPage viewTermListPage;
	
	private ValidateUIHelpers validateUIHelpers;
	
	@BeforeClass
	public void setUp() {
		driver = getDriver();
		if(driver == null){
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        // cấu hình cơ bản
	        driver.manage().window().maximize();
	    }
		//Đăng nhập vào website Quản lý trợ giảng
		authenSupport authen = new authenSupport(driver);
		
		//Điều hướng đến giao diện trang chủ của Admin sau khi đăng nhập thành công
		adminDashboardPage = authen.loginWithAdmin();
		
		//Đợi trang tải xong
		validateUIHelpers = new ValidateUIHelpers(driver);
		validateUIHelpers.waitForPageLoaded();
		
		//Điều hướng đến giao diện trang Xem danh sách học kỳ sau khi nhấp vào menu Học kỳ bên tay trái
		viewTermListPage = adminDashboardPage.clickViewTermList();
		
		//Mở giao diện trang Thêm học kỳ mới
		addtermPage = viewTermListPage.openAddTermForm();
		
		// Đợi cho modal form addTerm hiển thị
	    new WebDriverWait(driver, 5).until(
	            ExpectedConditions.visibilityOfElementLocated(By.id("tenhocky"))
	        );
		
	}
	
	/*
	 * Test Objective: Xác nhận hệ thống hiển thị thông báo thành công sau khi người dùng nhập đầy đủ và đúng định dạng 
	 * các trường thông tin bắt buộc và với giá trị cận biên dưới của trường thông tin Học kỳ.
	*/
	@Test (priority = 1)
	public void TC01_SaveTermSuccessfulWithLB() throws InterruptedException {
		
	    // Gọi hàm fillDataTerm
	    addtermPage.fillDataTerm("2028", "2", "2028-02-22");
	    
	    addtermPage.clickLuuThongTin();
	}

	/*
	*************** KIỂM TRA CÁC KỊCH BẢN LooknFeel **************
	*/  
	 	
	/*
	 * Test Objective: Xác nhận hệ thống mở thành công giao diện Thêm học kỳ và hiển thị chính xác tiêu đề form 
	*/
	@Test(priority = 2)
	public void TC02_VerifyOpenAddTermFormSuccessfully(){
		Assert.assertEquals(addtermPage.getAddTermPageText(), "Thêm học kỳ mới");
	}

}

