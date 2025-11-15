package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.ClassSectionPage;
import GV.Pages.DiemDanhPopup;
import org.testng.annotations.Test;

public class F102_DiemDanhTC extends GVBaseTest {

    @Test
    public void testDiemDanhSinhVien() {

        String maLHP = "251_71ITBS10103_0102";
        String mssv  = "2174802010489";

        ClassSectionPage classPage = new ClassSectionPage(driver);
        DiemDanhPopup diemDanhPopup = new DiemDanhPopup(driver);

        // B1: Mở popup Điểm danh cho lớp
        classPage.searchLHP(maLHP);
        classPage.openDiemDanh(maLHP);

        // B2: Chờ popup load
        diemDanhPopup.waitPopupLoaded();

        // B3: Tìm chính xác MSSV
        diemDanhPopup.search(mssv);

        // B4: Chọn trạng thái "Có mặt" và ghi chú
        diemDanhPopup.selectStatus(mssv, "Có mặt");
        diemDanhPopup.enterNote(mssv, "Test auto điểm danh");

        // B5: Lưu thông tin
        diemDanhPopup.submit();

        System.out.println("✔ F1.0.2 – Điểm danh cho MSSV " + mssv + " hoàn tất.");
    }
}
