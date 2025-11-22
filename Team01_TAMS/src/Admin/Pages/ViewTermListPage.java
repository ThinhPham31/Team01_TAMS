package Admin.Pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;   // BẮT BUỘC CHO SELENIUM 3
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Admin.Pages.AddTermPage;
import Commons.WebUI;

public class ViewTermListPage {

    private WebDriver driver;

    public ViewTermListPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchBox = By.xpath("//*[@id='dataTableBasic_filter']/label/input");
    private By firstColumnCells = By.cssSelector("#dataTableBasic tbody tr td:nth-child(1)");

    // Nhập từ khóa
    public void enterSearchTerm(String keyword) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyword);
    }

    // Kiểm tra dòng chứa text
    public boolean allRowsContain(String text) {
        for (var cell : driver.findElements(firstColumnCells)) {
            if (!cell.getText().contains(text)) return false;
        }
        return true;
    }

    // Mở form Thêm học kỳ
    public AddTermPage openAddTermForm() {
        By AddTermButtonLocator = By.xpath("//*[@id='page-content']/section/div[1]/div/div/div[2]/a");
        WebUI.waitForElementClickable(driver, AddTermButtonLocator).click();
        return new AddTermPage(driver);
    }

    // Mở form sửa học kỳ
    public void openEditTermForm(String hocKy) {

        WebDriverWait wait = new WebDriverWait(driver, 8);

        // 1. Chờ dòng chứa học kỳ
        WebElement row = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[1][text()='" + hocKy + "']/parent::tr")
            )
        );

        // 2. Lấy cột chứa nút edit
        WebElement editCell = row.findElement(By.xpath("./td[last()]"));

        // 3. Lấy nút <a> đúng theo cấu trúc
        WebElement editButton = editCell.findElement(By.cssSelector("a[id^='openSua']"));

        // 4. Click bằng JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", editButton);
        js.executeScript("arguments[0].click();", editButton);
        
        System.out.println("Edit button ID = " + editButton.getAttribute("id"));
    }
  
    // Lấy Ngày bắt đầu sau khi update
    public String getStartDate(String hocKy) {
        String xpath = "//td[text()='" + hocKy + "']/following-sibling::td[3]";
        return driver.findElement(By.xpath(xpath)).getText().trim();
    }

    // Bật/tắt trạng thái
    public void toggleTermStatus(String hocKy, boolean active) {
        String xpath = "//input[@fullname='" + hocKy + "']";
        WebElement checkbox = driver.findElement(By.xpath(xpath));

        boolean current = checkbox.isSelected();

        if (current != active) {
            checkbox.click();
        }
    }

    // Kiểm tra trạng thái checkbox
    public boolean isTermStatusActive(String hocKy) {
        String xpath = "//input[@fullname='" + hocKy + "']";
        return driver.findElement(By.xpath(xpath)).isSelected();
    }

    // Lấy toàn bộ bảng dưới dạng List<Map>
    public List<Map<String, String>> getWebTable() {
        List<Map<String, String>> tableData = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.cssSelector("#dataTableBasic tbody tr"));

        for (WebElement row : rows) {

            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() < 5) continue;

            Map<String, String> rowData = new LinkedHashMap<>();

            rowData.put("HocKy", cells.get(0).getText().trim());
            rowData.put("NamBatDau", cells.get(1).getText().trim());
            rowData.put("NamKetThuc", cells.get(2).getText().trim());
            rowData.put("NgayBatDau", cells.get(3).getText().trim());

            WebElement checkbox = cells.get(4).findElement(By.tagName("input"));
            String status = checkbox.isSelected() ? "Active" : "Inactive";
            rowData.put("TrangThai", status);

            tableData.add(rowData);
        }

        return tableData;
    }

    public void setEditStartDateDirect(String yyyymmdd) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1) chờ input có thể tương tác (đảm bảo modal đã mở và input có id đúng)
        WebElement input = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("editngaybatdau"))
        );

        // 2) scoll vào view để chắc chắn
        js.executeScript("arguments[0].scrollIntoView(true);", input);

        // 3) set date bằng flatpickr instance (sử dụng arguments để tránh string concat)
        js.executeScript(
            "arguments[0]._flatpickr.setDate(arguments[1], true);",
            input,
            yyyymmdd
        );

        // 4) trigger event để app front-end nhận thay đổi (nếu cần)
        js.executeScript(
            "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));" +
            "arguments[0].dispatchEvent(new Event('change', {bubbles: true}));",
            input
        );

        // 5) đợi input value thực sự thay đổi sang giá trị mong muốn
        wait.until(driver1 -> yyyymmdd.equals(input.getAttribute("value")));
    }
  
    public void safeEnterSearchTerm(String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement box = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchBox)
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", box);
        js.executeScript("arguments[0].click();", box);

        box.clear();
        box.sendKeys(keyword);
    }
    
    public void waitAfterSave() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Chờ modal đóng
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("editTermModal")));

        // Chờ bảng dataTable load lại
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("#dataTableBasic tbody tr")
        ));
    }
}
