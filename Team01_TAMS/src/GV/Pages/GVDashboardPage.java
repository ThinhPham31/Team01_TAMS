package GV.Pages;

import Commons.WebUI;
import Helpers.ValidateUIHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * GVDashboardPage:
 *  - Đại diện cho giao diện Dashboard của Giảng viên sau khi đăng nhập thành công
 *  - Chứa các thao tác mở menu bên trái (Lớp học phần, Thời khóa biểu, Trợ giảng, ...)
 */
public class GVDashboardPage {
	
	private ValidateUIHelpers helpers;

    private WebDriver driver;

    public GVDashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // =============== LOCATORS MENU BÊN TRÁI ===============
    
    // Meunu "Trang Chủ"
    private By menuHome = By.xpath("/html/body/div[2]/nav/div/div[1]/div[2]/div/div/div/ul/li[1]/a");

    // Menu "Lớp học phần"
    private By menuClassSection   = By.id("child-classsection-index");

    // Menu "Thời khóa biểu"
    private By menuTimeTable      = By.id("child-studentaffairs-studentaffairstimetable-index");

    // Menu  "Trợ giảng"
    private By menuTAParent       = By.id("parent-trogiang");

    // Các menu con của "Trợ giảng" 
    private By menuTAThongTin     = By.id("child-trogiang-dadangky");
    private By menuTAPhanCong     = By.id("child-trogiang-phancongtrogiang");
    private By menuTATaskList     = By.id("child-trogiang-tasklist");
    private By menuTAReview       = By.id("child-trogiang-reviewtask");

    // =============== ACTIONS ===============

    /**
     * Click menu "Lớp học phần" bên trái.
     *  → Trả về ClassSectionPage để testcase thao tác tiếp.
     */
    public ClassSectionPage clickClassSectionMenu(ValidateUIHelpers helper) {
        driver.findElement(menuClassSection).click();
        helper.waitForPageLoaded();
        return new ClassSectionPage(driver, helper);
    }

    /**
     * Mở menu Trợ giảng → Quản lý công việc
     *  (nếu bạn cần viết test cho phần Task List sau này)
     */
    public void openTaskListMenu() {
        // Click menu cha "Trợ giảng"
        WebUI.waitForElementClickable(driver, menuTAParent).click();

        // Click menu con "Quản lý công việc"
        WebUI.waitForElementClickable(driver, menuTATaskList).click();
    }
}
