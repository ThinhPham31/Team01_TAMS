package BCN.Pages;

import Helpers.ValidateUIHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TaEvaluationResultPage {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    // Trang kết quả đánh giá
    private static final String PAGE_URL =
            "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/ReviewTask";

    // ===== Locators =====
    private By semesterSelect = By.id("hocky");
    private By majorSelect    = By.id("nganh");

    private By tableRows      = By.cssSelector("table tbody tr");

    private By firstViewButton = By.xpath(
            "//table//tbody/tr[1]//a[contains(.,'Xem đánh giá')]"
    );

    private By detailModal = By.cssSelector("div.modal.show");
    private By detailBody  = By.cssSelector("div.modal.show .modal-body");


    // ===== Constructor =====
    public TaEvaluationResultPage(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }


    // ===== Open page =====
    public void openPage() {
        driver.get(PAGE_URL);
        helper.waitForPageLoaded();
    }


    // ===== Select semester =====
    public void selectSemester(String semesterText) {
        helper.waitForPageLoaded();
        WebElement el = driver.findElement(semesterSelect);
        new Select(el).selectByVisibleText(semesterText);
        helper.waitForPageLoaded();
    }


    // ===== Select major =====
    public void selectMajor(String majorText) {
        helper.waitForPageLoaded();
        WebElement el = driver.findElement(majorSelect);
        new Select(el).selectByVisibleText(majorText);
        helper.waitForPageLoaded();
    }


    // ===== Count rows =====
    public int getResultRowCount() {
        helper.waitForPageLoaded();
        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }


    // ===== Open details of first row =====
    public void openFirstEvaluationDetail() {
        helper.waitForPageLoaded();

        List<WebElement> rows = driver.findElements(tableRows);
        if (rows.isEmpty()) {
            throw new RuntimeException("Không có dữ liệu để xem chi tiết đánh giá");
        }

        driver.findElement(firstViewButton).click();
        helper.waitForPageLoaded();
    }


    // ===== Check evaluation detail modal =====
    public boolean isEvaluationInfoDisplayed() {

        helper.waitForPageLoaded();

        List<WebElement> modal = driver.findElements(detailModal);
        if (modal.isEmpty()) return false;

        WebElement body = driver.findElement(detailBody);
        String text = body.getText().trim();

        return text.length() > 0;
    }
}
