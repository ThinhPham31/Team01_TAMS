package GV.Testcases;

import Commons.InitiationTest;
import GV.Pages.GVDashboardPage;
import GV.Pages.ClassSectionPage;
import GV.Pages.DiemDanhPopup;
import Helpers.ValidateUIHelpers;
import Helpers.authenSupport;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class F102_DiemDanhTC extends InitiationTest {

    private GVDashboardPage gvDashboardPage;
    private ClassSectionPage classSectionPage;
    private DiemDanhPopup diemDanhPopup;
    private ValidateUIHelpers validateUIHelpers;
    private authenSupport auth;

    @BeforeClass
    public void loginAsGV() throws InterruptedException {

        // KHÔNG được khai báo lại WebDriver driver
        // driver đã được tạo từ InitiationTest.initializeTestBaseSetup()

        validateUIHelpers = new ValidateUIHelpers(driver);

        // Login GV (OTP bạn nhập tay)
        auth = new authenSupport(driver);
        gvDashboardPage = auth.loginWithGV();

        validateUIHelpers.waitForPageLoaded();

        // Mở menu Lớp học phần
        gvDashboardPage.clickClassSectionMenu(validateUIHelpers);

        // Khởi tạo ClassSectionPage
        classSectionPage = new ClassSectionPage(driver, validateUIHelpers);

        classSectionPage.waitForTableLoaded();
    }

    @Test(priority = 1)
    public void TC01_UpdateAttendanceStatus() throws InterruptedException {

        String maLHP = "251_71ITBS10103_0102";

        // 1. Chọn filter
        classSectionPage.selectHocKy("251");
        classSectionPage.selectNganh("Công Nghệ Thông Tin_TH0102");

        // 2. Tìm đúng lớp
        classSectionPage.searchLHP(maLHP);

        // 3. Mở popup điểm danh
        classSectionPage.openDiemDanhPopup(maLHP);

        // 4. Khởi tạo popup đúng cách
        diemDanhPopup = new DiemDanhPopup(driver, validateUIHelpers);

        // 5. Chờ popup load
        diemDanhPopup.waitLoaded();

        // 6. MSSV test
        String mssv = "2174802010489";

        // 7. Tìm sinh viên
        diemDanhPopup.searchStudent(mssv);

        // 8. Chọn trạng thái
        diemDanhPopup.selectStatus(mssv, "Có mặt");

        // 9. Ghi chú
        diemDanhPopup.inputNote(mssv, "Điểm danh bằng script – TC01");

        // 10. Lưu
        diemDanhPopup.clickSave();

        Thread.sleep(1500);
    }
}
