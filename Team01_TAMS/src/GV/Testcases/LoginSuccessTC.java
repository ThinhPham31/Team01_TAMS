package GV.Testcases;

import Commons.GVBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSuccessTC extends GVBaseTest {

    @Test
    public void verifyLoginSuccess() {
        // Kiểm tra sau khi login xong có xuất hiện menu Lớp học phần
        By menuLopHocPhan = By.id("child-classsection-index");
        boolean isDisplayed = driver.findElement(menuLopHocPhan).isDisplayed();

        Assert.assertTrue(isDisplayed, "Menu Lớp học phần không hiển thị → Login có thể đã lỗi!");
    }
}
