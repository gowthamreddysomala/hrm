package com.example.hrm.tests;

import com.example.hrm.base.BaseClass;
import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.pages.LoginPage;
import com.example.hrm.utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLoginPage extends BaseClass {
    private WebDriver webDriver;
    private LoginPage loginPage;
    private ScreenShotUtil screenShotUtil;

    @BeforeClass
    public void init() throws NullValueException {
        webDriver = BaseClass.webDriver;
        loginPage = new LoginPage(webDriver);
    }

    @Test(groups = "login")
    public void validateTitle() {
        try {
           Assert.assertTrue(loginPage.validateTitle());
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("Invalid_Title");
            Reporter.log("invalid Title... Terminating tests");
            throw e;
        }
        Reporter.log("Valid Title");
    }

    // test to log in to the webpage
    // if the method returns false it logs the report and captures the screenshot and throws the exception
    @Test(dependsOnMethods = "validateTitle",groups = "login")
    public void Login() throws NullValueException {
        try {
            Assert.assertTrue(loginPage.Login());
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("Login_Failed");
            Reporter.log("Login failed... Terminating tests");
            throw e;
        }
        Reporter.log("Login Successful");
    }


}
