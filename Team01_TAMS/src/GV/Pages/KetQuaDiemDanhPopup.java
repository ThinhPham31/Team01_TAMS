package GV.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * KetQuaDiemDanhPopup:
 *  - Đại diện cho popup "KẾT QUẢ ĐIỂM DANH LHP: ...".
 *  - Cho phép:
 *      + Đếm số dòng sinh viên.
 *      + Đếm số cột buổi học (các ngày).
 *      + Bấm nút Xuất Excel.
 */
public class KetQuaDiemDanhPopup {

    private WebDriver driver;

    public KetQuaDiemDanhPopup(WebDriver driver) {
        this.driver = driver;
    }

    // Root modal
    private By modalRoot = By.id("ketquadiemdanhmodal");

    // Bảng kết quả 
    private By tableStudentRows = By.xpath("//div[@id='ketquadiemdanhmodal']//table//tbody//tr");

    // Header các cột ngày 
    private By tableHeaderCells = By.xpath("//div[@id='ketquadiemdanhmodal']//table//thead//tr/th");

    // Nút "Xuất" ở góc trên bên phải
    private By btnExport = By.xpath("//div[@id='ketquadiemdanhmodal']//button[normalize-space()='Xuất' or .//span[normalize-space()='Xuất']]");

    /**
     * Đợi Kết quả điểm danh hiển thị.
     */
    public void waitLoaded() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(modalRoot));
    }

    /**
     * Lấy tổng số dòng sinh viên trong bảng kết quả.
     */
    public int getStudentRowCount() {
        return driver.findElements(tableStudentRows).size();
    }

    /**
     * Lấy số cột buổi học (không tính cột TT, Thông tin SV).
     */
    public int getSessionColumnCount() {
        List<WebElement> headers = driver.findElements(tableHeaderCells);
        int total = headers.size();
        if (total <= 2) return 0;
        return total - 2;
    }

    /**
     * Click nút "Xuất" để tải file Excel kết quả điểm danh.
     */
    public void clickExport() {
        driver.findElement(btnExport).click();
    }
}
