package BCN.Pages;

import Helpers.ValidateUIHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class UpdateUserPage {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    // URL trang cập nhật người dùng
    private static final String PAGE_URL =
            "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/TeachingAssistant/Registered";

    // ===== LOCATORS =====
    private By fullNameInput    = By.id("edithoten");
    private By emailInput       = By.id("editemail");

    private By phoneInput       = By.id("editdienthoai");
    private By birthdayInput    = By.id("editngaysinh");

    private By anyDayInPicker   = By.xpath(
            "//div[contains(@class,'flatpickr-calendar') and contains(@class,'open')]"
          + "//span[contains(@class,'flatpickr-day') "
          + "       and not(contains(@class,'prevMonthDay')) "
          + "       and not(contains(@class,'nextMonthDay'))]"
    );

    private By genderSelect     = By.id("editgioitinh");
    private By majorSelect      = By.id("editnganh");

    private By saveButton       = By.xpath("//button[contains(.,'Lưu thông tin')]");
    private By toastMessage     = By.cssSelector(".toast-body");

    private By codeInput        = By.id("editma");


    // ===== CONSTRUCTOR =====
    public UpdateUserPage(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }


    // ===== ACTIONS =====

    public void waitForFormVisible() {
        helper.waitForPageLoaded();
        helper.verifyElementExist(fullNameInput);
        helper.verifyElementExist(emailInput);
    }

    public void setCode(String code) {
        helper.waitForPageLoaded();
        WebElement codeField = driver.findElement(codeInput);
        codeField.clear();
        codeField.sendKeys(code);
    }

    // readonly fields
    public boolean isEmailEnabled() {
        return driver.findElement(emailInput).isEnabled();
    }

    public boolean isFullNameEnabled() {
        return driver.findElement(fullNameInput).isEnabled();
    }

    // phone
    public void setPhone(String phone) {
        helper.waitForPageLoaded();
        WebElement el = driver.findElement(phoneInput);
        el.clear();
        el.sendKeys(phone);
        helper.waitForPageLoaded();
    }

    // birthday – chọn 1 ngày bất kỳ
    public void setBirthdayAnyDay() {
        helper.waitForPageLoaded();

        WebElement input = driver.findElement(birthdayInput);
        input.click();

        helper.waitForPageLoaded();

        WebElement day = driver.findElement(anyDayInPicker);
        day.click();

        helper.waitForPageLoaded();
    }

    // gender
    public void setGender(String genderText) {
        helper.waitForPageLoaded();
        WebElement el = driver.findElement(genderSelect);
        new Select(el).selectByVisibleText(genderText);
        helper.waitForPageLoaded();
    }

    // major
    public void setMajor(String majorText) {
        helper.waitForPageLoaded();
        WebElement el = driver.findElement(majorSelect);
        new Select(el).selectByVisibleText(majorText);
        helper.waitForPageLoaded();
    }

    // save
    public void clickSave() {
        helper.waitForPageLoaded();
        driver.findElement(saveButton).click();
        helper.waitForPageLoaded();
    }

    // toast
    public String getToastText() {
        helper.waitForPageLoaded();
        return driver.findElement(toastMessage).getText().trim();
    }
}
