package GV.Pages;

import org.openqa.selenium.*;
import Helpers.ValidateUIHelpers;

public class ImportPopup {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    public ImportPopup(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // ===== LOCATORS =====
    private By modalRoot = By.id("importsv");
    private By inputFile = By.id("fileImportDssv");
    private By btnImport = By.id("btnSubmitImport");
    private By importSV = By.xpath("//*[@id=\"btnimportsv-6855\"]/span/i");
    private By buttonCapNhat = By.xpath("/html/body/div[6]/div/div[6]/button[1]");

    // ===== ACTIONS =====
    
    /**
     * Click nút ImportSV để mở popup Import sinh viên
     */
    public void clickButtonCapNhat() {
        helper.waitForPageLoaded();

        WebElement btn = driver.findElement(buttonCapNhat);
        btn.click();
        // sau khi click, popup phải hiển thị
        waitLoaded();
    }
    
    /**
     * Click nút ImportSV để mở popup Import sinh viên
     */
    public void clickImportSV() {
        helper.waitForPageLoaded();

        WebElement btn = driver.findElement(importSV);
        btn.click();

        // sau khi click, popup phải hiển thị
        waitLoaded();
    }

    
    /**
     * Đợi popup Import hiển thị
     */
    public void waitLoaded() {
        helper.verifyElementExist(modalRoot);
        helper.waitForPageLoaded();
    }

    /**
     * Upload file Excel
     */
    public void uploadFile(String filePath) {
        helper.waitForPageLoaded();

        WebElement fileInput = driver.findElement(inputFile);
        fileInput.sendKeys(filePath);

        helper.waitForPageLoaded();
    }

    /**
     * Click nút Import
     */
    public void clickImport() {
        helper.waitForPageLoaded();
        driver.findElement(btnImport).click();
        helper.waitForPageLoaded();
    }
}
