package GV.Pages;

import Commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ThongTinCaNhanPage {

    private WebDriver driver;

    public ThongTinCaNhanPage(WebDriver driver){
        this.driver = driver;
    }

    // Nút mở modal thông tin cá nhân trong dropdown user
    private By btnOpen = By.xpath("//a[contains(@data-bs-target,'#thongtincanhanmodal')]");

    // Modal
    private By modal   = By.id("thongtincanhanmodal");

    // Các field editable:
    private By phoneInput = By.id("infodienthoai");
    private By dateInput  = By.id("infongaysinh");
    private By genderSelect = By.id("infogioitinh");

    // Nút lưu
    private By btnSave = By.id("btnInfoSubmit");

      //Mở  Thông tin cá nhân.
    public void openModal(){
        WebUI.waitForElementClickable(driver, btnOpen);
        driver.findElement(btnOpen).click();
        WebUI.waitForElementVisible(driver, modal, 10);
    }

     //Cập nhật số điện thoại.
    public void updatePhone(String phone){
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phone);
    }

     //Cập nhật ngày sinh (định dạng yyyy-MM-dd hoặc theo flatpickr của hệ thống).
    public void updateDOB(String dob){
        driver.findElement(dateInput).clear();
        driver.findElement(dateInput).sendKeys(dob);
    }

    //Chọn giới tính trong dropdown.
    public void selectGender(String gender){
        WebUI.selectOptionByText(driver, genderSelect, gender);
    }

    //Nhấn Lưu thông tin.
    public void save(){
        driver.findElement(btnSave).click();
        WebUI.sleep(1.5);
    }
}
