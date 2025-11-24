package GV.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * DiemDanhPopup:
 *  - Đại diện cho popup/modal Điểm danh sinh viên trong LHP.
 *  - Cho phép:
 *      + Chờ modal load.
 *      + Tìm sinh viên bằng MSSV hoặc Họ tên.
 *      + Chọn trạng thái điểm danh.
 *      + Nhập ghi chú.
 *      + Lưu thông tin.
 */
public class DiemDanhPopup {

    private WebDriver driver;

    public DiemDanhPopup(WebDriver driver) {
        this.driver = driver;
    }

    // Root của modal Điểm danh 
    private By modalRoot = By.id("diemdanhsv");

    // Ô tìm kiếm 
    private By txtSearchStudent = By.xpath("//div[@id='diemdanhsv']//input[@placeholder='Nhập từ khóa tìm kiếm...']");

    // Nút Lưu thông tin
    private By btnSave = By.id("btnSubmitDiemDanh");

    /**
     * Đợi modal Điểm danh hiển thị đầy đủ.
     */
    public void waitLoaded() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(modalRoot));
    }

    /**
     * Tìm sinh viên trong danh sách bằng từ khóa.
     */
    public void searchStudent(String keyword) {
        WebElement searchBox = driver.findElement(txtSearchStudent);
        searchBox.clear();
        searchBox.sendKeys(keyword);
    }

    private WebElement getRowByMSSV(String mssv) {
        String xpath = "//div[@id='diemdanhsv']//tr[.//span[contains(@class,'badge') and normalize-space()='" + mssv + "']]";
        return driver.findElement(By.xpath(xpath));
    }

    /**
     * Chọn trạng thái điểm danh cho 1 sinh viên.
     */
    public void selectStatus(String mssv, String statusText) {
        WebElement row = getRowByMSSV(mssv);

        // dropdown điểm danh (cột "Điểm danh")
        WebElement ddlStatus = row.findElement(By.xpath(".//select"));

        ddlStatus.click();
        ddlStatus.findElement(By.xpath(".//option[normalize-space()='" + statusText + "']")).click();
    }

    /**
     * Nhập ghi chú cho sinh viên trong điểm danh.
     */
    public void inputNote(String mssv, String note) {
        WebElement row = getRowByMSSV(mssv);

        WebElement txtNote = row.findElement(By.xpath(".//textarea"));
        txtNote.clear();
        txtNote.sendKeys(note);
    }

    /**
     * Click nút “Lưu thông tin” để lưu toàn bộ điểm danh của buổi học.
     */
    public void clickSave() {
        driver.findElement(btnSave).click();
    }
}
