package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.ApplyByLinkPageObject;
import setup.SeleniumBaseTest;

import static setup.SeleniumDriver.getDriver;

/**
 * Created by vitaliybizilia on 6/16/17.
 */
public class ApplyByLinkTests extends SeleniumBaseTest {
    @Test
    public void validLoginWithApply(){
        ApplyByLinkPageObject applyByLink = new ApplyByLinkPageObject();
        applyByLink.openHomePage();
        applyByLink.clickLoginOnTopMenu();
        applyByLink.login("john.smith@gmail.com", "johnsmith");
        applyByLink.selectJob("Test application link");
        applyByLink.apply();
        getDriver().close();
        applyByLink.switchingBetweenTabs(0);

    }
    @Test
    public void zvalidSignUpnWithEmail(){

        ApplyByLinkPageObject applyByLink = new ApplyByLinkPageObject();
        applyByLink.openHomePage();
        waitFor(5);
        applyByLink.logOut();
        applyByLink.clickLoginOnTopMenu();
        applyByLink.signUp();
        applyByLink.selectJob("Test application link");
        applyByLink.apply();
        getDriver().close();
        applyByLink.switchingBetweenTabs(0);
    }
    @Test
    public void validSignUpnWithFacebook(){

        ApplyByLinkPageObject applyByLink = new ApplyByLinkPageObject();
        applyByLink.openHomePage();
        waitFor(5);
        applyByLink.logOut();
        applyByLink.clickLoginOnTopMenu();
        applyByLink.signUpwithfacebook("riottest1606@gmail.com", "riot1606test");
        applyByLink.selectJob("Test application link");
        applyByLink.apply();
        getDriver().close();
        applyByLink.switchingBetweenTabs(0);

    }
    @Test
    public void applyAsNotLoggedInUser(){

        ApplyByLinkPageObject applyByLink = new ApplyByLinkPageObject();
        applyByLink.openHomePage();
        waitFor(5);
        applyByLink.selectJob("Test application link");
        applyByLink.apply();
        applyByLink.login("john.smith@gmail.com", "johnsmith");
    }
    @Test
    public void SignUpAsCompany(){

        ApplyByLinkPageObject applyByLink = new ApplyByLinkPageObject();
        applyByLink.openHomePage();
        waitFor(5);
        applyByLink.clickLoginOnTopMenu();
        applyByLink.signUpAsCompany();
        applyByLink.selectJob("Test application link");
        applyByLink.getApplyBtnPresent();

    }

}
