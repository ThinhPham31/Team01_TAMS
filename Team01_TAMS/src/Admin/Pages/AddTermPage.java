package Admin.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddTermPage {
	private WebDriver driver;
	
	private By headerPageText = By.xpath("//*[@id=\"themmoiTitle\"]");
	
	//Tên học kỳ
	private By termLabelLocator = By.xpath("//*[@id=\"themmoi\"]/div/div/div[2]/div/div[1]/div/label");
    private By termInputLocator = By.xpath("//*[@id=\"tenhocky\"]");
    
    //Năm bắt đầu
    private By startYearLabelLocator = By.xpath("//*[@id=\"themmoi\"]/div/div/div[2]/div/div[2]/div/label");
    private By startYearDropdownLocator = By.xpath("//*[@id=\"nambatdau\"]");
    
    //Năm kết thúc
    private By endYearLabelLocator = By.xpath("//label[@for='namketthuc' and text()='Năm kết thúc: ']");
    private By endYearDropdownLocator = By.xpath("//*[@id=\"namketthuc\"]");
    
    //Ngày bắt đầu
    private By startDateLabelLocator = By.xpath("//*[@id=\"themmoi\"]/div/div/div[2]/div/div[4]/div/label");
    private By startDateInputLocator = By.xpath("//*[@id=\"ngaybatdau\"]");

    //Nút Lưu & Đóng
    private final By saveButtonLocator = By.xpath("//*[@id=\"btnSubmit\"]");
    private final By cancelButtonLocator = By.xpath("//*[@id=\"btnClose\"]");
    
    private By successfulMessageLocator = By.xpath("//*[@id=\"swal2-title\"]");
    
	//Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong class này đọc
	public AddTermPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getAddTermPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}
/*
	public boolean verifyAddTermPageTitle() {
		String expectedTitle = "Học kỳ";
		return getAddTermPageTitle().equals(expectedTitle);
	}
*/	
	//Get header text 
	public String getAddTermPageText() {
		String pageText = driver.findElement(headerPageText).getText();
		return pageText;
	}
	
	//Input term name
    public void enterTermName(String termName) {
        WebElement input = driver.findElement(termInputLocator);
        input.clear();
        input.sendKeys(termName);
    }
    
	// Chọn năm bắt đầu
    public void selectStartYear(String year) {
        WebElement dropdown = driver.findElement(startYearDropdownLocator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(year);

        // Selenium 3 compatible wait
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                String value = d.findElement(termInputLocator).getAttribute("value");
                return value != null && !value.trim().isEmpty();
            }
        });
    }
	
	// Nhập ngày bắt đầu
    public void selectStartDate(String yyyymmdd) {
        String[] parts = yyyymmdd.split("-");
        int targetYear = Integer.parseInt(parts[0]);
        int targetMonth = Integer.parseInt(parts[1]); 
        int targetDay = Integer.parseInt(parts[2]);

        // Step 1: mở calendar
        WebElement dateInput = driver.findElement(startDateInputLocator);
        dateInput.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".flatpickr-calendar")
        ));

        // Step 2: đưa calendar lên trên modal nếu bị che
        ((JavascriptExecutor) driver).executeScript(
            "document.querySelector('.flatpickr-calendar').style.zIndex='999999';"
        );

        // Step 3: lấy năm hiện tại
        WebElement yearInput = driver.findElement(By.cssSelector("input.cur-year"));
        int currentYear = Integer.parseInt(yearInput.getAttribute("value"));

        // Step 4: thay đổi năm bằng arrowUp / arrowDown
        if (currentYear < targetYear) {
            WebElement up = driver.findElement(By.cssSelector(".numInputWrapper .arrowUp"));
            for (int i = currentYear; i < targetYear; i++) {
                up.click();
            }
        } else if (currentYear > targetYear) {
            WebElement down = driver.findElement(By.cssSelector(".numInputWrapper .arrowDown"));
            for (int i = currentYear; i > targetYear; i--) {
                down.click();
            }
        }

        // Step 5: chọn tháng bằng select
        WebElement monthSelect = driver.findElement(By.cssSelector("select.flatpickr-monthDropdown-months"));
        monthSelect.click();

        WebElement monthOption = driver.findElement(By.cssSelector(
                "select.flatpickr-monthDropdown-months option[value='" + (targetMonth - 1) + "']"
        ));
        monthOption.click();

        // Step 6: chọn ngày theo text
        WebElement dayBtn = driver.findElement(By.xpath(
                "//span[contains(@class,'flatpickr-day') and text()='" + targetDay + "']"
        ));
        dayBtn.click();
    }

    
    /*
     * 4. Hàm appendTermName: Lấy value hiện tại và thêm ký tự vào cuối
     */
    public void appendTermName(String extraChar) {
        WebElement termInput = driver.findElement(termInputLocator);

        // Đặt con trỏ về cuối value
        termInput.sendKeys(Keys.END);

        // Gõ thêm 1 ký tự giống hành vi người dùng
        termInput.sendKeys(extraChar);
    }
	
	//Clear all input data
	public void resetInputData() {
		
	}
	
	//Click on Luu thông tin button
	public void clickLuuThongTin() {
		driver.findElement(saveButtonLocator).click(); 
	}
	
	//Click on Đóng button
	public void clickHuyLuu () {
		driver.findElement(cancelButtonLocator).click();
	}
}
