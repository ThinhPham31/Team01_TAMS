package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.ClassSectionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class F101_ViewStudentListTC extends GVBaseTest {

    @Test
    public void testViewListLHP() {

        // Khởi tạo Page Lớp học phần
        ClassSectionPage page = new ClassSectionPage(driver);

        // B1: Tìm kiếm theo mã LHP cụ thể
        String maLHP = "251_71ITBS10103_0102";
        page.searchLHP(maLHP);

        // B2: Kiểm tra table có ít nhất 1 dòng
        int rowCount = page.getRowCount();
        Assert.assertTrue(rowCount > 0, "Không có dòng nào trong bảng sau khi tìm: " + maLHP);

        System.out.println("✔ F1.0.1 – Danh sách LHP hiển thị đúng, tìm được: " + maLHP);
    }
}
