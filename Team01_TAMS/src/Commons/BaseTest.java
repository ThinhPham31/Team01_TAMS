package Commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Gọi trong @BeforeTest của từng test class
    protected void setupDriver() {
        // Đường dẫn chromedriver.exe trong project
        String driverPath = System.getProperty("user.dir")
                + "\\resources\\drivers\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
