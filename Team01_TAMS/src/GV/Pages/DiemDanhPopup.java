package GV.Pages;

import org.openqa.selenium.*;
import Helpers.ValidateUIHelpers;

/**
 * DiemDanhPopup:
 *  - Popup điểm danh sinh viên trong LHP
 *  - Dùng ValidateUIHelpers để thao tác UI
 */
public class DiemDanhPopup {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    public DiemDanhPopup(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // ============== LOCATORS ==============
    private By modalRoot          = By.id("diemdanhsv");
    private By txtSearchStudent   = By.xpath("//div[@id='diemdanhsv']//input[@placeholder='Nhập từ khóa tìm kiếm...']");
    private By btnSave            = By.id("btnSubmitDiemDanh");

    // ============== PRIVATE HELPERS ==============

    private WebElement getRowByMSSV(String mssv) {
        String xpath =
                "//div[@id='diemdanhsv']//tr[" +
                ".//span[contains(@class,'badge') and normalize-space()='" + mssv + "']" +
                "]";
        return driver.findElement(By.xpath(xpath));
    }

    // ============== PUBLIC ACTIONS ==============

    /**
     * Chờ popup điểm danh hiển thị
     */
    public void waitLoaded() {
        helper.verifyElementExist(modalRoot);
        helper.waitForPageLoaded();
    }

    /**
     * Tìm SV theo MSSV/Họ tên
     */
    public void searchStudent(String keyword) {
        helper.setText(txtSearchStudent, keyword);
        helper.waitForPageLoaded();
    }

    /**
     * Chọn trạng thái điểm danh
     */
    public void selectStatus(String mssv, String statusText) {

        WebElement row = getRowByMSSV(mssv);

        WebElement ddl = row.findElement(By.xpath(".//select"));
        ddl.click();

        ddl.findElement(By.xpath(".//option[normalize-space()='" + statusText + "']")).click();

        helper.waitForPageLoaded();
    }

    /**
     * Nhập ghi chú
     */
    public void inputNote(String mssv, String note) {
        WebElement row = getRowByMSSV(mssv);

        WebElement txt = row.findElement(By.xpath(".//textarea"));
        txt.clear();
        txt.sendKeys(note);

        helper.waitForPageLoaded();
    }

    /**
     * Lưu điểm danh
     */
    public void clickSave() {
        driver.findElement(btnSave).click();
        helper.waitForPageLoaded();
    }
}
