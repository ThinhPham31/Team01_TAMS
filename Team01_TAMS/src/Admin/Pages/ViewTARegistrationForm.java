package Admin.Pages;

import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import Commons.WebUI;

public class ViewTARegistrationForm {

    private WebDriver driver;

    public ViewTARegistrationForm(WebDriver driver) {
        this.driver = driver;
    }

    // ========================== LOCATORS ==========================

    // Filter Khoa
    private By facultyDropdown = By.id("filter-khoa");

    // Filter Ngành
    private By majorDropdown = By.id("filter-nganh");

    // Search input DataTable
    private By searchBox = By.cssSelector("#tableFormDangKy_filter input[type='search']");

    // Button Mở Đăng Ký (open modal "themmoi")
    private By openRegisterBtn = By.xpath("//a[@data-bs-target='#themmoi']");

    // Table
    private By tableRows = By.cssSelector("#tableFormDangKy tbody tr");


    // ========================== FILTER ==========================

    public void selectFaculty(String value) {
        WebElement dropdown = driver.findElement(facultyDropdown);
        dropdown.click();
        dropdown.findElement(By.xpath("./option[text()='" + value + "']")).click();
    }

    public void selectMajor(String value) {
        WebElement dropdown = driver.findElement(majorDropdown);
        dropdown.click();
        dropdown.findElement(By.xpath("./option[text()='" + value + "']")).click();
    }


    // ========================== SEARCH ==========================

    public void enterSearch(String text) {
        WebElement box = driver.findElement(searchBox);
        box.clear();
        box.sendKeys(text);
    }

    // ========================== OPEN FORM ==========================

    public void clickOpenRegister() {
        WebUI.waitForElementClickable(driver, openRegisterBtn).click();
    }

    // ========================== ACTION BUTTONS ==========================

    /**
     * Nút Edit có id dạng openSua-118
     * Tìm theo Học kỳ ở cột 2 (index = 2)
     */
    public void clickEdit(String hocKy) {

        String xpath = "//table[@id='tableFormDangKy']//tr[td[2][text()='" + hocKy + "']]";
        WebElement row = driver.findElement(By.xpath(xpath));

        WebElement editBtn = row.findElement(By.cssSelector("a[id^='openSua']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editBtn);
    }

    /**
     * Nút Delete có id dạng openXoa-118
     */
    public void clickDelete(String hocKy) {

        String xpath = "//table[@id='tableFormDangKy']//tr[td[2][text()='" + hocKy + "']]";
        WebElement row = driver.findElement(By.xpath(xpath));

        WebElement deleteBtn = row.findElement(By.cssSelector("a[id^='openXoa']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
    }


    // ========================== WAIT ==========================

    public void waitTableReload() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
    }
}
