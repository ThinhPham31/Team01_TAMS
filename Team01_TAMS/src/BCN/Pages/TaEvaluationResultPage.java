package BCN.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TaEvaluationResultPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // TODO: Đổi lại đúng URL trang "Kết Quả Đánh Giá Công Việc Trợ Giảng"
    // Bạn mở trang đó trên browser, copy nguyên url rồi dán vô đây.
    private static final String PAGE_URL =
            "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/ReviewTask";

    public TaEvaluationResultPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ========== LOCATORS ==========

    // Combobox Học kỳ & Ngành (dựa trên id bạn gửi)
    private By semesterSelect = By.id("hocky");   // <select id="hocky">
    private By majorSelect    = By.id("nganh");   // <select id="nganh">

    // Các dòng trong bảng kết quả
    private By tableRows      = By.cssSelector("table tbody tr");

    // Nút "Xem đánh giá" của dòng đầu tiên
    private By firstViewButton = By.xpath(
            "//table//tbody/tr[1]//a[contains(.,'Xem đánh giá')]"
    );

    // Modal chi tiết đánh giá (Bootstrap modal)
    private By detailModal = By.cssSelector("div.modal.show");
    private By detailBody  = By.cssSelector("div.modal.show .modal-body");

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

    // Mở trang Kết quả đánh giá
    public void openPage() {
        driver.get(PAGE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(semesterSelect));
        pause5s(); // cho bạn nhìn UI
    }

    // Chọn học kỳ (vd "251")
    public void selectSemester(String semesterText) {
        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(semesterSelect));
        new Select(el).selectByVisibleText(semesterText);
        pause5s();
    }

    // Chọn ngành (vd "Công Nghệ Thông Tin_TH0101")
    public void selectMajor(String majorText) {
        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(majorSelect));
        new Select(el).selectByVisibleText(majorText);
        pause5s();
    }

    // Lấy số dòng kết quả đang hiển thị
    public int getResultRowCount() {
        return driver.findElements(tableRows).size();
    }

    // Mở chi tiết đánh giá dòng đầu tiên
    public void openFirstEvaluationDetail() {
        // đảm bảo có ít nhất 1 dòng
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(firstViewButton));
        btn.click();

        // chờ modal hiện
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailModal));
        pause5s();
    }

    // Kiểm tra trong modal có hiển thị thông tin đánh giá
    public boolean isEvaluationInfoDisplayed() {
        try {
            WebElement body = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(detailBody));
            return body.isDisplayed() && body.getText().trim().length() > 0;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
