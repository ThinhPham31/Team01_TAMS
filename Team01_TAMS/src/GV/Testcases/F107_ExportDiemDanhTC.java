package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.ClassSectionPage;
import GV.Pages.KetQuaDiemDanhPopup;
import org.testng.annotations.Test;

public class F107_ExportDiemDanhTC extends GVBaseTest {

    @Test
    public void testExportKetQuaDiemDanh() {

        String maLHP = "251_71ITBS10103_0102";

        ClassSectionPage classPage = new ClassSectionPage(driver);
        KetQuaDiemDanhPopup resultPopup = new KetQuaDiemDanhPopup(driver);

        // B1: Mở popup
        classPage.searchLHP(maLHP);
        classPage.openKetQuaDiemDanh(maLHP);
        resultPopup.waitLoaded();

        // B2: Nhấn Export
        resultPopup.exportFile();

        System.out.println("Đã nhấn Xuất file Excel kết quả điểm danh cho lớp: " + maLHP);
    }
}
