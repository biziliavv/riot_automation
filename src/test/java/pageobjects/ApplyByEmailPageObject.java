package pageobjects;

import javafx.beans.value.WeakChangeListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static setup.SeleniumDriver.getDriver;

/**
 * Created by vitaliybizilia on 6/18/17.
 */
public class ApplyByEmailPageObject extends BasePageObject {
    public ApplyByEmailPageObject() {
        super(getDriver());
    }

    public void typeCoverMessageAndSelectFile(){
        waitForPageLoaded();
        WebElement coverLetter = getDriver().findElement(By.xpath("//textarea[@ng-model='mail.text']"));
        coverLetter.sendKeys("This is text for testing");
        waitFor(3);
        /*String filePath = System.getProperty("user.dir") + "/src/test.jpg";
        WebElement fileUpl = getDriver().findElement(By.xpath("//div[2]/div[1]/input"));
        fileUpl.sendKeys(filePath);*/

    }
    public String getSuccessfulMessageText(){
        waitForPageLoaded();
        return getDriver().findElement(By.xpath("/html/body/div[1]/div/div/p")).getText();

    }
    public void clickOnCancelButton(){
        waitForPageLoaded();
        WebElement cancelBtn = getDriver().findElement(By.xpath("//button[text()='Cancel']"));
        cancelBtn.click();
    }

    public String getTitleOfJobPage(){
        waitForPageLoaded();
        return getDriver().findElement(By.cssSelector("h1.job_details_about_heading.ng-binding")).getText();
    }
}
