package pageobjects;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;


import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static setup.SeleniumDriver.driver;
import static setup.SeleniumDriver.getDriver;

/**
 * Created by vitaliybizilia on 5/17/17.
 */
public abstract class BasePageObject {
    private WebDriver driver;


    public BasePageObject(WebDriver driver) {
        this.driver = driver;

    }


    // Custom wait method for waiting until element is visible
    public WebElement fluentWaitforElement(WebElement element, int timoutSec, int pollingSec) {

        FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(timoutSec, TimeUnit.SECONDS)
                .pollingEvery(pollingSec, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class, TimeoutException.class).ignoring(StaleElementReferenceException.class);

        for (int i = 0; i < 2; i++) {
            try {
                fWait.until(ExpectedConditions.visibilityOf(element));
                fWait.until(ExpectedConditions.elementToBeClickable((By) element));
            } catch (Exception e) {

                System.out.println("Element Not found trying again - " + element.toString().substring(70));
                e.printStackTrace();

            }
        }

        return element;

    }

    public static void waitFor(Integer seconds) {
        getDriver().manage().timeouts().setScriptTimeout(seconds, TimeUnit.SECONDS);
    }


    public void openHomePage(){
        getDriver().get("http://dev.timetoriot.com/");
    }
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
    public void login(String emailValue, String passValue){
        waitForPageLoaded();
        WebElement alreadyUserLnk = getDriver().findElement(By.xpath("//p[text()='Already a user? Log in']"));
        alreadyUserLnk.click();
        waitFor(3);
        WebElement emailField = getDriver().findElement(By.xpath("//input[@type='email']"));
        emailField.sendKeys(emailValue);
        WebElement passwordField = getDriver().findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys(passValue);
        waitFor(3);
        WebElement loginSubmitBtn = getDriver().findElement(By.xpath("//button[@type='submit']"));
        loginSubmitBtn.click();
        waitForPageLoaded();
        WebElement jobs = getDriver().findElement(By.xpath("//li/a[@ui-sref='index.jobs_index']"));
        jobs.click();
        waitFor(3);
    }
    public void clickLoginOnTopMenu(){
        waitForPageLoaded();
        WebElement loginBtn = getDriver().findElement(By.xpath("//button[text()='Log in']"));
        loginBtn.click();
    }
    public void selectJob(String applicationDescription){
        waitForPageLoaded();
        WebElement jobDesc = getDriver().findElement(By.xpath("//p[text()='"+applicationDescription+"']"));
        jobDesc.click();
        waitFor(3);

    }
    public void apply(){
        waitForPageLoaded();
        WebElement applyBtn = getDriver().findElement(By.xpath("//button[text()='Apply']"));
        applyBtn.click();
    }
    public String generateEmail(String domain, int length) {
        return RandomStringUtils.random(length, "abcdefghijklmnopqrstuvwxyz") + "@" + domain;
    }
    public void signUp(){
        waitForPageLoaded();
        WebElement signUpWithEmail = getDriver().findElement(By.xpath("//button[@ng-click='signUpClicked()']"));
        signUpWithEmail.click();
        waitFor(3);
        WebElement fullNamefield = getDriver().findElement(By.xpath("//input[@name='uName']"));
        fullNamefield.sendKeys("Testing Testing");
        waitFor(3);
        WebElement emaildField = getDriver().findElement(By.xpath("//form[@name='formSignUp']/input[@type='email']"));
        emaildField.click();
        String emailName = generateEmail("gmail.com", 5);
        emaildField.sendKeys(emailName);
        waitForPageLoaded();
        WebElement submitBtn = getDriver().findElement(By.xpath("//button[@ng-click='submitSignUp(signUpForm, formSignUp)']"));
        submitBtn.click();
        waitForPageLoaded();
        WebElement passwordField = getDriver().findElement(By.xpath("//input[@type='password']"));
        passwordField.sendKeys("testtest");
        waitFor(3);
        WebElement createProfileBtn = getDriver().findElement(By.xpath("//button[text()='Create my profile']"));
        createProfileBtn.click();
        waitForPageLoaded();
        WebElement jobs = getDriver().findElement(By.xpath("//li/a[@ui-sref='index.jobs_index']"));
        jobs.click();
        waitFor(3);
        getDriver().manage().deleteAllCookies();

    }
    public void switchingBetweenTabs(Integer count) {
        ArrayList tabs = new ArrayList(getDriver().getWindowHandles());
        System.out.println(tabs.size());
        getDriver().switchTo().window((String) tabs.get(count));


    }
    public void logOut(){
        try {
            WebElement profileBtn = getDriver().findElement(By.xpath("//div[@ng-click='goToMyProfile()']"));
            profileBtn.click();
            waitFor(3);
            WebElement logOut = getDriver().findElement(By.xpath("//a[@ng-click='logOut()']"));
            logOut.click();
            waitFor(4);
        } catch (StaleElementReferenceException e){
            System.out.println("User is already logged out");
        }
    }
    public void signUpwithfacebook(String emailValue, String passwordValue) {
        waitForPageLoaded();
        WebElement signUpWithFacebook = getDriver().findElement(By.xpath("//button[@ng-click='loginFacebook()']"));
        signUpWithFacebook.click();
        waitForPageLoaded();
        WebElement fbEmailField = getDriver().findElement(By.id("email"));
        fbEmailField.sendKeys(emailValue);
        WebElement fbPasswordField = getDriver().findElement(By.id("pass"));
        fbPasswordField .sendKeys(passwordValue);
        waitFor(2);
        WebElement loginButton = getDriver().findElement(By.id("loginbutton"));
        loginButton.click();
        waitForPageLoaded();
        WebElement jobs = getDriver().findElement(By.xpath("//li/a[@ui-sref='index.jobs_index']"));
        jobs.click();
        waitFor(3);
    }
    public void signUpAsCompany(){
        waitForPageLoaded();
        WebElement signUpAsCompany = getDriver().findElement(By.xpath("//button[@ng-click='signUpCompany()']"));
        signUpAsCompany.click();
        waitForPageLoaded();
        WebElement companyName = getDriver().findElement(By.xpath("//input[@ng-model='companyUser.company_name']"));
        companyName.sendKeys("Testing Test");
        WebElement personalName = getDriver().findElement(By.xpath("//input[@ng-model='companyUser.name']"));
        personalName.sendKeys("Testing Test");
        WebElement companyEmail = getDriver().findElement(By.xpath("//input[@ng-model='companyUser.email']"));
        companyEmail.sendKeys(generateEmail("test.com", 5));
        WebElement switchLabel = getDriver().findElement(By.xpath("//label[@for='email_switch']"));
        switchLabel.click();
        WebElement companyPassword = getDriver().findElement(By.xpath("//input[@ng-model='companyUser.password']"));
        companyPassword .sendKeys("12345678");
        WebElement claimBtn = getDriver().findElement(By.xpath("//button[@ng-click='createCompanyProfile(companyUser, form)']"));
        claimBtn.click();
        waitForPageLoaded();
        WebElement jobs = getDriver().findElement(By.xpath("//li/a[@ui-sref='index.jobs_index']"));
        jobs.click();
        waitFor(3);
    }
    public void getApplyBtnPresent() {
        waitForPageLoaded();
        if (driver.findElements(By.xpath("//button[text()='Apply']")).size() != 0) {
            System.out.println("Element is Absent");
        } else {
            System.out.println("Element is Present");
        }
    }

}

