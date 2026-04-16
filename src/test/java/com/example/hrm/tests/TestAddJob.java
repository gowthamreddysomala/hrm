package com.example.hrm.tests;

import com.example.hrm.base.BaseClass;
import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.pages.AddJob;
import com.example.hrm.utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAddJob extends BaseClass {
    private AddJob addJob;
    private WebDriver webDriver;
    private ScreenShotUtil screenShotUtil;

    @BeforeClass
    public void init() throws NullValueException {
        this.webDriver = BaseClass.webDriver;
        addJob = new AddJob(webDriver);
        screenShotUtil = new ScreenShotUtil(webDriver);
    }
    // test to click on add and validate weather the expected site is opended or not
    // if the method returns false it logs the report and captures the screenshot and throws the exception
    @Test(dependsOnMethods = "getData")
    public void clickOnAdd() throws InterruptedException {
        try {
            addJob.clickOnAdd();
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("clickOnAdd");
            Reporter.log("Error retrieving data.. Terminating the tests....");
            throw e;
        }
    }

        // method to validate the expected webpage opended or not if not throws exception
    // if the method returns false it logs the report and captures the screenshot and throws the exception
    @Test(dependsOnMethods = "clickOnAdd")
    public void validateSite() throws NullValueException {
        try {
            Assert.assertTrue(addJob.validateJobPage());

        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("validateSite");
            Reporter.log("Failed to Add Job.. Terminating the tests....");
            throw e;
        }

    }

    // method to enter the expected data into the form
    // if the method returns false it logs the report and captures the screenshot and throws the exception
    @Test(dependsOnMethods = "validateSite")
    public void injectData() throws NullValueException, InterruptedException {
        try {
            addJob.injectUserData();
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("injectData");
            Reporter.log("Failed to Add Job.. Terminating the tests....");
            throw e;
        }


    }
}
