package com.automationpractice;

import com.automationpractice.base.BaseTest;
import com.automationpractice.pages.LogInPage;
import com.automationpractice.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MainTest extends BaseTest {

    @Test(priority = 1, groups = { "positive" })
    public void positiveGuestCanGoToLogInPageTest() {
        MainPage mainPage = new MainPage(driver, log);
        String expectedPageTitle = "Login - My Store";
        String correctLogInPageName = "AUTHENTICATION";

        // Open main page - http://automationpractice.com/
        mainPage.openMainPage();

        // Push Sing in button and wait for login page to load
        LogInPage logInPage = mainPage.pushSignInButton();
        logInPage.waitForLogInPageToLoad();

        // Verification
        // - Verify title of the page is correct - Login - My Store
        log.info("Verification");
        String actualTitle = logInPage.getTitle();
        Assert.assertTrue(expectedPageTitle.equals(actualTitle),
                "Page title is not expected. \nExpected: " + expectedPageTitle + "\nActual:" + actualTitle);

        // - Verify name of the page is correct - AUTHENTICATION
        Assert.assertTrue(logInPage.isCorrectLogInPageLoaded(correctLogInPageName), "Login page name is not expected");
    }
}
