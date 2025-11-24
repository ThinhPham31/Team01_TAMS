package GV.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * NotificationArea:
 *  - Đại diện cho dropdown chuông "Thông báo" trên header.
 *  - Dùng cho:
 *      + Mở dropdown.
 *      + Tìm kiếm thông báo.
 *      + Xóa 1 thông báo cụ thể.
 */
public class NotificationArea {

    private WebDriver driver;

    public NotificationArea(WebDriver driver) {
        this.driver = driver;
    }

    // Nút chuông Thông báo 
    private By btnNotification = By.id("dropdownNotification");

    // Ô tìm kiếm trong dropdown
    private By txtSearch = By.id("inp-search-thongbao");

    // Nút search (kính lúp)
    private By btnSearch = By.id("btn-search-thongbao");

    /**
     * Mở Thông báo 
     */
    public void openNotificationDropdown() {
        driver.findElement(btnNotification).click();

        // Đợi ô tìm kiếm xuất hiện để chắc chắn dropdown đã mở
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(txtSearch));
    }

    /**
     * Tìm kiếm thông báo theo từ khóa.
     */
    public void search(String keyword) {
        openNotificationDropdown();

        WebElement txt = driver.findElement(txtSearch);
        txt.clear();
        txt.sendKeys(keyword);

        driver.findElement(btnSearch).click();
    }

    /**
     * Xóa 1 thông báo 
     */
    public void deleteNotification(String notiId) {
        openNotificationDropdown();

        // Click icon X tương ứng với id thông báo
        By deleteIcon = By.id("deleteNoti-" + notiId);
        driver.findElement(deleteIcon).click();
    }

    /**
     * (Tùy chọn) Kiểm tra thông báo đã bị xóa 
     */
    public boolean isNotificationDeleted(String notiId) {
        try {
            driver.findElement(By.id("cpn-thongbao-" + notiId));
            return false; // còn tồn tại
        } catch (NoSuchElementException e) {
            return true; // không tìm thấy → đã xóa
        }
    }
}
