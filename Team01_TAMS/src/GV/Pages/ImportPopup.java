package GV.Pages;

import Commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ImportPopup {

    private WebDriver driver;

    public ImportPopup(WebDriver driver) {
        this.driver = driver;
    }

    // Modal import
    private By popup    = By.id("importsv");

    // Input chọn file
    private By fileInput = By.id("fileImportDssv");

    // Nút Import
    private By btnImport = By.id("btnSubmitImport");

    public void waitPopup() {
        WebUI.waitForElementVisible(driver, popup, 15);
    }

    public void upload(String filePath) {
        driver.findElement(fileInput).sendKeys(filePath);
        WebUI.sleep(1);
    }

    //Nhấn nút Import để gửi file lên server.  
    public void submit() {
        driver.findElement(btnImport).click();
        WebUI.sleep(2);
    }
}
