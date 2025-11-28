package BCN.Pages;

import Helpers.ValidateUIHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TaRemunerationPage {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    private static final String PAGE_URL =
            "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/Statisticals/StatisticalRemuneration";

    // ========== LOCATORS ==========
    private By semesterSelect = By.id("hocky");
    private By majorSelect    = By.id("nganh");

    private By exportButton   = By.id("exportfile");
    private By tableRows      = By.cssSelector("table tbody tr");

    private By pageTitle      = By.xpath("//*[contains(.,'Bảng Thống Kê Trợ Giảng')]");


    // ========== CONSTRUCTOR ==========
    public TaRemunerationPage(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // ========== ACTIONS ==========

    // Mở trang thống kê thù lao
    public void openPage() {
        driver.get(PAGE_URL);
        helper.waitForPageLoaded();
    }

    // Chọn học kỳ
    public void selectSemester(String semesterText) {
        helper.waitForPageLoaded();
        WebElement el = driver.findElement(semesterSelect);
        new Select(el).selectByVisibleText(semesterText);
        helper.waitForPageLoaded();
    }

    // Chọn ngành
    public void selectMajor(String majorText) {
        helper.waitForPageLoaded();
        WebElement el = driver.findElement(majorSelect);
        new Select(el).selectByVisibleText(majorText);
        helper.waitForPageLoaded();
    }

    // Đếm số dòng TA
    public int getTaRowCount() {
        helper.waitForPageLoaded();
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }

    // Click nút Xuất
    public void clickExport() {
        helper.waitForPageLoaded();
        driver.findElement(exportButton).click();
        helper.waitForPageLoaded();
    }

    // Kiểm tra trang đã load OK
    public boolean isPageLoaded() {
        helper.waitForPageLoaded();

        boolean title = helper.verifyElementExist(pageTitle);
        boolean hasRows = driver.findElements(tableRows).size() > 0;

        return title && hasRows;
    }
}
