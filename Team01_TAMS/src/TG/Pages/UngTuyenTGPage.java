package TG.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class UngTuyenTGPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // ==== Locators ====
    private By applyButton = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/table/tbody/tr[1]/td[6]/span/a");
    private By avgScoreField = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[7]/div/input");
    private By conductScoreField = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[8]/div/input");
    private By finalScoreField = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[9]/div/input");
    private By uploadFileInput = By.cssSelector("input[type='file']");
    private By saveButton = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[3]/button[3]");
    private By successMessage = By.xpath("/html/body/div[6]/div/h2");
    private By errorMessages1 = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[7]/div/span");
    private By errorMessages2 = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[8]/div/span");
    private By errorMessages3 = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[9]/div/span");
    private By errorMessages4 = By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div/div[10]/div/span");
    private By TroGiangButton = By.xpath("//*[@id=\"parent-trogiang\"]");
    private By DangKyButton = By.xpath("//*[@id=\"child-trogiang-apply\"]");
    private By ListNganh = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[2]/select");
    private By ChonNganhDB = By.xpath("/html/body/div[2]/main/section/div[2]/div/div/div[1]/div/div[2]/select/option[2]");
    private By ChonNganhKhac = By.xpath("");

    public UngTuyenTGPage(WebDriver driver) {
        this.driver = driver;
    }

    // ==== Actions ====
    public void openApplyFormNDB() throws InterruptedException {
    	driver.findElement(TroGiangButton).click();
    	driver.findElement(DangKyButton).click();
    	Thread.sleep(2000);
    	driver.findElement(ListNganh).click();
    	driver.findElement(ChonNganhDB).click();
    	Thread.sleep(500);
        driver.findElement(applyButton).click();
        Thread.sleep(1000);
    }
    
    public void openApplyFormNK() throws InterruptedException {
    	driver.findElement(TroGiangButton).click();
    	driver.findElement(DangKyButton).click();
    	Thread.sleep(2000);
    	driver.findElement(ListNganh).click();
    	driver.findElement(ChonNganhKhac).click();
    	Thread.sleep(500);
        driver.findElement(applyButton).click();
        Thread.sleep(1000);
    }

    public void fillForm(String avg, String conduct, String finalScore, String filePath) {
        if (avg != null) driver.findElement(avgScoreField).sendKeys(avg);
        if (conduct != null) driver.findElement(conductScoreField).sendKeys(conduct);
        if (finalScore != null) driver.findElement(finalScoreField).sendKeys(finalScore);
        if (filePath != null) driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public void isSuccessMessageDisplayed() throws InterruptedException {
    	Thread.sleep(1000);
    	if (driver.findElement(successMessage).isDisplayed()) {
    		System.out.println("Ứng Tuyển Thành Công được hiển thị");
    	} else {
    		System.out.println("Ứng Tuyển Thành Công không được hiển thị");
    	}
        //return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    public boolean hasErrorMessage(String expectedText1, String expectedText2, String expectedText3, String expectedText4) {
    	try {
            WebElement msg1 = driver.findElement(errorMessages1);
            WebElement msg2 = driver.findElement(errorMessages2);
            WebElement msg3 = driver.findElement(errorMessages3);
            WebElement msg4 = driver.findElement(errorMessages4);

            boolean match1 = msg1.getText().contains(expectedText1);
            boolean match2 = msg2.getText().contains(expectedText2);
            boolean match3 = msg3.getText().contains(expectedText3);
            boolean match4 = msg4.getText().contains(expectedText4);

            return match1 && match2 && match3 && match4;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clearAllFields() {
        driver.findElement(avgScoreField).clear();
        driver.findElement(conductScoreField).clear();
        driver.findElement(finalScoreField).clear();
    }
}
