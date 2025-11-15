package GV.Pages;

import Commons.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NotificationArea {

    private WebDriver driver;

    public NotificationArea(WebDriver driver){
        this.driver = driver;
    }

    // Icon chuông mở dropdown thông báo
    private By bellButton = By.id("dropdownNotification");

    // Ô tìm kiếm thông báo
    private By searchBox  = By.id("inp-search-thongbao");
    private By btnSearch  = By.id("btn-search-thongbao");

    public By deleteBtn(String id){
        return By.id("deleteNoti-" + id);
    }

    public By notiItem(String id){
        return By.id("cpn-thongbao-" + id);
    }

    // ============= ACTION =============

    public void openNotificationDropdown() {
        WebUI.waitForElementClickable(driver, bellButton);
        driver.findElement(bellButton).click();
        WebUI.sleep(1);
    }
   
     //Tìm kiếm thông báo theo từ khóa.  
    public void search(String keyword){
        WebUI.waitForElementVisible(driver, searchBox, 10);
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyword);
        driver.findElement(btnSearch).click();
        WebUI.sleep(1.5);
    }

    public void deleteNotification(String id){
        WebUI.waitForElementClickable(driver, deleteBtn(id));
        driver.findElement(deleteBtn(id)).click();
        WebUI.sleep(1.5);
    }
}
