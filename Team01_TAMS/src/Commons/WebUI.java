package Commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Helpers.PropertiesHelper;

public class WebUI {
	private static int TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT_TIMEOUT"));
	private static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
	private static double STEP_TIME = Double.parseDouble(PropertiesHelper.getValue("STEP_TIME"));
	
	static {
	    System.out.println("====== WebUI CONFIG VALUES ======");
	    System.out.println("TIMEOUT = " + TIMEOUT);
	    System.out.println("PAGE_LOAD_TIMEOUT = " + PAGE_LOAD_TIMEOUT);
	    System.out.println("STEP_TIME = " + STEP_TIME);
	    System.out.println("=================================");
	}
	
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForElementVisible(WebDriver driver, By by, int second) {
        System.out.println("➡ Waiting for element visible: " + by.toString());
        System.out.println("➡ Visible Timeout = " + second);

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(500, TimeUnit.MILLISECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementPresent(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By by) {
        if (driver == null) {
            System.out.println("❌ ERROR: WebDriver is NULL in waitForElementClickable!");
        }

        System.out.println("➡ Waiting for element clickable: " + by.toString());
        System.out.println("➡ Using TIMEOUT = " + TIMEOUT);

        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

        System.out.println("✔ Element is now clickable: " + by.toString());

        return element;
    }

    public static Boolean checkElementExist(WebDriver driver, By by) {
        List<WebElement> listElement = driver.findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " is existing.");
            return true;
        } else {
            System.out.println("Element " + by + " has NOT existed.");
            return false;
        }
    }

    public static Boolean checkElementExist(WebDriver driver, String xpath) {
        List<WebElement> listElement = driver.findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist.");
            return false;
        }
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded(WebDriver driver) {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.pollingEvery(500, TimeUnit.MILLISECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }
}
