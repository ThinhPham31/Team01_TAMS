package GV.Pages;

import Commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DiemDanhPopup {

    private WebDriver driver;

    public DiemDanhPopup(WebDriver driver) {
        this.driver = driver;
    }

    // Modal chính
    private By popupContainer = By.id("diemdanhsv");

    // Nút Lưu thông tin
    private By btnSave = By.id("btnSubmitDiemDanh");

    // Ô search trong popup điểm danh
    private By txtSearch = By.xpath("//div[@id='diemdanhsv']//input[@placeholder='Nhập từ khóa tìm kiếm...']");


    public By dropdownTrangThai(String mssv) {
        return By.xpath("//div[@id='diemdanhsv']//span[contains(text(),'" + mssv + "')]"
                + "/ancestor::tr//select");
    }

    public By inputGhiChu(String mssv) {
        return By.xpath("//div[@id='diemdanhsv']//span[contains(text(),'" + mssv + "')]"
                + "/ancestor::tr//textarea");
    }

    // ============= ACTION =============

    public void waitPopupLoaded() {
        WebUI.waitForElementVisible(driver, popupContainer, 15);
    }

    public void search(String keyword) {
        WebUI.waitForElementVisible(driver, txtSearch, 10);
        driver.findElement(txtSearch).clear();
        driver.findElement(txtSearch).sendKeys(keyword);
        WebUI.sleep(1.5);
    }

    public void selectStatus(String mssv, String status) {
        WebUI.selectOptionByText(driver, dropdownTrangThai(mssv), status);
    }

    public void enterNote(String mssv, String note) {
        driver.findElement(inputGhiChu(mssv)).clear();
        driver.findElement(inputGhiChu(mssv)).sendKeys(note);
    }

    public void submit() {
        WebUI.waitForElementClickable(driver, btnSave, 10);
        driver.findElement(btnSave).click();
        WebUI.sleep(2);
    }
}
