package TG.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ThongBaoPage {
	WebDriver driver;
	
	// ====== Các phần tử trên trang ======
	private By Bell = By.xpath("/html/body/div[2]/main/div/nav/div/ul/li[1]/a");
	private By TimKiemTB = By.xpath("/html/body/div[2]/main/div/nav/div/ul/li[1]/div/div/div[2]/div/input");
	private By TKButton = By.xpath("/html/body/div[2]/main/div/nav/div/ul/li[1]/div/div/div[2]/div/a");
	private By NoData = By.xpath("/html/body/div[2]/main/div/nav/div/ul/li[1]/div/div/ul/div[1]/div[2]/div/div/div/li/span");
	private By TTs = By.xpath("/html/body/div[2]/main/div/nav/div/ul/li[1]/div/div/ul/div[1]/div[2]/div/div/div/li[1]/div/div[1]/a/div/div/h5");
	private By NDs = By.xpath("/html/body/div[2]/main/div/nav/div/ul/li[1]/div/div/ul/div[1]/div[2]/div/div/div/li[1]/div/div[1]/a/div/div/p");

	
	
	public ThongBaoPage(WebDriver driver) {
        this.driver = driver;
    }
	
	// ==== Actions ====
    
	// Mở Bảng Thông Báo
    public void OpenTB() throws InterruptedException {
        driver.findElement(Bell).click();
    }
    
    // Điền Nội dung tìm kiếm với dữ liệu được cấp ở TKThongBaoTest
    public void fill(String data) {
    	driver.findElement(TimKiemTB).sendKeys(data);
    }
    
    // Nhấn chọn Biểu tượng tìm kiếm
    public void Search() {
    	driver.findElement(TKButton).click();
    }
    
    // Kiểm tra thông báo "Không tìm thấy kết quả phù hợp." có được hiển thị không và so hiển thị có chính xác không
    public void checkSearchNoResult() throws InterruptedException {
    	// Kết quả mong đợi thông báo "Không tìm thấy kết quả phù hợp."
    	String expectedText = "Không tìm thấy kết quả phù hợp.";
    	// Nếu Element Thông báo được hiển thị thì
    	if (driver.findElement(NoData).isDisplayed()) {
    		// Tiến hành lấy nội dung thông báo gán vào actualText
    		String actualText = driver.findElement(NoData).getText();
    		// So sánh kết quả mong đợi và kết quả thực tế nếu True = pass , false thì trả về thông báo "Hiển thị sai thông báo" và hiển thị thông báo sai
    		Assert.assertEquals(actualText,expectedText,"Hiển thị sai thông báo - Thông báo sai: " + actualText);
    	}
    	// Nếu Element thông báo không hiển thị thì 
    	else {
    		// Trả về kết quả fail cùng thông báo "Không hiển thị thông báo: Không tìm thấy kết quả phù hợp."
    		Assert.fail("Không hiển thị thông báo: Không tìm thấy kết quả phù hợp.");
    		
    	}
    	
    	Thread.sleep(2000);
    }
    // Nhấn chọn Biểu tượng tìm kiếm
    public void ClearTK() {
    	driver.findElement(TimKiemTB).clear();
    }
    
    // Kiểm tra các kết quả tìm kiếm có được hiển thị không và có chính xác với kết quả mong đợi không
    public void checkSearchTT(String expectedTT) throws InterruptedException {
    	// Tìm các phần tử phù hợp với xpath=TTs và lưu thành một List TTList
    	List<WebElement> TTList = driver.findElements(TTs);
    	// Gán filterCorrect = true để có thể dễ dàng sử dụng trong hàm if nếu filter correct = true thì kết quả không có lỗi pass
    	// Nếu filterCorrect= false thì sẽ là fail và thông báo lỗi
    	boolean filterCorrect = true;
    	// Nếu Các Thông báo được hiển thị thì 
    	if (driver.findElement(TTs).isDisplayed()) {
    		//Tạo vòng lặp xét từng Title
    		for (WebElement TT : TTList) {
    			// Lấy Title, Gán Title vào text
                String text = TT.getText().trim();
                // Kiểm tra điều kiện nếu text không chứa expectedTT (Kết quả mong đợi khi tìm kiếm) thì
                if (!(text.contains(expectedTT))) {
                	// Chuyển đổi trạng tái filterCorrect về flase
                    filterCorrect = false;
                    //Lệnh này kiểm tra nếu filterCorrect = True thì pass, filterCorrect = false sẽ báo lỗi "Tìm kiếm sai kết quả"
            		Assert.assertTrue(filterCorrect, "Tìm kiếm sai kết quả");
                }
            }
    		
    	}
    	// Nếu các Thông báo không được hiển thị thì tiến hành
    	else {
    		// Chuyển đổi trạng thái filterCorrect thành false
    		filterCorrect = false;
    		// Lệnh này kiểm tra nếu filterCorrect = True thì pass nhưng ở đây FilterCorrect đang false nên sẽ báo lỗi "Không tìm thấy kết quả phù hợp, Tìm kiếm bị lỗi"
    		Assert.assertTrue(filterCorrect, "Không tìm thấy kết quả phù hợp, Tìm kiếm bị lỗi");
    		
    	}
    	
    }
    // Kiểm tra các kết quả tìm kiếm có được hiển thị không và có chính xác với kết quả mong đợi không
    public void checkSearchND(String expectedND) throws InterruptedException {
    	// Tìm các phần tử phù hợp với xpath=NDs và lưu thành một List NDList
    	List<WebElement> NDList = driver.findElements(NDs);
    	// Gán filterCorrect = true để có thể dễ dàng sử dụng trong hàm if nếu filter correct = true thì kết quả không có lỗi pass
    	// Nếu filterCorrect= false thì sẽ là fail và thông báo lỗi
    	boolean filterCorrect = true;
    	// Nếu Các Thông báo được hiển thị thì
    	if (driver.findElement(NDs).isDisplayed()) {
    		//Tạo vòng lặp xét từng Nội dung Thông Báo
    		for (WebElement ND : NDList) {
    			// Lấy NộiDung, Gán NộiDung vào text
                String text = ND.getText().trim();
                // Kiểm tra điều kiện nếu text không chứa expectedND (Kết quả mong đợi khi tìm kiếm) thì
                if (!(text.contains(expectedND))) {
                	// Chuyển đổi trạng tái filterCorrect về flase
                    filterCorrect = false;
                    //Lệnh này kiểm tra nếu filterCorrect = True thì pass, filterCorrect = false sẽ báo lỗi "Tìm kiếm sai kết quả"
            		Assert.assertTrue(filterCorrect, "Tìm kiếm sai kết quả, Kết quả sai: " + text);
                }
            }
    		
    	}
    	// Nếu không Thông báo lỗi "Không tìm kết quả phù hợp" thì tiến hành
    	else {
    		// Chuyển đổi trạng thái filterCorrect thành false
    		filterCorrect = false;
    		// Lệnh này kiểm tra nếu filterCorrect = True thì pass nhưng ở đây FilterCorrect đang false nên sẽ báo lỗi "Không tìm thấy kết quả phù hợp, Tìm kiếm bị lỗi"
    		Assert.assertTrue(filterCorrect, "Không tìm thấy kết quả phù hợp, Tìm kiếm bị lỗi");
    		
    	}
    	
    }

}
