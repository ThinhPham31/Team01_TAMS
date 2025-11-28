package BCN.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Commons.WebUI;

public class BcnDashboardPage {

    private WebDriver driver;

    public BcnDashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Trang chủ
    private By dashboardMenu = By.id("child-studentaffairs-studentaffairsdashboard-index");

    // Thống kê
    private By thongKeMenu = By.id("parent-thongke");
    private By thongKeTroGiangMenu = By.id("child-thongke-trogiang");
    private By thongKeThuLaoMenu = By.id("child-thongke-thulao");

    // Trợ giảng
    private By troGiangMenu = By.id("parent-trogiang");
    private By formDangKyMenu = By.id("child-trogiang-register");
    private By deXuatMenu = By.id("child-trogiang-advances");
    private By thongTinTroGiangMenu = By.id("child-trogiang-dadangky");
    private By ketQuaDanhGiaMenu = By.id("child-trogiang-reviewtask");

    // TKB
    private By tkbMenu = By.id("parent-studentaffairs-studentaffairstimetable");
    private By xemTkbMenu = By.id("child-studentaffairs-studentaffairstimetable-index");
    private By importTkbMenu = By.id("child-studentaffairs-studentaffairstimetable-import");

    // Người dùng
    private By userMenu = By.id("child-user-index");

    // Hàm mở Trang chủ
    public void openDashboard() {
        WebUI.waitForElementClickable(driver, dashboardMenu).click();
    }

    // Hàm mở Thống kê → Trợ giảng
    public void openThongKeTroGiang() {
        WebUI.waitForElementClickable(driver, thongKeMenu).click();
        WebUI.waitForElementClickable(driver, thongKeTroGiangMenu).click();
    }

    // Hàm mở Thống kê → Thù lao
    public void openThongKeThuLao() {
        WebUI.waitForElementClickable(driver, thongKeMenu).click();
        WebUI.waitForElementClickable(driver, thongKeThuLaoMenu).click();
    }

    // Hàm mở Trợ giảng → Biểu mẫu đăng ký
    public void openFormDangKy() {
        WebUI.waitForElementClickable(driver, troGiangMenu).click();
        WebUI.waitForElementClickable(driver, formDangKyMenu).click();
    }

    // Hàm mở Trợ giảng → Đề xuất trợ giảng
    public void openDeXuatTroGiang() {
        WebUI.waitForElementClickable(driver, troGiangMenu).click();
        WebUI.waitForElementClickable(driver, deXuatMenu).click();
    }

    // Hàm mở Trợ giảng → Thông tin trợ giảng
    public void openThongTinTroGiang() {
        WebUI.waitForElementClickable(driver, troGiangMenu).click();
        WebUI.waitForElementClickable(driver, thongTinTroGiangMenu).click();
    }

    // Hàm mở Trợ giảng → Kết quả đánh giá
    public void openKetQuaDanhGia() {
        WebUI.waitForElementClickable(driver, troGiangMenu).click();
        WebUI.waitForElementClickable(driver, ketQuaDanhGiaMenu).click();
    }

    // Hàm mở TKB → Xem TKB
    public void openXemTKB() {
        WebUI.waitForElementClickable(driver, tkbMenu).click();
        WebUI.waitForElementClickable(driver, xemTkbMenu).click();
    }

    // Hàm mở TKB → Import TKB
    public void openImportTKB() {
        WebUI.waitForElementClickable(driver, tkbMenu).click();
        WebUI.waitForElementClickable(driver, importTkbMenu).click();
    }

    // Hàm mở Người dùng
    public void openUsers() {
        WebUI.waitForElementClickable(driver, userMenu).click();
    }
}
