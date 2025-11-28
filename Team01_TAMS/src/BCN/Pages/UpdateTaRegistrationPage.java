package BCN.Pages;

import Helpers.ValidateUIHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UpdateTaRegistrationPage {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    private static final String PAGE_URL =
            "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/TeachingAssistant/Registered";

    // ========= LOCATORS =========

    private By semesterSelect     = By.id("hocky");
    private By majorSelect        = By.id("nganh");

    private By tableRows          = By.cssSelector("table tbody tr");

    private By firstDetailButton =
            By.cssSelector("table tbody tr:first-child td:last-child a");

    private By firstRowStatusCell =
            By.cssSelector("table tbody tr:first-child td:nth-child(5)");

    private By detailModal        = By.cssSelector("div.modal.show");
    private By detailInfoBlock    = By.cssSelector("div.modal.show .modal-body");

    private By approveButton =
            By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]//button[contains(.,'Duyệt')]");

    private By toastMessage       = By.cssSelector(".toast-body");


    // ========= CONSTRUCTOR =========
    public UpdateTaRegistrationPage(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }


    // ========= PUBLIC METHODS =========


    // Mở trang danh sách đăng ký trợ giảng
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
    public void selectMajorFilter(String majorText) {
        helper.waitForPageLoaded();
        WebElement el = driver.findElement(majorSelect);
        new Select(el).selectByVisibleText(majorText);
        helper.waitForPageLoaded();
    }


    // Lấy trạng thái dòng đầu tiên
    public String getFirstRowStatus() {

        helper.waitForPageLoaded();

        List<WebElement> rows = driver.findElements(tableRows);
        if (rows.isEmpty()) {
            throw new RuntimeException("Không có dòng nào trong bảng để lấy trạng thái");
        }

        WebElement cell = driver.findElement(firstRowStatusCell);
        return cell.getText().trim();
    }


    // Mở modal chi tiết dòng đầu tiên
    public void openFirstRegistrationDetail() {

        helper.waitForPageLoaded();

        List<WebElement> rows = driver.findElements(tableRows);
        if (rows.isEmpty()) {
            throw new RuntimeException("Không có dòng nào để xem chi tiết");
        }

        driver.findElement(firstDetailButton).click();
        helper.waitForPageLoaded();
    }


    // Kiểm tra modal có hiển thị thông tin hay chưa
    public boolean isDetailInfoDisplayed() {

        helper.waitForPageLoaded();

        List<WebElement> modal = driver.findElements(detailModal);
        if (modal.isEmpty()) return false;

        WebElement info = driver.findElement(detailInfoBlock);
        return info.isDisplayed() && info.getText().trim().length() > 0;
    }


    // Bấm nút Duyệt trong modal
    public void approveInDetailModal() {
        helper.waitForPageLoaded();
        driver.findElement(approveButton).click();
        helper.waitForPageLoaded();
    }


    // Lấy text của toast message
    public String getToastText() {
        helper.waitForPageLoaded();
        WebElement toast = driver.findElement(toastMessage);
        return toast.getText().trim();
    }
}
