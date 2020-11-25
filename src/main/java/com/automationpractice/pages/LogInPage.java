package com.automationpractice.pages;

import com.automationpractice.base.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePageObject<LogInPage> {
    private static final String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account/";
    private  By createAccountForm = By.cssSelector("#create-account_form");
    private By loginPageName = By.cssSelector("#center_column .page-heading");
    private By emailField = By.cssSelector("#email_create");
    private By createAnAccountButton = By.cssSelector("#SubmitCreate");
    private By accountCreationPageName = By.cssSelector("#noSlide .page-heading");
    private By errorMessage = By.cssSelector("#create_account_error");


    public LogInPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void waitForLogInPageToLoad() {
        log.info("Waiting for LogIn page to load");
        waitForVisibilityOf(createAccountForm);
    }

    public boolean isCorrectLogInPageLoaded(String correctLogInPageName) {
        if (getText(loginPageName).equals(correctLogInPageName)) {
            return true;
        }
        return false;
    }

    public void openLogInPage() {
        getPage(URL);
    }

    public void fillUpEmail(String email) {
        type(email, emailField);
    }

    public AccountCreationPage pushCreateAnAccountButton() {
        log.info("Clicking on Create an account button");
        click(createAnAccountButton);
        return new AccountCreationPage(driver, log);
    }

    public String getLogInErrorMessage() {
        waitForVisibilityOf(errorMessage, 10);
        return getText(errorMessage);
    }
}

