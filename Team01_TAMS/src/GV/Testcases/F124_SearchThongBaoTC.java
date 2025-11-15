package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.NotificationArea;
import org.testng.annotations.Test;

public class F124_SearchThongBaoTC extends GVBaseTest {

    @Test
    public void testSearchNotification() {

        String keyword = "Ứng tuyển trợ giảng";

        NotificationArea noti = new NotificationArea(driver);

        // B1: Mở dropdown
        noti.openNotificationDropdown();

        // B2: Tìm kiếm theo từ khóa
        noti.search(keyword);

        // (Có thể assert thêm theo nội dung text nếu cần)
        System.out.println("✔ F1.2.4 – Đã tìm kiếm thông báo với từ khóa: " + keyword);
    }
}
