package com.automationpractice.pages;

import com.automationpractice.base.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePageObject<MainPage> {
    private static final String URL = "http://automationpractice.com/";
    private By signInLink = By.cssSelector(".login");

    public MainPage(WebDriver driver, Logger log) {

        super(driver, log);
    }

    public void openMainPage() {
        getPage(URL);
    }

    public LogInPage pushSignInButton() {
        log.info("Clicking on Sign in Button");
        click(signInLink);
        return new LogInPage(driver, log);
    }
}
