package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.ClassSectionPage;
import GV.Pages.DiemDanhPopup;
import org.testng.annotations.Test;


public class F103_TimKiemSinhVienTC extends GVBaseTest {

    @Test
    public void testSearchStudentInDiemDanh() {

        String maLHP = "251_71ITBS10103_0102";
        String keyword = "Nguyễn Hoàng";

        ClassSectionPage classPage = new ClassSectionPage(driver);
        DiemDanhPopup diemDanhPopup = new DiemDanhPopup(driver);

        // B1: Mở popup điểm danh
        classPage.searchLHP(maLHP);
        classPage.openDiemDanh(maLHP);
        diemDanhPopup.waitPopupLoaded();

        // B2: Tìm kiếm theo tên
        diemDanhPopup.search(keyword);

        System.out.println("✔ F1.0.3 – Đã tìm kiếm sinh viên với từ khóa: " + keyword);
    }
}
