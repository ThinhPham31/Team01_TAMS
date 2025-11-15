package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.ClassSectionPage;
import GV.Pages.KetQuaDiemDanhPopup;
import org.testng.annotations.Test;

public class F106_KetQuaDiemDanhTC extends GVBaseTest {

    @Test
    public void testViewKetQuaDiemDanh() {

        String maLHP = "251_71ITBS10103_0102";
        String mssv  = "2174802010489";

        ClassSectionPage classPage = new ClassSectionPage(driver);
        KetQuaDiemDanhPopup resultPopup = new KetQuaDiemDanhPopup(driver);

        // B1: Mở popup Kết quả điểm danh
        classPage.searchLHP(maLHP);
        classPage.openKetQuaDiemDanh(maLHP);

        // B2: Chờ popup hiển thị
        resultPopup.waitLoaded();

        // B3: Tìm kiếm theo MSSV
        resultPopup.search(mssv);

        System.out.println("✔ F1.0.6 – Đã mở và tìm kiếm kết quả điểm danh cho MSSV: " + mssv);
    }
}
