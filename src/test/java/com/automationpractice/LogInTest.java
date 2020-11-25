package com.automationpractice;

import com.automationpractice.base.BaseTest;
import com.automationpractice.base.CsvDataProvider;
import com.automationpractice.pages.AccountCreationPage;
import com.automationpractice.pages.LogInPage;
import com.automationpractice.pages.MyAccountPage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Locale;
import java.util.Map;


public class LogInTest extends BaseTest {

    @Test(priority = 2, groups = { "positive" }, dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void positiveGuestCanRegisterTest(Map<String, String> testData) {
        LogInPage logInPage = new LogInPage(driver, log);
        String correctAccountCreationPageName = "CREATE AN ACCOUNT";
        String correctMyAccountPageName = "MY ACCOUNT";
        Faker faker = new Faker(new Locale("en-US"));
        String emailAddress = faker.internet().emailAddress();
        String testNumber = testData.get("no");
        String description = testData.get("description");
        String firstName = testData.get("firstName");
        String lastName = testData.get("lastName");
        String password = testData.get("password");
        String streetAddress = testData.get("streetAddress");
        String city = testData.get("city");
        String state = testData.get("state");
        String zipCode = testData.get("zipCode");
        String phoneNumber = testData.get("phoneNumber");

        log.info("Test № #" + testNumber + " for " + description + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\npassword: " + password + "\nAddress: " + streetAddress + "\ncity: " + city + "\nstate: " + state + "\nzipCode: " + zipCode + "\nphoneNumber: " + phoneNumber);

        // Open Login page - http://automationpractice.com/index.php?controller=authentication&back=my-account/
        logInPage.openLogInPage();

        // Fill up email
        logInPage.fillUpEmail(emailAddress);

        // Push "Create an account" button and wait for account creation page to load
        AccountCreationPage accountCreationPage = logInPage.pushCreateAnAccountButton();
        accountCreationPage.waitForAccountCreationPageToLoad();

        // Verification
        // - Verify name of the page is correct - CREATE AN ACCOUNT
        log.info("Verification ACCOUNT CREATION PAGE");
        Assert.assertTrue(accountCreationPage.isCorrectAccountCreationPageLoaded(correctAccountCreationPageName), "Account creation page name is not expected");

        // Fill up First name and wait when validation is complete
        accountCreationPage.fillUpFirstName(firstName);
        accountCreationPage.waitValidationFirstNameIsComplete();

        // Fill up Last name and wait when validation is complete
        accountCreationPage.fillUpLastName(lastName);
        accountCreationPage.waitValidationLastNameIsComplete();

        // Fill up password and wait when validation is complete
        accountCreationPage.fillUpPassword(password);
        accountCreationPage.waitValidationPasswordIsComplete();

        // Fill up data
        accountCreationPage.fillUpData(streetAddress, city, state, zipCode, phoneNumber);

        // Push "Register" button and wait when registration is complete
        MyAccountPage myAccountPage = accountCreationPage.pushRegisterButton();
        myAccountPage.waitRegistrationIsComplete();

        // Verification
        // - Verify name of the page is correct - MY ACCOUNT
        log.info("Verification MY ACCOUNT PAGE");
        Assert.assertTrue(myAccountPage.isCorrectMyAccountPageLoaded(correctMyAccountPageName), "My account page name is not expected");
    }

    @Test(priority = 3, groups = { "negative" })
    public void negativeErrorMessageLogInPageTest() {
        LogInPage logInPage = new LogInPage(driver, log);
        String expectedErrorMessage = "Invalid email address";

        // Open Login page - http://automationpractice.com/index.php?controller=authentication&back=my-account/
        logInPage.openLogInPage();

        // Push "Create an account" button
        logInPage.pushCreateAnAccountButton();

        // Verification
        // - Verify Error message in login page
        log.info("Verification error message in login page");
        String errorMessage = logInPage.getLogInErrorMessage();
        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                    "Error message is not expected. \nExpected: " + expectedErrorMessage + "\nActual:" + errorMessage);
    }

    @Test(priority = 4, groups = { "negative" })
    public void negativeErrorMessageAccountCreationPageTest() {
        LogInPage logInPage = new LogInPage(driver, log);
        Faker faker = new Faker(new Locale("en-US"));
        String emailAddress = faker.internet().emailAddress();
        String expectedErrorMessage = "There are 8 errors";

        // Open Login page - http://automationpractice.com/index.php?controller=authentication&back=my-account/
        logInPage.openLogInPage();

        // Fill up email
        logInPage.fillUpEmail(emailAddress);

        // Push "Create an account" button and wait for account creation page to load
        AccountCreationPage accountCreationPage = logInPage.pushCreateAnAccountButton();
        accountCreationPage.waitForAccountCreationPageToLoad();

        // Push "Register" button
        accountCreationPage.pushRegisterButton();

        // Verification
        // - Verify Error message in account creation page
        log.info("Verification error message in account creation page");
        String errorMessage = accountCreationPage.getAccountCreationErrorMessage();
        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                "Error message is not expected. \nExpected: " + expectedErrorMessage + "\nActual:" + errorMessage);
    }
}
