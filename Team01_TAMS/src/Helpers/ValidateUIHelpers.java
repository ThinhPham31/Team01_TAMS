package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ValidateUIHelpers {
    private WebDriver driver;
    private WebDriverWait wait;
    private long timeoutWaitForPageLoaded = 30; // giây

    public ValidateUIHelpers(WebDriver _driver){
        driver = _driver;
        wait = new WebDriverWait(driver, 10); // 10 giây mặc định
    }

    public void setText(By element, String value){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).sendKeys(value);
    }

    public boolean verifyUrl(String url)
    {
        System.out.println(driver.getCurrentUrl());
        System.out.println(url);

        return driver.getCurrentUrl().contains(url); //True/False
    }

    public boolean verifyElementText(By element, String textValue){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(textValue); //True/False
    }

    public boolean verifyElementExist(By element){
        List<WebElement> listElement = driver.findElements(element);
        return listElement.size() > 0;
    }

    // Wait
    public void waitForPageLoaded(){
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            wait = new WebDriverWait(driver, timeoutWaitForPageLoaded); // Selenium 3 style
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }
    }
    
    public void compareList(List<Map<String, String>> expected, List<Map<String, String>> actual) {

        // Kiểm tra số lượng dòng giữa UI và dữ liệu Excel
        Assert.assertEquals(
            actual.size(),
            expected.size(),
            "Số dòng giữa UI và Excel không khớp"
        );

        // Duyệt từng dòng theo index
        for (int i = 0; i < expected.size(); i++) {

            Map<String, String> expectedRow = expected.get(i); // Dòng từ Excel
            Map<String, String> actualRow = actual.get(i);     // Dòng từ UI

            // Duyệt qua từng cột trong dòng Excel
            for (String key : expectedRow.keySet()) {

                String expectedValue = expectedRow.get(key).trim(); // Giá trị kỳ vọng
                String actualValue = actualRow.get(key).trim();     // Giá trị UI thực tế

                // So sánh giá trị từng cột, báo lỗi rõ ràng khi sai
                Assert.assertEquals(
                    actualValue,
                    expectedValue,
                    "Sai dữ liệu tại dòng " + (i + 1) + " - cột: " + key
                );
            }
        }
    }

}
