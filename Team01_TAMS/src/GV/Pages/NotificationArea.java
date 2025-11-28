package GV.Pages;

import org.openqa.selenium.*;
import Helpers.ValidateUIHelpers;

public class NotificationArea {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    public NotificationArea(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // Nút chuông Thông báo 
    private By btnNotification = By.id("dropdownNotification");
    
    // Khung danh sách thông báo
    private By notificationList = By.id("content-cpn-thongbao");

    // Ô tìm kiếm trong dropdown
    private By txtSearch = By.id("inp-search-thongbao");

    // Nút search (kính lúp)
    private By btnSearch = By.id("btn-search-thongbao");
    
    private By xDelete = By.xpath("//*[@id=\"deleteNoti-873\"]/i");
    
    private By btnConfirmDelete = By.xpath("/html/body/div[5]/div/div[6]/button[1]");
   

    /**
     * Mở Thông Báo nhưng KHÔNG làm nó đóng lại
     */
    public void openNotificationDropdown() {
        helper.waitForPageLoaded();

        WebElement bell = driver.findElement(btnNotification);

        // Kiểm tra dropdown đã mở chưa
        boolean isOpen = driver.findElement(notificationList).isDisplayed();

        if (!isOpen) {
            bell.click();
            helper.waitForPageLoaded();
        }
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
        helper.waitForPageLoaded();
    }
    
    
    /**
     * Lấy id x của thông báo đầu tiên.
     */
    public String getFirstNotificationId() {
        helper.waitForPageLoaded();

        By firstDelete = By.cssSelector("a[id^='deleteNoti-']");
        WebElement e = driver.findElement(firstDelete);

        String id = e.getAttribute("id"); // deleteNoti-873
        return id.replace("deleteNoti-", "");
    }

    
    // Click icon X
    public void clickDeleteById(String notiId) {
        helper.waitForPageLoaded();
        By deleteBtn = By.id("deleteNoti-" + notiId);
        driver.findElement(deleteBtn).click();
        helper.waitForPageLoaded();
    }
    
    // Confirm delete
    public void confirmDelete() {
        helper.waitForPageLoaded();
        driver.findElement(btnConfirmDelete).click();
        helper.waitForPageLoaded();
    }
}
