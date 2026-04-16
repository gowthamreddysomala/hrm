package com.example.hrm.tests;

import com.example.hrm.base.BaseClass;
import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.pages.HomePage;
import com.example.hrm.utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestHomePage extends BaseClass {
    private WebDriver webDriver;
    private HomePage homePage;
    private ScreenShotUtil screenShotUtil;

    @BeforeClass
    public void init() throws NullValueException {
        webDriver = BaseClass.webDriver;
        homePage = new HomePage(webDriver);
        screenShotUtil = new ScreenShotUtil(webDriver);
    }

    @Test
    public void validateHomepageURL() throws NullValueException {

        try {
            Assert.assertTrue(homePage.validateTitle());
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("validateHomepageURL");
            Reporter.log("Homepage not opened.. Terminating the tests....");
            throw e;
        }
        Reporter.log("Opened Homepage Successfully");
    }






}
