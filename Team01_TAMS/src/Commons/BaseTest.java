package Commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected void setupDriver() {
        String driverPath = System.getProperty("user.dir")
                + "\\Team01_TAMS\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 15); // Selenium 3: truyền số giây
    }

    protected void quitDriver() {
        if (driver != null) driver.quit();
    }
}
