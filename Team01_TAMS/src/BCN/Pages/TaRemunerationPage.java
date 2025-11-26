package BCN.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TaRemunerationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // TODO: kiểm tra lại URL trang "Bảng Thống Kê Trợ Giảng"
    private static final String PAGE_URL =
            "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/Statisticals/StatisticalRemuneration";

    public TaRemunerationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ========== LOCATORS ==========

    // Combobox Học kỳ & Ngành (thường giống id hocky / nganh ở các trang khác)
    private By semesterSelect = By.id("hocky");   // <select id="hocky">
    private By majorSelect    = By.id("nganh");   // <select id="nganh">

    // Nút "Xuất"
    private By exportButton = By.id("exportfile");
    // Các dòng trong bảng danh sách trợ giảng
    private By tableRows      = By.cssSelector("table tbody tr");

    // Header/breadcrumb để chắc chắn vào đúng trang
    private By pageTitle      = By.xpath("//*[contains(.,'Bảng Thống Kê Trợ Giảng')]");

    // ========== HELPER ==========

    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pause5s() {
        pause(5000);
    }

    // ========== ACTIONS ==========

    // Mở trang thống kê thù lao
    public void openPage() {
        driver.get(PAGE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        pause5s();   // cho bạn nhìn màn hình
    }

    // Chọn học kỳ (vd: "251")
    public void selectSemester(String semesterText) {
        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(semesterSelect));
        new Select(el).selectByVisibleText(semesterText);
        pause5s();
    }

    // Chọn ngành (vd: "Công Nghệ Th")
    public void selectMajor(String majorText) {
        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(majorSelect));
        new Select(el).selectByVisibleText(majorText);
        pause5s();
    }

    // Đếm số dòng TA đang hiển thị trong bảng
    public int getTaRowCount() {
        return driver.findElements(tableRows).size();
    }

    // Click nút Xuất – giả sử sinh ra file excel / csv
    public void clickExport() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(exportButton));
        btn.click();
        pause5s();  // cho bạn nhìn thấy popup download
    }

    // Kiểm tra trang đã load OK (có tiêu đề + bảng)
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
