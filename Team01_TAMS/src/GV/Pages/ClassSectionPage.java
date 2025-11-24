package GV.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ClassSectionPage:
 *  - Đại diện cho màn hình "Quản lý Lớp Học Phần" của giảng viên.
 *  - Các chức năng được dùng trong test:
 *      + Tìm kiếm LHP theo Mã LHP.
 *      + Mở Điểm danh lớp học.
 *      + Mở Import danh sách sinh viên.
 *      + Mở Kết quả điểm danh lớp học.
 */
public class ClassSectionPage {

    private WebDriver driver;

    // Constructor nhận driver từ testcase truyền vào
    public ClassSectionPage(WebDriver driver) {
        this.driver = driver;
    }

    // ================= LOCATORS CƠ BẢN =================

    // Bảng danh sách lớp học phần 
    private By tableClassSection = By.id("dataTableBasic");

    // Ô tìm kiếm bên phải bảng
    private By txtSearchLHP = By.xpath("//input[@placeholder='Nhập từ khóa tìm kiếm...']");

    // ================= HÀM DÙNG CHUNG =================

    /**
     * Đợi bảng Lớp học phần hiển thị.
     * Gọi ở testcase nếu cần trước khi thao tác với bảng.
     */
    public void waitForTableLoaded() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(tableClassSection));
    }

    /**
     * Nhập Mã LHP vào ô tìm kiếm
     *
	   Mã lớp học phần hoặc từ khóa bất kỳ.
     */
    public void searchLHP(String keyword) {
        WebElement searchBox = driver.findElement(txtSearchLHP);
        searchBox.clear();
        searchBox.sendKeys(keyword);
    }

    private WebElement getRowByMaLHP(String maLHP) {
        String xpath = "//table[@id='dataTableBasic']//tbody//tr[td[1][normalize-space()='" + maLHP + "']]";
        return driver.findElement(By.xpath(xpath));
    }

    /**
     * Mở "Điểm danh lớp học" cho 1 LHP cụ thể.
     */
    public void openDiemDanhPopup(String maLHP) {
        WebElement row = getRowByMaLHP(maLHP);

        // Tìm icon Điểm danh theo thuộc tính title
        WebElement diemDanhIcon = row.findElement(
                By.xpath(".//a[@title='Điểm danh lớp học' or @data-bs-original-title='Điểm danh lớp học']"));
        diemDanhIcon.click();
    }

    /**
     * Mở "Kết quả điểm danh lớp học" cho 1 LHP cụ thể.
     */
    public void openKetQuaDiemDanhPopup(String maLHP) {
        WebElement row = getRowByMaLHP(maLHP);

        WebElement ketQuaIcon = row.findElement(
                By.xpath(".//a[@title='Kết quả điểm danh lớp học' or @data-bs-original-title='Kết quả điểm danh lớp học']"));
        ketQuaIcon.click();
    }

    /**
     * Mở Import danh sách sinh viên cho 1 LHP.
     */
    public void openImportPopup(String maLHP) {
        WebElement row = getRowByMaLHP(maLHP);

        WebElement importIcon = row.findElement(
                By.xpath(".//a[@title='Upload danh sách sinh viên' or @data-bs-original-title='Upload danh sách sinh viên']"));
        importIcon.click();
    }
}
