package BCN.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BcnUserListPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ====== Locators ======

    // Menu "Người dùng" bên trái
    private By menuUser = By.id("child-user-index");

    // Ô tìm kiếm
    private By searchInput = By.cssSelector("input[aria-controls='dataTableBasic']");

    // Các dòng user trong bảng
    private By tableRows = By.cssSelector("#dataTableBasic tbody tr");

    // Dòng message "Không tìm thấy dữ liệu nào"
    private By emptyMessage = By.cssSelector("td.dataTables_empty");
 // thêm ở phần khai báo locator
    private By editFirstRowButton = By.cssSelector("table tbody tr:first-child td:last-child a"); 
 // thêm method
    public void openEditFormFirstRow() {
        wait.until(ExpectedConditions.elementToBeClickable(editFirstRowButton)).click();
    }



    public BcnUserListPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Mở màn hình "Người dùng" (từ Dashboard)
    public void open() {
        // click menu Người dùng
        wait.until(ExpectedConditions.elementToBeClickable(menuUser)).click();
        // đợi tới khi ô search xuất hiện
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
    }

    // Số lượng user đang hiển thị trong bảng
    public int getUserCount() {
        // nếu có message "Không tìm thấy dữ liệu nào" thì trả về 0
        List<WebElement> empty = driver.findElements(emptyMessage);
        if (!empty.isEmpty() && empty.get(0).isDisplayed()) {
            return 0;
        }

        // ngược lại đếm số dòng trong tbody
        return driver.findElements(tableRows).size();
    }

    // Gõ keyword vào ô search
    public void searchUser(String keyword) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.clear();
        input.sendKeys(keyword);

        // chờ data table filter xong:
        // hoặc có dòng dữ liệu, hoặc hiện message empty
        wait.until(driver -> {
            boolean hasRows = !driver.findElements(tableRows).isEmpty();
            boolean hasEmpty = !driver.findElements(emptyMessage).isEmpty();
            return hasRows || hasEmpty;
        });
    }

    // Lấy danh sách TÊN (hoặc nội dung cột nào đó) của tất cả user
    public List<String> getAllUserNames() {
        List<String> result = new ArrayList<>();

        // nếu bảng trống thì trả về list rỗng
        List<WebElement> empty = driver.findElements(emptyMessage);
        if (!empty.isEmpty() && empty.get(0).isDisplayed()) {
            return result;
        }

        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            // TODO: tuỳ cấu trúc bảng:
            // nếu cột "Họ & Tên" ở cột thứ 2 => td:nth-child(2)
            WebElement nameCell = row.findElement(By.cssSelector("td:nth-child(2)"));
            result.add(nameCell.getText().trim());
        }

        return result;
    }

    public boolean isEmptyMessageDisplayed() {
        List<WebElement> empty = driver.findElements(emptyMessage);
        return !empty.isEmpty() && empty.get(0).isDisplayed();
    }
}
