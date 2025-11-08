package BCN.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateTaRegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // URL trang “Danh Sách Sinh Viên Đăng Ký Trợ Giảng”
    // TODO: nếu URL khác thì chỉnh lại chuỗi này
    private static final String PAGE_URL =
            "https://cntttest.vanlanguni.edu.vn:18081/Ta2025/TeachingAssistant/Registered";

    public UpdateTaRegistrationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ========= LOCATORS =========

    // filter Học kỳ, Ngành
    private By semesterSelect = By.id("hocky");  // <select id="hocky">
    private By majorSelect    = By.id("nganh");  // <select id="nganh">

    // bảng danh sách SV đăng ký
    private By tableRows      = By.cssSelector("table tbody tr");

    // nút “chi tiết” dòng đầu (cột cuối cùng là icon / link)
    // nếu HTML khác, bạn sửa selector này
    private By firstDetailButton =
            By.cssSelector("table tbody tr:first-child td:last-child a");

    // ô Trạng thái của dòng đầu tiên
    // hiện tại bảng có: Sinh viên(1) – Liên hệ(2) – Lớp HP(3) – Điểm số(4) – Trạng thái(5) – Actions(6)
    // nếu thứ tự cột khác, chỉ việc đổi 5 thành index phù hợp.
    private By firstRowStatusCell =
            By.cssSelector("table tbody tr:first-child td:nth-child(5)");

    // modal chi tiết đăng ký
    private By detailModal      = By.cssSelector("div.modal.show");
    // 1 element bất kỳ trong body modal (ví dụ đoạn chứa MSSV / Họ tên)
    private By detailInfoBlock  = By.cssSelector("div.modal.show .modal-body");

    // nút Duyệt trong modal
    private By approveButton =
            By.xpath("//div[contains(@class,'modal') and contains(@class,'show')]//button[contains(.,'Duyệt')]");

    // Toast message sau khi duyệt
    private By toastMessage = By.cssSelector(".toast-body");

    // ========= tiện ích =========
    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pause5s() {
        pause(5000);
    }

    // ========= PUBLIC METHODS dùng trong test =========

    /**
     * Mở trang Danh Sách Sinh Viên Đăng Ký Trợ Giảng
     * (dùng sau khi đã login)
     */
    public void openPage() {
        driver.get(PAGE_URL);
        // chờ load xong combo Học kỳ
        wait.until(ExpectedConditions.visibilityOfElementLocated(semesterSelect));
        pause5s();
    }

    /**
     * Chọn học kỳ ở combo Học kỳ
     * @param semesterText ví dụ "251"
     */
    public void selectSemester(String semesterText) {
        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(semesterSelect));
        new Select(el).selectByVisibleText(semesterText);
        pause5s();
    }

    /**
     * Chọn ngành ở combo Ngành
     * @param majorText ví dụ "Công Nghệ Thông Tin"
     */
    public void selectMajorFilter(String majorText) {
        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(majorSelect));
        new Select(el).selectByVisibleText(majorText);
        pause5s();
    }

    /**
     * Lấy trạng thái của dòng đầu tiên trong bảng
     * (ví dụ: "Chưa được duyệt" / "Đã được duyệt")
     */
    public String getFirstRowStatus() {
        // chờ có ít nhất 1 dòng dữ liệu
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
        WebElement statusCell = driver.findElement(firstRowStatusCell);
        return statusCell.getText().trim();
    }

    /**
     * Mở modal chi tiết đăng ký của dòng đầu tiên
     */
    public void openFirstRegistrationDetail() {
        // bảo đảm có dữ liệu
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(firstDetailButton));
        btn.click();

        // chờ modal show lên
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailModal));
        pause5s();
    }

    /**
     * Kiểm tra trong modal có hiển thị thông tin chi tiết
     */
    public boolean isDetailInfoDisplayed() {
        try {
            WebElement info = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(detailInfoBlock));
            return info.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Trong modal chi tiết, bấm nút Duyệt
     */
    public void approveInDetailModal() {
        WebElement btnApprove = wait.until(
                ExpectedConditions.elementToBeClickable(approveButton));
        btnApprove.click();
    }

    /**
     * Lấy text của toast thông báo sau khi duyệt
     */
    public String getToastText() {
        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toastMessage));
        String text = toast.getText().trim();
        pause5s(); // cho bạn kịp nhìn toast
        return text;
    }
}

