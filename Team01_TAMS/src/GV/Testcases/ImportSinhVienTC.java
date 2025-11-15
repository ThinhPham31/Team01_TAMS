package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.ClassSectionPage;
import GV.Pages.ImportPopup;
import org.testng.annotations.Test;


public class F104_ImportSinhVienTC extends GVBaseTest {

    @Test
    public void testImportDSSV() {

        String maLHP = "251_71ITBS10103_0102";


        String filePath = System.getProperty("user.dir") + "\\TestData\\DSSV_251_71ITBS10103_0102.xlsx";

        ClassSectionPage classPage = new ClassSectionPage(driver);
        ImportPopup importPopup = new ImportPopup(driver);

        // B1: Mở popup Import
        classPage.searchLHP(maLHP);
        classPage.openImport(maLHP);

        // B2: Chờ popup
        importPopup.waitPopup();

        // B3: Upload file & Import
        importPopup.upload(filePath);
        importPopup.submit();

        System.out.println("✔ F1.0.4 – Đã gửi file import DSSV cho lớp: " + maLHP);
    }
}
