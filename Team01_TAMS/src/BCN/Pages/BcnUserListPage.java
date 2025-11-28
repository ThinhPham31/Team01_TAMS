package BCN.Pages;

import Helpers.ValidateUIHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BcnUserListPage {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    // ====== Locators ======

    private By menuUser = By.id("child-user-index");

    private By searchInput = By.cssSelector("input[aria-controls='dataTableBasic']");

    private By tableRows = By.cssSelector("#dataTableBasic tbody tr");

    private By emptyMessage = By.cssSelector("td.dataTables_empty");

    private By editFirstRowButton = By.cssSelector("table tbody tr:first-child td:last-child a");


    // ====== Constructor ======
    public BcnUserListPage(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }


    // ====== Mở màn hình Người dùng ======
    public void open() {
        helper.waitForPageLoaded();
        driver.findElement(menuUser).click();
        helper.waitForPageLoaded();
    }


    // ====== Mở form Edit dòng đầu ======
    public void openEditFormFirstRow() {
        helper.waitForPageLoaded();
        driver.findElement(editFirstRowButton).click();
        helper.waitForPageLoaded();
    }


    // ====== Lấy số lượng user ======
    public int getUserCount() {

        if (isEmptyMessageDisplayed()) return 0;

        List<WebElement> rows = driver.findElements(tableRows);
        return rows.size();
    }


    // ====== Tìm kiếm user ======
    public void searchUser(String keyword) {
        helper.waitForPageLoaded();

        WebElement input = driver.findElement(searchInput);
        input.clear();
        input.sendKeys(keyword);

        helper.waitForPageLoaded();
    }


    // ====== Lấy danh sách tên ======
    public List<String> getAllUserNames() {

        List<String> result = new ArrayList<>();

        if (isEmptyMessageDisplayed()) return result;

        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.cssSelector("td:nth-child(2)"));
            result.add(nameCell.getText().trim());
        }

        return result;
    }


    // ====== Kiểm tra bảng trống ======
    public boolean isEmptyMessageDisplayed() {
        List<WebElement> empty = driver.findElements(emptyMessage);
        return !empty.isEmpty() && empty.get(0).isDisplayed();
    }
}
