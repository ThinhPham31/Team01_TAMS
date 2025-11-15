package GV.Pages;

import Commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KetQuaDiemDanhPopup {

    private WebDriver driver;

    public KetQuaDiemDanhPopup(WebDriver driver) {
        this.driver = driver;
    }

    // Modal chính
    private By popupContainer = By.id("ketquadiemdanhmodal");

    // Nút Xuất (Export)
    private By btnExport = By.xpath("//div[@id='ketquadiemdanhmodal']//button[contains(.,'Xuất')]");

    // Ô tìm kiếm 
    private By searchBox =
            By.xpath("//div[@id='ketquadiemdanhmodal']//input[@placeholder='Nhập từ khóa tìm kiếm...']");

    public void waitLoaded() {
        WebUI.waitForElementVisible(driver, popupContainer, 10);
    }

    public void search(String keyword) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyword);
        WebUI.sleep(1.5);
    }

    public void exportFile() {
        WebUI.waitForElementClickable(driver, btnExport);
        driver.findElement(btnExport).click();
        WebUI.sleep(3); // chờ file tải xong
    }
}
