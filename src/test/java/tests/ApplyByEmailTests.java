package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.ApplyByEmailPageObject;
import pageobjects.ApplyByLinkPageObject;

import static pageobjects.BasePageObject.waitFor;

/**
 * Created by vitaliybizilia on 6/18/17.
 */
public class ApplyByEmailTests {
    @Test
    public void loginAndCheckValidApplyByEmail(){
        ApplyByEmailPageObject applyByEmail = new ApplyByEmailPageObject();
        applyByEmail.openHomePage();
        applyByEmail.clickLoginOnTopMenu();
        applyByEmail.login("john.smith@gmail.com", "johnsmith");
        applyByEmail.selectJob("Test applcation e-mail");
        applyByEmail.apply();
        applyByEmail.typeCoverMessageAndSelectFile();
        applyByEmail.apply();
        Assert.assertEquals(applyByEmail.getSuccessfulMessageText(), "Your application was successfully sent!");

    }
    @Test
    public void signUpByEmailAndCheckValidApply(){
        ApplyByEmailPageObject applyByEmail = new ApplyByEmailPageObject();
        applyByEmail.openHomePage();
        applyByEmail.clickLoginOnTopMenu();
        applyByEmail.signUp();
        applyByEmail.selectJob("Test applcation e-mail");
        applyByEmail.apply();
        applyByEmail.typeCoverMessageAndSelectFile();
        applyByEmail.apply();
        Assert.assertEquals(applyByEmail.getSuccessfulMessageText(), "Your application was successfully sent!");

    }
    @Test
    public void signUpByFacebookAndCheckValidApply(){
        ApplyByEmailPageObject applyByEmail = new ApplyByEmailPageObject();
        applyByEmail.openHomePage();
        applyByEmail.clickLoginOnTopMenu();
        applyByEmail.signUpwithfacebook("riottest1606@gmail.com", "riot1606test");
        applyByEmail.selectJob("Test applcation e-mail");
        applyByEmail.apply();
        applyByEmail.typeCoverMessageAndSelectFile();
        applyByEmail.apply();
        Assert.assertEquals(applyByEmail.getSuccessfulMessageText(), "Your application was successfully sent!");

    }
    @Test
    public void applyAsNotLoggedInUser() {
        ApplyByEmailPageObject applyByEmail = new ApplyByEmailPageObject();
        applyByEmail.openHomePage();
        waitFor(5);
        applyByEmail.selectJob("Test applcation e-mail");
        applyByEmail.apply();
        applyByEmail.login("john.smith@gmail.com", "johnsmith");
        applyByEmail.typeCoverMessageAndSelectFile();
        applyByEmail.apply();
        Assert.assertEquals(applyByEmail.getSuccessfulMessageText(), "Your application was successfully sent!");
    }
    @Test
    public void checkSignUpAsCompany(){

        ApplyByEmailPageObject applyByEmail = new ApplyByEmailPageObject();
        applyByEmail.openHomePage();
        waitFor(5);
        applyByEmail.clickLoginOnTopMenu();
        applyByEmail.signUpAsCompany();
        applyByEmail.selectJob("Test applcation e-mail");
        waitFor(5);
        applyByEmail.getApplyBtnPresent();

    }
    @Test
    public void loginAndCheckCancelButton() {
        ApplyByEmailPageObject applyByEmail = new ApplyByEmailPageObject();
        applyByEmail.openHomePage();
        applyByEmail.clickLoginOnTopMenu();
        applyByEmail.login("john.smith@gmail.com", "johnsmith");
        applyByEmail.selectJob("Test applcation e-mail");
        applyByEmail.apply();
        applyByEmail.clickOnCancelButton();
        Assert.assertEquals(applyByEmail.getTitleOfJobPage(), "Test applcation e-mail");
    }
}
