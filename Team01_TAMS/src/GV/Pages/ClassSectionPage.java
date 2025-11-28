package GV.Pages;

import Helpers.ValidateUIHelpers;
import org.openqa.selenium.*;

public class ClassSectionPage {

    private WebDriver driver;
    private ValidateUIHelpers helper;

    // Constructor
    public ClassSectionPage(WebDriver driver, ValidateUIHelpers helper) {
        this.driver = driver;
        this.helper = helper;
    }

    // ================= LOCATORS =================

    // Bảng danh sách lớp học phần 
    private By tableClassSection = By.id("dataTableBasic");

    // Ô tìm kiếm
    private By txtSearchLHP = By.xpath("//*[@id=\"dataTableBasic_filter\"]/label/input");
    private By chonHocKy = By.id("hocky");
    private By chonNganh = By.id("nganh");


    // ================= HÀM HỖ TRỢ =================

    /** 
     * Đợi bảng LHP hiển thị 
     */
    public void waitForTableLoaded() {
        helper.waitForPageLoaded();
        helper.verifyElementExist(tableClassSection);
    }
    
    

    /** 
     * Tìm kiếm lớp học phần theo mã 
     */
    public void searchLHP(String keyword) {
        helper.waitForPageLoaded();
        WebElement searchBox = driver.findElement(txtSearchLHP);
        searchBox.clear();
        searchBox.sendKeys(keyword);
        helper.waitForPageLoaded();
    }

    /**
     * Lấy dòng theo Mã LHP
     */
    private WebElement getRowByMaLHP(String maLHP) {
        helper.waitForPageLoaded();

        String xpath = "//table[@id='dataTableBasic']//tbody//tr[td[1][normalize-space()='" + maLHP + "']]";

        return driver.findElement(By.xpath(xpath));
    }


    // ================= ACTIONS =================
    
    /**
     * Chọn học kỳ theo text hiển thị trong dropdown <select id="hocky">
     */
    public void selectHocKy(String hocKyText) {
        helper.waitForPageLoaded();

        WebElement ddl = driver.findElement(chonHocKy);
        ddl.click();

        // kiểm tra option tồn tại
        boolean exists = ddl.findElements(
                By.xpath(".//option[normalize-space()='" + hocKyText + "']")
        ).size() > 0;

        if (!exists) {
            throw new RuntimeException("Không tìm thấy học kỳ: " + hocKyText);
        }

        ddl.findElement(
                By.xpath(".//option[normalize-space()='" + hocKyText + "']")
        ).click();

        helper.waitForPageLoaded();
    }

    /**
     * Chọn ngành theo text hiển thị trong dropdown <select id="nganh">
     */
    public void selectNganh(String nganhText) {
        helper.waitForPageLoaded();

        WebElement ddl = driver.findElement(chonNganh);
        ddl.click();

        boolean exists = ddl.findElements(
                By.xpath(".//option[normalize-space()='" + nganhText + "']")
        ).size() > 0;

        if (!exists) {
            throw new RuntimeException("Không tìm thấy ngành: " + nganhText);
        }

        ddl.findElement(
                By.xpath(".//option[normalize-space()='" + nganhText + "']")
        ).click();

        helper.waitForPageLoaded();
    }


    /**
     * Mở popup Điểm danh lớp học
     */
    public void openDiemDanhPopup(String maLHP) {
        WebElement row = getRowByMaLHP(maLHP);

        WebElement diemDanhIcon = row.findElement(
                By.xpath(".//a[@title='Điểm danh lớp học' or @data-bs-original-title='Điểm danh lớp học']"));

        diemDanhIcon.click();
        helper.waitForPageLoaded();
    }

    /**
     * Mở popup Kết quả điểm danh lớp học
     */
    public void openKetQuaDiemDanhPopup(String maLHP) {
        WebElement row = getRowByMaLHP(maLHP);

        WebElement ketQuaIcon = row.findElement(
                By.xpath(".//a[@title='Kết quả điểm danh lớp học' or @data-bs-original-title='Kết quả điểm danh lớp học']"));

        ketQuaIcon.click();
        helper.waitForPageLoaded();
    }

    /**
     * Mở popup Import danh sách sinh viên
     */
    public void openImportPopup(String maLHP) {
        WebElement row = getRowByMaLHP(maLHP);

        WebElement importIcon = row.findElement(
                By.xpath(".//a[@title='Upload danh sách sinh viên' or @data-bs-original-title='Upload danh sách sinh viên']"));

        importIcon.click();
        helper.waitForPageLoaded();
    }

    /**
     * Lấy số lượng dòng trong bảng
     */
    public int getRowCount() {
        helper.waitForPageLoaded();
        return driver.findElements(By.cssSelector("#dataTableBasic tbody tr")).size();
    }
}
