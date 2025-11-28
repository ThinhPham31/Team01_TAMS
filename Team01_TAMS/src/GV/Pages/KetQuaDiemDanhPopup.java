package GV.Pages;

import org.openqa.selenium.*;
import Helpers.ValidateUIHelpers;

import java.util.List;

/**
 * KetQuaDiemDanhPopup:
 *  - Đại diện popup "KẾT QUẢ ĐIỂM DANH LHP: ...".
 *  - Hỗ trợ:
 *      + Đếm số dòng sinh viên.
 *      + Đếm số buổi học.
 *      + Xuất Excel.
 *  - Đã chuyển toàn bộ sang ValidateUIHelpers.
 */
public class KetQuaDiemDanhPopup {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    public KetQuaDiemDanhPopup(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // ===== LOCATORS =====
    private By modalRoot = By.id("ketquadiemdanhmodal");
    private By tableStudentRows =
            By.xpath("//div[@id='ketquadiemdanhmodal']//table//tbody//tr");

    private By tableHeaderCells =
            By.xpath("//div[@id='ketquadiemdanhmodal']//table//thead//tr/th");

    private By btnExport =
            By.xpath("//*[@id=\"btnExportDssv\"]");
    
    private By btnDiemDanhPopup = By.xpath("//*[@id=\"viewKetQuaDiemDanh-6855\"]/a/span/i");

    // ===== ACTIONS =====

    public void clickBtnDiemDanhPopup() {
        helper.waitForPageLoaded();

        WebElement btn = driver.findElement(btnDiemDanhPopup);
        btn.click();

        // sau khi click, popup phải hiển thị
        waitLoaded();
    }
    
    /** 
     * Chờ popup hiển thị
     */
    public void waitLoaded() {
        helper.verifyElementExist(modalRoot);
        helper.waitForPageLoaded();
    }

    /**
     * Đếm số dòng sinh viên
     */
    public int getStudentRowCount() {
        helper.waitForPageLoaded();
        List<WebElement> rows = driver.findElements(tableStudentRows);
        return rows.size();
    }

    /**
     * Đếm số cột buổi học (trừ TT + Thông tin SV)
     */
    public int getSessionColumnCount() {
        helper.waitForPageLoaded();

        List<WebElement> headers = driver.findElements(tableHeaderCells);
        int total = headers.size();

        if (total <= 2) return 0;
        return total - 2;
    }

    /**
     * Click nút Xuất Excel
     */
    public void clickExport() {
        helper.waitForPageLoaded();

        WebElement btn = driver.findElement(btnExport);
        btn.click();

        helper.waitForPageLoaded();
    }
}
