package TG.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class UngTuyenTGPage {
    private WebDriver driver;

    // ==== Locators ====
    private By applyButton = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[6]/span/a");
    private By avgScoreField = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[7]/div/input");
    private By conductScoreField = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[8]/div/input");
    private By finalScoreField = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[9]/div/input");
    private By uploadFileInput = By.cssSelector("input[type='file']");
    private By saveButton = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[3]/button[3]");
    private By successMessage = By.xpath("/html/body/div[6]/div/h2");
    private By errorMessages1 = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[7]/div/span");
    private By errorMessages2 = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[8]/div/span");
    private By errorMessages3 = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[9]/div/span");
    private By errorMessages4 = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[10]/div/span");
    private By TroGiangButton = By.xpath("//*[@id=\"parent-trogiang\"]");
    private By DangKyButton = By.xpath("//*[@id=\"child-trogiang-apply\"]");
    private By ListNganh = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[2]/select");
    private By ChonNganhDB = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[2]/select/option[6]");
    private By ChonNganhKhac = By.xpath("");
    private By UpdateButton = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[6]/span/a");
    private By DeletePhotoButton = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[10]/div/div/div/a/i");
    private By HuyDangKyButton = By.id("btnHuydangky");
    private By KhongHuyButton = By.xpath("/html/body/div[7]/div/div[6]/button[3]");
    private By HuyNgayButton = By.xpath("/html/body/div[8]/div/div[6]/button[1]");
    private By DongButton = By.id("btnClose");
    private By CancelsuccessMessage = By.id("swal2-title");
    private By ListHK = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[1]/select");
    private By ChonHK = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[1]/select/option[98]");
    

    public UngTuyenTGPage(WebDriver driver) {
        this.driver = driver;
    }

    // ==== Actions ====
    
    // Các thao tác mở form ứng tuyển ngành Đặc Biệt (HK251 Ngành CNTT TH102)
    public void openApplyFormNDB() throws InterruptedException {
    	driver.findElement(TroGiangButton).click();
    	driver.findElement(DangKyButton).click();
    	Thread.sleep(2000);
    	driver.findElement(ListHK).click();
    	driver.findElement(ChonHK).click();
    	Thread.sleep(2000);
    	driver.findElement(ListNganh).click();
    	driver.findElement(ChonNganhDB).click();
    	Thread.sleep(500);
        driver.findElement(applyButton).click();
        Thread.sleep(1000);
    }
    
    // Các thao tác mở form ứng tuyển ngành khác
    public void openApplyFormNK() throws InterruptedException {
    	driver.findElement(TroGiangButton).click();
    	driver.findElement(DangKyButton).click();
    	Thread.sleep(2000);
    	driver.findElement(ListHK).click();
    	driver.findElement(ChonHK).click();
    	Thread.sleep(2000);
    	driver.findElement(ListNganh).click();
    	driver.findElement(ChonNganhKhac).click();
    	Thread.sleep(500);
        driver.findElement(applyButton).click();
        Thread.sleep(1000);
    }

    // Các thao tác mở Cập nhật ứng tuyển ngành Đặc Biệt (HK251 ngành CNTT TH102
    public void updateApplyFormNDB() throws InterruptedException {
    	driver.findElement(TroGiangButton).click();
    	driver.findElement(DangKyButton).click();
    	Thread.sleep(2000);
    	driver.findElement(ListHK).click();
    	driver.findElement(ChonHK).click();
    	Thread.sleep(2000);
    	driver.findElement(ListNganh).click();
    	driver.findElement(ChonNganhDB).click();
    	Thread.sleep(500);
        driver.findElement(UpdateButton).click();
        Thread.sleep(1000);
    }
    
    // Các thao tác mở Cập nhật form ứng tuyển ngành khác
    public void updateApplyFormNK() throws InterruptedException {
    	driver.findElement(TroGiangButton).click();
    	driver.findElement(DangKyButton).click();
    	Thread.sleep(2000);
    	driver.findElement(ListNganh).click();
    	driver.findElement(ChonNganhKhac).click();
    	Thread.sleep(500);
        driver.findElement(UpdateButton).click();
        Thread.sleep(1000);
    }
    
    // Các thao tác Điền form ứng tuyển với dữ liệu được cấp ở UngTuyenTGTest
    public void fillForm(String avg, String conduct, String finalScore, String filePath) {
        if (avg != null) driver.findElement(avgScoreField).sendKeys(avg);
        if (conduct != null) driver.findElement(conductScoreField).sendKeys(conduct);
        if (finalScore != null) driver.findElement(finalScoreField).sendKeys(finalScore);
        if (filePath != null) driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    // Nhấn chọn Lưu thông tin ở Form ứng tuyển trợ giảng
    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    // Kiểm tra nếu hệ thống thông báo Ứng Tuyển Thành Công
    public void isSuccessMessageDisplayed() throws InterruptedException {
    	Thread.sleep(1000);
    	if (driver.findElement(successMessage).isDisplayed()) {
    		System.out.println("Ứng Tuyển Thành Công được hiển thị");
    	} else {
    		System.out.println("Ứng Tuyển Thành Công không được hiển thị");
    	}
        //return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    // Kiểm tra nếu có bất kì trường thông tin nào bỏ trống mà không hiện thông báo lỗi, với thông báo lỗi mong đợi được cấp ở UngTuyenTGTest và thông báp lỗi thực tế 
    public boolean hasErrorMessage(String expectedText1, String expectedText2, String expectedText3, String expectedText4) {
    	try {
            WebElement msg1 = driver.findElement(errorMessages1);
            WebElement msg2 = driver.findElement(errorMessages2);
            WebElement msg3 = driver.findElement(errorMessages3);
            WebElement msg4 = driver.findElement(errorMessages4);

            boolean match1 = msg1.getText().contains(expectedText1);
            boolean match2 = msg2.getText().contains(expectedText2);
            boolean match3 = msg3.getText().contains(expectedText3);
            boolean match4 = msg4.getText().contains(expectedText4);

            return match1 && match2 && match3 && match4;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Xoá các trường điểm Trung Bình, Điểm rèn luyện, Điểm tổng kết, và xoá ảnh
    public void clearAllFields() {
        driver.findElement(avgScoreField).clear();
        driver.findElement(conductScoreField).clear();
        driver.findElement(finalScoreField).clear();
        driver.findElement(DeletePhotoButton).click();
    }
    
    // Xoá các trường điểm Trung Bình, Điểm rèn luyện, Điểm tổng kết
    public void clearScores() {
        driver.findElement(avgScoreField).clear();
        driver.findElement(conductScoreField).clear();
        driver.findElement(finalScoreField).clear();
    }
    
    // Nhấn Nút Huỷ đăng ký sau đó nhấn không huỷ
    public void CancelbutNo() {
    	driver.findElement(HuyDangKyButton).click();
    	driver.findElement(KhongHuyButton).click();
    }
    
    // Nhấn chọn nút Đóng trong cập nhật form ứng tuyển TG
    public void CloseForm() {
    	driver.findElement(DongButton).click();
    }
    
    //// Nhấn Nút Huỷ đăng ký sau đó nhấn Huỷ ngay!
    public void CancelbutYes() throws InterruptedException {
    	driver.findElement(HuyDangKyButton).click();
    	Thread.sleep(300);
    	driver.findElement(HuyNgayButton).click();
    }
    
    // Kiểm tra thông báo Huỷ ứng tuyển thành công có được hiển thị
    public void CancelSuccessMessageDisplayed() throws InterruptedException {
    	Thread.sleep(500);
    	if (driver.findElement(CancelsuccessMessage).isDisplayed()) {
    		System.out.println("Huỷ Ứng Tuyển Thành Công được hiển thị");
    	} else {
    		System.out.println("Huỷ Ứng Tuyển Thành Công không được hiển thị");
    	}
    }
    
    // Nhấn chọn Cập nhật mở lại Cập nhật form ứng tuyển TG
    public void ReUpdate() {
    	driver.findElement(UpdateButton).click();
    }
}
