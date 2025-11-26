package TG.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class XemUngTuyenTGPage {
	WebDriver driver;
	
	// ====== Các phần tử trên trang ======
	private By TGButton = By.xpath("//*[@id=\"parent-trogiang\"]");
	private By KQDangKy = By.xpath("//*[@id=\"child-trogiang-resultapply\"]");
	private By HKButton = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[1]/select");
	private By HK253Button= By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[1]/select/option[1]");
	private By HK251Button= By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[1]/select/option[18]");
	private By NganhButton = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[1]/select");
	private By NganhCNTTth101 = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[2]/select/option[4]");
	private By NganhCNTT = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[2]/select/option[8]");
	private By NoData = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td");
	private By LHPxpath = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td[1]");
	private By MaLHP = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[1]");  
	private By TenHP = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[2]");
	private By LichHoc = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[3]/ul/li");
	private By SoTC = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[4]");
	private By TrangThai= By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[5]");
	private By TimKiem = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[1]/div[2]/div/label/input");
	private By THPcolumn = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]");
	private By LHcolumn = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td[3]");
	private By STCcolumn = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td[4]");
	private By TTcolumn = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td[5]");


	
	// ====== Constructor ======
    public XemUngTuyenTGPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // ====== Các hàm thao tác ======
    
    // Mở Dropdownlist Trợ Giảng
    public void TGDropdown() throws InterruptedException {
        driver.findElement(TGButton).click();
    }
    // Chọn Kết Quả Đăng Ký trong DropdownList Trợ Giảng
    public void ClickKQDangKy() throws InterruptedException {
    	driver.findElement(KQDangKy).click();
    	Thread.sleep(2000);
    }
    // Chọn Học Kỳ 253
    public void chonHK253() throws InterruptedException {
    	driver.findElement(HKButton).click();
    	driver.findElement(HK253Button).click();
    	Thread.sleep(2000);
    }
    // Chọn ngành CNTT TH101
    public void chonNganh101() throws InterruptedException {
    	driver.findElement(NganhButton).click();
    	driver.findElement(NganhCNTTth101).click();
    	Thread.sleep(2000);
    }
    // Chọn ngành CNTT
    public void chonNganhCNTT() throws InterruptedException {
    	driver.findElement(NganhButton).click();
    	driver.findElement(NganhCNTT).click();
    	Thread.sleep(2000);
    }
    // Kiểm tra thông báo "Không có dữ liệu để hiển thị" có được hiển thị không và so sánh với thông báo mong đợi "Không có dữ liệu để hiển thị."
    public void checkNoData() throws InterruptedException {
    	String expectedText = "Không có dữ liệu để hiển thị.";
    	if (driver.findElement(NoData).isDisplayed()) {
    		String actualText = driver.findElement(NoData).getText();
    		Assert.assertEquals(actualText,expectedText,"Hiển thị sai thông báo - Thông báo sai: " + actualText);
    	}
    	else {
    		Assert.fail("Không hiển thị thông báo: Không có dữ liệu để hiển thị ");
    		
    	}
    	
    	Thread.sleep(2000);
    }
    
    // Chọn Học Kỳ 251
    public void chonHK251() throws InterruptedException {
    	driver.findElement(HKButton).click();
    	driver.findElement(HK251Button).click();
    	Thread.sleep(2000);
    }
    
    // Kiểm tra các lớp học phần có đúng HK251 ngành CNTT TH102 thông qua Mã LHP có chứa "251" "IT"
    public void checkData() throws InterruptedException {
    	// Tìm các phần tử phù hợp với xpath=LHPxpath và lưu thành một List lhpList
    	List<WebElement> lhpList = driver.findElements(LHPxpath);
    	// Gán filterCorrect = true để có thể dễ dàng sử dụng trong hàm if nếu filter correct = true thì kết quả không có lỗi pass
    	// Nếu filterCorrect= false thì sẽ là fail và thông báo lỗi
    	boolean filterCorrect = true;
    	// Nếu Thông báo Lỗi "Không có dữ liệu để hiển thị" được hiển thị thì
    	if (driver.findElement(NoData).isDisplayed()) {
    		// Vì ô Thông báo "Không có dữ liệu để hiển thị" có xpath giống với xpath của LHPxpath nên chúng ta kiểm tra thêm một bước nội dung của NoData
    		if (driver.findElement(NoData).getText().equals("Không có dữ liệu để hiển thị")) {
    			// Chuyển đổi trạng thái filterCorrect thành false
        		filterCorrect = false;
        		// Lệnh này kiểm tra nếu filterCorrect = True thì pass nhưng ở đây FilterCorrect đang false nên sẽ báo lỗi " Không có dữ liệu để hiển thị"
        		Assert.assertTrue(filterCorrect, "Không có dữ liệu để hiển thị");
    		}
    	}
    	// Nếu Thông báo lỗi " Không có dữ liệu để hiển thị thì tiến hành
    	else {
    		//Tạo vòng lặp từng xét từng Mã LHP
    		for (WebElement lhp : lhpList) {
                String text = lhp.getText().trim();
                // Kiểm tra điều kiện nếu không chứa "251" và "IT" thì
                if (!(text.contains("251") && text.contains("IT"))) {
                	// Chuyển đổi trạng tái filterCorrect về flase
                    filterCorrect = false;
                }
            }
    		//Lệnh này kiểm tra nếu filterCorrect = True thì pass, filterCorrect = false sẽ báo lỗi "Bộ lọc không lọc đúng ngành hoặc Học Kỳ
    		Assert.assertTrue(filterCorrect, "Bộ lọc không lọc đúng ngành hoặc Học Kỳ");
    		
    	}
    	
    }
    
    public void checkInfo() throws InterruptedException {
    	// Kết quả mong đợi Mã LHP
    	String expectedMLHP = "251_71ITBS10103_01";
    	// Nếu Mã LHP được hiển thị thì
    	if (driver.findElement(MaLHP).isDisplayed()) {
    		// Lấy Mã LHP thực tế
    		String actualMLHP = driver.findElement(MaLHP).getText();
    		// So sánh Mã LHP thực tế và Mã LHP mong đợi
    		Assert.assertEquals(actualMLHP,expectedMLHP,"Hiển thị sai Mã LHP - Mã LHP Sai: " + actualMLHP);
    	}
    	// Nếu mã LHP không hiển thị thì
    	else {
    		//Trả về fail kèm thông báo không hiển thị Mã LHP
    		Assert.fail("Không hiển thị Mã LHP ");
    		
    	}
    	// Kết quả mong đợi Tên HP
    	String expectedTHP = "Nhập môn công nghệ thông tin";
    	// Nếu Tên HP được hiển thị thì
    	if (driver.findElement(TenHP).isDisplayed()) {
    		// Lấy Tên HP thực tế
    		String actualTHP = driver.findElement(TenHP).getText();
    		// So sánh Tên HP thực tế và Tên HP mong đợi
    		Assert.assertEquals(actualTHP,expectedTHP,"Hiển thị sai Tên HP - Tên HP Sai: " + actualTHP);
    	}
    	// Nếu Tên HP không hiển thị thì
    	else {
    		//Trả về fail kèm thông báo không hiển thị Tên HP
    		Assert.fail("Không hiển thị Tên HP ");
    		
    	}
    	// Kết quả mong đợi Lịch Học
    	String expectedLH = "Thứ Ba; Tiết 1 - 3; Tuần 2 - 11; Phòng CS3.F.03.06";
    	// Nếu Lịch Học được hiển thị thì
    	if (driver.findElement(LichHoc).isDisplayed()) {
    		// Lấy Lịch Học thực tế
    		String actualLH = driver.findElement(LichHoc).getText();
    		// So sánh Lịch Học thực tế và Lịch Học mong đợi
    		Assert.assertEquals(actualLH,expectedLH,"Hiển thị sai Lịch Học - Lịch Học Sai: " + actualLH);
    	}
    	// Nếu Lịch Học không hiển thị thì
    	else {
    		//Trả về fail kèm thông báo không hiển thị Lịch Học
    		Assert.fail("Không hiển thị Lịch Học ");
    		
    	}
    	// Kết quả mong đợi Số Tín Chỉ
    	String expectedTC = "3";
    	// Nếu Số TC được hiển thị thì
    	if (driver.findElement(SoTC).isDisplayed()) {
    		// Lấy Số TC thực tế
    		String actualTC = driver.findElement(SoTC).getText();
    		// So sánh Số TC thực tế và Số TC mong đợi
    		Assert.assertEquals(actualTC,expectedTC,"Hiển thị sai Số TC - Số TC Sai: " + actualTC);
    	}
    	// Nếu Số TC không hiển thị thì
    	else {
    		//Trả về fail kèm thông báo không hiển thị Số TC
    		Assert.fail("Không hiển thị Số TC ");
    		
    	}
    	// Kết quả mong đợi Trạng Thái
    	String expectedTT = "Chưa phân công";
    	// Nếu Số Trạng Thái được hiển thị thì
    	if (driver.findElement(TrangThai).isDisplayed()) {
    		// Lấy Trạng Thái thực tế
    		String actualTT = driver.findElement(TrangThai).getText();
    		// So sánh Trạng Thái thực tế và Trạng Thái mong đợi
    		Assert.assertEquals(actualTT,expectedTT,"Hiển thị sai Trạng thái - Trạng Thái Sai: " + actualTT);
    	}
    	// Nếu Trạng Thái không hiển thị thì
    	else {
    		//Trả về fail kèm thông báo không hiển thị Trạng Thái
    		Assert.fail("Không hiển thị Trạng Thái ");
    		
    	}
    }
    // Điền Mã LHP với mã được cấp ở TKUngTuyenTest
    public void fill(String data) {
    	driver.findElement(TimKiem).sendKeys(data);
    }
    
    // Kiểm tra các mã Lớp Học Phần sau khi tìm kiếm có đúng với "251_71ITBS10103_01"
    public void checkMLHP() throws InterruptedException {
    	// Tìm các phần tử phù hợp với xpath=LHPxpath và lưu thành một List lhpList
    	List<WebElement> lhpList = driver.findElements(LHPxpath);
    	// Gán filterCorrect = true để có thể dễ dàng sử dụng trong hàm if nếu filter correct = true thì kết quả không có lỗi pass
    	// Nếu filterCorrect= false thì sẽ là fail và thông báo lỗi
    	boolean filterCorrect = true;
    	// Nếu Thông báo Lỗi "Không tìm thấy dữ liệu nào" được hiển thị thì
    	if (driver.findElement(NoData).isDisplayed()) {
    		// Vì ô Thông báo "Không tìm thấy dữ liệu nào" có xpath giống với xpath của LHPxpath nên chúng ta kiểm tra thêm một bước nội dung của NoData
    		if (driver.findElement(NoData).getText().equals("Không tìm thấy dữ liệu nào")) {
    			// Chuyển đổi trạng thái filterCorrect thành false
        		filterCorrect = false;
        		// Lệnh này kiểm tra nếu filterCorrect = True thì pass nhưng ở đây FilterCorrect đang false nên sẽ báo lỗi " Không tìm thấy dữ liệu nào"
        		Assert.assertTrue(filterCorrect, "Không tìm thấy dữ liệu nào");
    		}
    	}
    	// Nếu không Thông báo lỗi "Không tìm thấy dữ liệu nào" thì tiến hành
    	else {
    		//Tạo vòng lặp từng xét từng Mã LHP
    		for (WebElement lhp : lhpList) {
                String text = lhp.getText().trim();
                // Kiểm tra điều kiện nếu không chứa "251_71ITBS10103_01"
                if (!(text.contains("251_71ITBS10103_01"))) {
                	// Chuyển đổi trạng tái filterCorrect về flase
                    filterCorrect = false;
                    //Lệnh này kiểm tra nếu filterCorrect = True thì pass, filterCorrect = false sẽ báo lỗi "Tìm kiếm sai kết quả"
            		Assert.assertTrue(filterCorrect, "Tìm kiếm sai kết quả");
                }
            }
    		
    		
    	}
    	
    }
    
    // Kiểm tra thông báo "Không tìm thấy dữ liệu nào" có được hiển thị không và so sánh với thông báo mong đợi "Không tìm thấy dữ liệu nào"
    public void checkSearchNoData() throws InterruptedException {
    	String expectedText = "Không tìm thấy dữ liệu nào";
    	if (driver.findElement(NoData).isDisplayed()) {
    		String actualText = driver.findElement(NoData).getText();
    		Assert.assertEquals(actualText,expectedText,"Hiển thị sai thông báo - Thông báo sai: " + actualText);
    	}
    	else {
    		Assert.fail("Không hiển thị thông báo: Không tìm thấy dữ liệu nào ");
    		
    	}
    	
    	Thread.sleep(2000);
    }
    
    // Kiểm tra các Tên Học Phần sau khi tìm kiếm có đúng với "Nhập môn công nghệ thông tin"
    public void checkTHP() throws InterruptedException {
    	// Tìm các phần tử phù hợp với xpath=THPcolumn và lưu thành một List lhpList
    	List<WebElement> lhpList = driver.findElements(THPcolumn);
    	// Gán filterCorrect = true để có thể dễ dàng sử dụng trong hàm if nếu filter correct = true thì kết quả không có lỗi pass
    	// Nếu filterCorrect= false thì sẽ là fail và thông báo lỗi
    	boolean filterCorrect = true;
    	// Nếu Thông báo Lỗi "Không tìm thấy dữ liệu nào" được hiển thị thì
    	if (driver.findElement(NoData).isDisplayed()) {
    		// Vì ô Thông báo "Không tìm thấy dữ liệu nào" có xpath giống với xpath của LHPxpath nên chúng ta kiểm tra thêm một bước nội dung của NoData
    		if (driver.findElement(NoData).getText().equals("Không tìm thấy dữ liệu nào")) {
    			// Chuyển đổi trạng thái filterCorrect thành false
        		filterCorrect = false;
        		// Lệnh này kiểm tra nếu filterCorrect = True thì pass nhưng ở đây FilterCorrect đang false nên sẽ báo lỗi " Không tìm thấy dữ liệu nào"
        		Assert.assertTrue(filterCorrect, "Không tìm thấy dữ liệu nào");
    		}
    	}
    	// Nếu không Thông báo lỗi "Không tìm thấy dữ liệu nào" thì tiến hành
    	else {
    		//Tạo vòng lặp từng xét từng Tên HP
    		for (WebElement lhp : lhpList) {
                String text = lhp.getText().trim();
                // Kiểm tra điều kiện nếu không chứa "Nhập môn công nghệ thông tin" thì
                if (!(text.contains("Nhập môn công nghệ thông tin"))) {
                	// Chuyển đổi trạng tái filterCorrect về flase
                    filterCorrect = false;
                    //Lệnh này kiểm tra nếu filterCorrect = True thì pass, filterCorrect = false sẽ báo lỗi "Tìm kiếm sai kết quả"
            		Assert.assertTrue(filterCorrect, "Tìm kiếm sai kết quả");
                }
            }
    		
    	}
    	
    }
    
    // Kiểm tra các Lịch Học sau khi tìm kiếm có đúng với "Thứ Ba; Tiết 1 - 3; Tuần 2 - 11; Phòng CS3.F.03.06"
    public void checkLH() throws InterruptedException {
    	// Tìm các phần tử phù hợp với xpath=LHcolumn và lưu thành một List lhpList
    	List<WebElement> lhpList = driver.findElements(LHcolumn);
    	// Gán filterCorrect = true để có thể dễ dàng sử dụng trong hàm if nếu filter correct = true thì kết quả không có lỗi pass
    	// Nếu filterCorrect= false thì sẽ là fail và thông báo lỗi
    	boolean filterCorrect = true;
    	// Nếu Thông báo Lỗi "Không tìm thấy dữ liệu nào" được hiển thị thì
    	if (driver.findElement(NoData).isDisplayed()) {
    		// Vì ô Thông báo "Không tìm thấy dữ liệu nào" có xpath giống với xpath của LHPxpath nên chúng ta kiểm tra thêm một bước nội dung của NoData
    		if (driver.findElement(NoData).getText().equals("Không tìm thấy dữ liệu nào")) {
    			// Chuyển đổi trạng thái filterCorrect thành false
        		filterCorrect = false;
        		// Lệnh này kiểm tra nếu filterCorrect = True thì pass nhưng ở đây FilterCorrect đang false nên sẽ báo lỗi " Không tìm thấy dữ liệu nào"
        		Assert.assertTrue(filterCorrect, "Không tìm thấy dữ liệu nào");
    		}
    	}
    	// Nếu không Thông báo lỗi "Không tìm thấy dữ liệu nào" thì tiến hành
    	else {
    		//Tạo vòng lặp từng xét từng Lịch Học
    		for (WebElement lhp : lhpList) {
                String text = lhp.getText().trim();
                // Kiểm tra điều kiện nếu không chứa "Nhập môn công nghệ thông tin" thì
                if (!(text.contains("Nhập môn công nghệ thông tin"))) {
                	// Chuyển đổi trạng tái filterCorrect về flase
                    filterCorrect = false;
                    //Lệnh này kiểm tra nếu filterCorrect = True thì pass, filterCorrect = false sẽ báo lỗi "Tìm kiếm sai kết quả"
            		Assert.assertTrue(filterCorrect, "Tìm kiếm sai kết quả");
            		
                }
            }
    		
    	}
    	
    }
    
    // Kiểm tra các Số TC sau khi tìm kiếm có đúng với "3"
    public void checkSTC() throws InterruptedException {
    	// Tìm các phần tử phù hợp với xpath=STCcolumn và lưu thành một List lhpList
    	List<WebElement> lhpList = driver.findElements(STCcolumn);
    	// Gán filterCorrect = true để có thể dễ dàng sử dụng trong hàm if nếu filter correct = true thì kết quả không có lỗi pass
    	// Nếu filterCorrect= false thì sẽ là fail và thông báo lỗi
    	boolean filterCorrect = true;
    	// Nếu Thông báo Lỗi "Không tìm thấy dữ liệu nào" được hiển thị thì
    	if (driver.findElement(NoData).isDisplayed()) {
    		// Vì ô Thông báo "Không tìm thấy dữ liệu nào" có xpath giống với xpath của LHPxpath nên chúng ta kiểm tra thêm một bước nội dung của NoData
    		if (driver.findElement(NoData).getText().equals("Không tìm thấy dữ liệu nào")) {
    			// Chuyển đổi trạng thái filterCorrect thành false
        		filterCorrect = false;
        		// Lệnh này kiểm tra nếu filterCorrect = True thì pass nhưng ở đây FilterCorrect đang false nên sẽ báo lỗi " Không tìm thấy dữ liệu nào"
        		Assert.assertTrue(filterCorrect, "Không tìm thấy dữ liệu nào");
    		}
    	}
    	// Nếu không Thông báo lỗi "Không tìm thấy dữ liệu nào" thì tiến hành
    	else {
    		//Tạo vòng lặp từng xét từng Số TC
    		for (WebElement lhp : lhpList) {
                String text = lhp.getText().trim();
                // Kiểm tra điều kiện nếu không chứa "3" thì
                if (!(text.contains("3"))) {
                	// Chuyển đổi trạng tái filterCorrect về flase
                    filterCorrect = false;
                    //Lệnh này kiểm tra nếu filterCorrect = True thì pass, filterCorrect = false sẽ báo lỗi "Tìm kiếm sai kết quả"
            		Assert.assertTrue(filterCorrect, "Tìm kiếm sai kết quả");
            		
                }
            }
    		
    	}
    	
    }
    
 // Kiểm tra các Trạng Thái sau khi tìm kiếm có đúng với "Chưa phân công"
    public void checkTT() throws InterruptedException {
    	// Tìm các phần tử phù hợp với xpath=STCcolumn và lưu thành một List lhpList
    	List<WebElement> lhpList = driver.findElements(TTcolumn);
    	// Gán filterCorrect = true để có thể dễ dàng sử dụng trong hàm if nếu filter correct = true thì kết quả không có lỗi pass
    	// Nếu filterCorrect= false thì sẽ là fail và thông báo lỗi
    	boolean filterCorrect = true;
    	// Nếu Thông báo Lỗi "Không tìm thấy dữ liệu nào" được hiển thị thì
    	if (driver.findElement(NoData).isDisplayed()) {
    		// Vì ô Thông báo "Không tìm thấy dữ liệu nào" có xpath giống với xpath của LHPxpath nên chúng ta kiểm tra thêm một bước nội dung của NoData
    		if (driver.findElement(NoData).getText().equals("Không tìm thấy dữ liệu nào")) {
    			// Chuyển đổi trạng thái filterCorrect thành false
        		filterCorrect = false;
        		// Lệnh này kiểm tra nếu filterCorrect = True thì pass nhưng ở đây FilterCorrect đang false nên sẽ báo lỗi " Không tìm thấy dữ liệu nào"
        		Assert.assertTrue(filterCorrect, "Không tìm thấy dữ liệu nào");
    		}
    	}
    	// Nếu không Thông báo lỗi "Không tìm thấy dữ liệu nào" thì tiến hành
    	else {
    		//Tạo vòng lặp từng xét từng Trạng Thái
    		for (WebElement lhp : lhpList) {
                String text = lhp.getText().trim();
                // Kiểm tra điều kiện nếu không chứa "Chưa phân công" thì
                if (!(text.contains("Chưa phân công"))) {
                	// Chuyển đổi trạng tái filterCorrect về flase
                    filterCorrect = false;
                    //Lệnh này kiểm tra nếu filterCorrect = True thì pass, filterCorrect = false sẽ báo lỗi "Tìm kiếm sai kết quả"
            		Assert.assertTrue(filterCorrect, "Tìm kiếm sai kết quả");
                }
            }
    		
    	}
    	
    }
}
