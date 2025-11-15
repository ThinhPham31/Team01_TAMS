package GV.Testcases;

import Commons.GVBaseTest;
import GV.Pages.NotificationArea;
import Commons.WebUI;
import org.testng.Assert;
import org.testng.annotations.Test;


public class F123_XoaThongBaoTC extends GVBaseTest {

    @Test
    public void testDeleteNotification() {

        String notiId = "559";  // có thể thay bằng ID khác đang có

        NotificationArea noti = new NotificationArea(driver);

        // B1: Mở dropdown thông báo
        noti.openNotificationDropdown();

        // B2: Xóa thông báo
        noti.deleteNotification(notiId);

        // B3: Kiểm tra element không còn tồn tại
        boolean isExist = WebUI.checkElementExist(driver, noti.notiItem(notiId));
        Assert.assertFalse(isExist, "Thông báo với ID " + notiId + " vẫn còn tồn tại sau khi Xóa!");

        System.out.println("✔ F1.2.3 – Đã xóa thông báo ID: " + notiId);
    }
}
