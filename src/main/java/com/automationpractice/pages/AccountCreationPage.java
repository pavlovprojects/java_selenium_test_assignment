package com.automationpractice.pages;

import com.automationpractice.base.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreationPage extends BasePageObject<AccountCreationPage> {
    private By accountCreationPageName = By.cssSelector("#noSlide .page-heading");
    private By accountCreationForm = By.cssSelector("#account-creation_form");
    private By firstNameField = By.cssSelector("#customer_firstname");
    private By lastNameField = By.cssSelector("#customer_lastname");
    private By firstNameValid = By.cssSelector(".form-ok #customer_firstname");
    private By lastNameValid = By.cssSelector(".form-ok #customer_lastname");
    private By passwordField = By.cssSelector("#passwd");
    private By passwordValid = By.cssSelector(".form-ok #passwd");
    private By streetAddressField = By.cssSelector("#address1");
    private By cityField = By.cssSelector("#city");
    private By stateDropdown = By.cssSelector("#id_state");
    private By zipCodeField = By.cssSelector("#postcode");
    private By phoneNumberField = By.cssSelector("#phone_mobile");
    private By registerButton = By.cssSelector("#submitAccount");
    private By errorMessage = By.cssSelector(".alert.alert-danger");


    public AccountCreationPage(WebDriver driver, Logger log) {

        super(driver, log);
    }

    public void waitForAccountCreationPageToLoad() {
        log.info("Waiting for AccountCreation page to load");
        waitForVisibilityOf(accountCreationForm);
    }

    public boolean isCorrectAccountCreationPageLoaded(String correctAccountCreationPageName) {
        if (getText(accountCreationPageName).equals(correctAccountCreationPageName)) {
            return true;
        }
        return false;
    }

    public void fillUpFirstName(String firstName) {
        type(firstName, firstNameField);
    }

    public void waitValidationFirstNameIsComplete() {
        log.info("Waiting, when First name validation is complete");
        waitForVisibilityOf(firstNameValid,5);
    }

    public void fillUpLastName(String lastName) {
        type(lastName, lastNameField);
    }

    public void waitValidationLastNameIsComplete() {
        log.info("Waiting, when Last name validation is complete");
        waitForVisibilityOf(lastNameValid, 5);
    }

    public void fillUpPassword(String password) {
        type(password, passwordField);
    }

    public void waitValidationPasswordIsComplete() {
        log.info("Waiting, when Password validation is complete");
        waitForVisibilityOf(passwordValid, 5);
    }

    public void fillUpData(String streetAddress, String city, String state, String zipCode, String phoneNumber) {
        type(streetAddress, streetAddressField);
        type(city, cityField);
        select(state, stateDropdown);
        type(zipCode, zipCodeField);
        type(phoneNumber, phoneNumberField);
    }

    public MyAccountPage pushRegisterButton() {
        log.info("Clicking on Register button");
        click(registerButton);
        return new MyAccountPage(driver, log);
    }

    public String getAccountCreationErrorMessage() {
        waitForVisibilityOf(errorMessage, 10);
        return getText(errorMessage);
    }
}
