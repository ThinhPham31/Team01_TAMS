package BCN.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateUserPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public UpdateUserPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ====== LOCATORS ======
    // Thay vì tìm modal, mình đợi luôn các field trong form cho chắc
    private By fullNameInput = By.id("edithoten");        // Họ & Tên (readonly)
    private By emailInput    = By.id("editemail");        // Email (readonly)

    private By phoneInput    = By.id("editdienthoai");    // Số điện thoại
    private By birthdayInput = By.id("editngaysinh");     // ô nhập ngày sinh (flatpickr)
 // 1 ngày bất kỳ trong calendar đang mở (bỏ qua ngày tháng trước/sau)
    private By anyDayInPicker = By.xpath(
            "//div[contains(@class,'flatpickr-calendar') and contains(@class,'open')]" +
            "//span[contains(@class,'flatpickr-day') " +
            "       and not(contains(@class,'prevMonthDay')) " +
            "       and not(contains(@class,'nextMonthDay'))]"
    );


    private By genderSelect  = By.id("editgioitinh");     // Giới tính
    private By majorSelect   = By.id("editnganh");        // Ngành

    private By saveButton    = By.xpath("//button[contains(.,'Lưu thông tin')]");
    private By toastMessage  = By.cssSelector(".toast-body");
    private By codeInput = By.id("editma"); 

    // ====== helper pause 5s (cho em dễ quan sát) ======
    private void pause5s() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ====== PUBLIC ACTIONS ======

    // Đợi form cập nhật visible
    public void waitForFormVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        pause5s();
    }
    public void setCode(String code) {
        WebElement codeField = wait.until(ExpectedConditions
                .elementToBeClickable(codeInput));
        codeField.clear();
        pause5s();           // cho bạn nhìn thấy rõ hơn
        codeField.sendKeys(code);
        pause5s();
    }


    // Kiểm tra 2 field không cho sửa
    public boolean isEmailEnabled() {
        return driver.findElement(emailInput).isEnabled();
    }

    public boolean isFullNameEnabled() {
        return driver.findElement(fullNameInput).isEnabled();
    }

    // SĐT
    public void setPhone(String phone) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(phoneInput));
        el.clear();
        el.sendKeys(phone);
        pause5s();
    }

    // Ngày sinh – chọn 1 ngày bất kì trong datepicker
    public void setBirthdayAnyDay() {
        // mở datepicker
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(birthdayInput));
        input.click();
        // cho UI có thời gian render calendar
        pause5s();

        // chọn 1 ngày bất kỳ trong tháng (ở đây là ngày đầu tiên nó tìm được,
        // nếu muốn đúng ngày 8 thì dùng XPath khác, mình ghi bên dưới)
        WebElement day = wait.until(ExpectedConditions.elementToBeClickable(anyDayInPicker));
        day.click();

        pause5s();
    }


    // Giới tính
    public void setGender(String genderText) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(genderSelect));
        new Select(el).selectByVisibleText(genderText);
        pause5s();
    }

    // Ngành
    public void setMajor(String majorText) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(majorSelect));
        new Select(el).selectByVisibleText(majorText);
        pause5s();
    }

    // Lưu
    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    // Đọc nội dung toast
    public String getToastText() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
        String text = toast.getText();
        pause5s();
        return text;
    }
}
