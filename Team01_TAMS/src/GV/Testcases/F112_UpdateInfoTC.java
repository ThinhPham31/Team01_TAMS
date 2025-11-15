package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.ThongTinCaNhanPage;
import org.testng.annotations.Test;

public class F112_UpdateInfoTC extends GVBaseTest {

    @Test
    public void testUpdateThongTinCaNhan() {

        ThongTinCaNhanPage infoPage = new ThongTinCaNhanPage(driver);

        // B1: Mở modal
        infoPage.openModal();

        // B2: Cập nhật thông tin
        infoPage.updatePhone("0329000000");
        infoPage.updateDOB("2003-03-31");
        infoPage.selectGender("Nam");

        // B3: Lưu
        infoPage.save();

        System.out.println("✔ F1.1.2 – Đã cập nhật thông tin cá nhân (SĐT, ngày sinh, giới tính).");
    }
}
