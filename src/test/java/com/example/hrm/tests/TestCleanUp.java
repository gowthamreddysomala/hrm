package com.example.hrm.tests;

import com.example.hrm.base.BaseClass;
import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.pages.CleanUp;
import com.example.hrm.utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCleanUp extends BaseClass {

    private WebDriver webDriver;
    private ScreenShotUtil screenShotUtil;
    private CleanUp cleanUp;

    @BeforeClass
    public void init() throws NullValueException {
        webDriver = BaseClass.webDriver;
        screenShotUtil = new ScreenShotUtil(webDriver);
        cleanUp = new CleanUp(webDriver);
    }
    // method to logout of the site after successful creation of the user
    // if the method returns false it logs the report and captures the screenshot and throws the exception
    @Test(dependsOnMethods = "injectData")
    public void logout() throws InterruptedException {
        try {
            cleanUp.logout();
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("logout");
            Reporter.log("Logout Failed..");
            throw e;
        }

    }
}
