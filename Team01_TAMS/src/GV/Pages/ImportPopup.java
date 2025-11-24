package GV.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ImportPopup:
 *  - Đại diện cho popup Import danh sách sinh viên của LHP.
 *  - Gồm:
 *      + Input chọn file Excel.
 *      + Nút Import.
 */
public class ImportPopup {

    private WebDriver driver;

    public ImportPopup(WebDriver driver) {
        this.driver = driver;
    }

    // Root modal Import
    private By modalRoot = By.id("importsv");

    // Input file 
    private By inputFile = By.id("fileImportDssv");

    // Nút Import
    private By btnImport = By.id("btnSubmitImport");

    /**
     * Đợi modal Import hiển thị.
     */
    public void waitLoaded() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(modalRoot));
    }

    /**
     * Upload file Excel chứa danh sách sinh viên.
     */
    public void uploadFile(String filePath) {
        WebElement fileInput = driver.findElement(inputFile);
        fileInput.sendKeys(filePath); // Selenium sẽ tự chọn file này
    }

    /**
     * Click nút Import để gửi file lên hệ thống.
     */
    public void clickImport() {
        driver.findElement(btnImport).click();
    }
}
