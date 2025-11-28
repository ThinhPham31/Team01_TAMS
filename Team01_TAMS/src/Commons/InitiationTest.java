package Commons;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

//import org.testng.annotations.Test;

import java.time.Duration;
import Helpers.PropertiesHelper;

public class InitiationTest {

	protected WebDriver driver;
	
	static String driverPath = "resources\\drivers\\";

	public WebDriver getDriver() {
		return driver;
	}
	
	//Hàm này để tùy chọn Browser. Cho chạy trước khi gọi class này (BeforeClass)
	private void setDriver(String browserType, String appURL) 
	{
		switch (browserType) 
		{
			case "chrome":
				driver = initChromeDriver(appURL);
				break;
			case "firefox":
				driver = initFirefoxDriver(appURL);
				break;
			default:
				System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
				driver = initChromeDriver(appURL);
		}
		
		driver.manage().window().maximize();
		long pageLoadTimeout = Long.parseLong(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

	}	
	
	//Khởi tạo cấu hình của các Browser để đưa vào Switch Case
	private static WebDriver initChromeDriver(String appURL) {
	    System.out.println("Launching Chrome browser...");

	    // Thư mục Desktop để lưu file tải về
	    String desktopPath = System.getProperty("user.home") + "\\Desktop";

	    // Thiết lập Chrome tự tải file về Desktop, không hiện popup
	    HashMap<String, Object> chromePrefs = new HashMap<>();
	    chromePrefs.put("download.default_directory", desktopPath);
	    chromePrefs.put("download.prompt_for_download", false);
	    chromePrefs.put("download.directory_upgrade", true);
	    chromePrefs.put("safebrowsing.enabled", true);

	    ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("prefs", chromePrefs);

	    // Load driver
	    System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
	    WebDriver driver = new ChromeDriver(options);

	    // Cấu hình mặc định của bạn – giữ nguyên
	    driver.manage().window().maximize();
	    driver.navigate().to(appURL);
	    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	    return driver;
	}


	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser...");
		System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	// Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(@Optional("chrome") String browserType, @Optional("https://cntttest.vanlanguni.edu.vn:18081/Ta2025/") String appURL) {
		try 
		{
			// Khởi tạo driver và browser
			setDriver(browserType, appURL);
		} catch (Exception e) {
			System.out.println("Error..." + e.getStackTrace());
		}
	}

	@AfterMethod
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(2000);
//		driver.quit();
	}
}
