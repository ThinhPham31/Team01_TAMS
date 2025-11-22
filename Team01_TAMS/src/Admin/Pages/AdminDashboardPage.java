package Admin.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Helpers.ValidateUIHelpers;
import Commons.WebUI;
import Admin.Pages.ViewTermListPage;

public class AdminDashboardPage {

	private WebDriver driver;
	private ValidateUIHelpers validateUIHelpers;
	private ViewTermListPage viewTermListPage;
	private ViewTARegistrationForm viewTARegistrationForm;
	
	//Menu Trang chủ
	private By dashboardLabelLocator = By.xpath("//*[@id=\"child-studentaffairs-studentaffairsdashboard-index\"]");
	
	//Menu Thống kê
	private By statisticLabelLocator = By.xpath("//*[@id=\"parent-thongke\"]");
	
	//Menu Thống kê trợ giảng
	private By statisticTALabelLocator = By.xpath("//*[@id=\"child-thongke-trogiang\"]");
	
	//Menu Thống kê thù lao
	private By statisticSalLabelLocator = By.xpath("//*[@id=\"child-thongke-thulao\"]");
	
	//Menu Trợ giảng
	private By TALabelLocator = By.xpath("//*[@id=\"parent-trogiang\"]");
	
	//Menu Biểu mẫu đăng ký
	private By TARegistrationFormLabelLocator = By.xpath("//*[@id=\"child-trogiang-register\"]");
	
	//Menu Đề xuất trợ giảng
	private By TASuggestionListLabelLocator = By.xpath("//*[@id=\"child-trogiang-advances\"]");
	
	//Menu Xem thông tin trợ giảng
	private By TAListLabelLocator = By.xpath("//*[@id=\"child-trogiang-dadangky\"]");
	
	//Menu Đề xuất trợ giảng
	private By TAEvaluationResultLabelLocator = By.xpath("//*[@id=\"child-trogiang-reviewtask\"]");
	
	//Menu Học kỳ, Ngành & Khoa
	private By CategoryLabelLocator = By.xpath("//*[@id=\"parent-studentaffairs-studentaffairssemesterandmajor\"]");
	
	//Menu Học kỳ
	private By CatTermListLabelLocator = By.xpath("//*[@id=\"child-studentaffairs-studentaffairssemesterandmajor-hocky\"]");
	
	// Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong class này đọc
	public AdminDashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Chọn chức năng Xem danh sách học kỳ từ menu bên trái
	public ViewTermListPage clickViewTermList() {
		//validateUIHelpers.clickElement(CatTermListLabelLocator);
		//Nhấp vào menu Danh mục Học kỳ, Ngành và Khoa
		WebUI.waitForElementClickable(driver, CategoryLabelLocator).click();
		
		//Nhấp vào menu con Học kỳ
		WebUI.waitForElementClickable(driver, CatTermListLabelLocator).click();
		
		return new ViewTermListPage(driver);
	}
	
	//Chọn chức năng Biểu mẫu đăng ký trợ giảng từ menu bên trái
	public ViewTARegistrationForm clickViewTARegistrationForm() {

	    //Nhấp vào menu Trợ giảng
	    WebUI.waitForElementClickable(driver, TALabelLocator).click();

	    //Nhấp vào menu con Biểu mẫu đăng ký
	    WebUI.waitForElementClickable(driver, TARegistrationFormLabelLocator).click();

	    //Trả về trang ViewTARegistrationForm
	    return new ViewTARegistrationForm(driver);
	}
	
	
}
