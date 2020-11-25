package com.automationpractice.pages;

import com.automationpractice.base.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePageObject<MyAccountPage> {
    private By registrationComplete = By.cssSelector("#center_column .info-account");
    private By myAccountPageName = By.cssSelector("#center_column .page-heading");

    public MyAccountPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void waitRegistrationIsComplete() {
        log.info("Waiting, when registration is complete");
        waitForVisibilityOf(registrationComplete, 10);
    }

    public boolean isCorrectMyAccountPageLoaded(String correctMyAccountPageName) {
        if (getText(myAccountPageName).equals(correctMyAccountPageName)) {
            return true;
        }
        return false;
    }
}
