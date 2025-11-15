package GV.Pages;

import Commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClassSectionPage {

    private WebDriver driver;

    public ClassSectionPage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== Locators chung trên trang Lớp học phần =====

    // Ô tìm kiếm bên phải bảng
    public By inputSearch = By.xpath("//input[@placeholder='Nhập từ khóa tìm kiếm...']");

    // Bảng danh sách LHP
    public By tableRows   = By.xpath("//table[@id='dataTableBasic']//tbody/tr");

    // Dropdown học kỳ & ngành
    public By selectHocKy = By.id("hocky");
    public By selectNganh = By.id("nganh");

    // ================== LOCATOR THEO MÃ LHP ==================

    public By rowClass(String maLHP) {
        return By.xpath("//td[contains(normalize-space(),'" + maLHP + "')]/parent::tr");
    }

    public By btnImport(String maLHP) {
        return By.xpath("//td[contains(normalize-space(),'" + maLHP + "')]/parent::tr//a[contains(@id,'btnimportsv')]");
    }

    public By btnDiemDanh(String maLHP) {
        return By.xpath("//td[contains(normalize-space(),'" + maLHP + "')]/parent::tr//i[contains(@class,'bi-calendar')]");
    }

    public By btnKetQuaDiemDanh(String maLHP) {
        return By.xpath("//td[contains(normalize-space(),'" + maLHP + "')]/parent::tr//i[contains(@class,'bi-table') or contains(@class,'bi-grid')]");
    }

    // ================== ACTION ==================

    /**
     * Tìm kiếm LHP theo từ khóa (mã, tên, ...).
     */
    public void searchLHP(String keyword) {
        WebUI.waitForElementVisible(driver, inputSearch, 10);
        driver.findElement(inputSearch).clear();
        driver.findElement(inputSearch).sendKeys(keyword);
        WebUI.sleep(1.5); // đợi table filter xong
    }

    public int getRowCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    public void openImport(String maLHP) {
        WebUI.waitForElementClickable(driver, btnImport(maLHP));
        driver.findElement(btnImport(maLHP)).click();
    }

    public void openDiemDanh(String maLHP) {
        WebUI.waitForElementClickable(driver, btnDiemDanh(maLHP));
        driver.findElement(btnDiemDanh(maLHP)).click();
    }


    public void openKetQuaDiemDanh(String maLHP) {
        WebUI.waitForElementClickable(driver, btnKetQuaDiemDanh(maLHP));
        driver.findElement(btnKetQuaDiemDanh(maLHP)).click();
    }
}
