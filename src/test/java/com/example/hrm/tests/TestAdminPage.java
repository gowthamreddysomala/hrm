package com.example.hrm.tests;

import com.example.hrm.base.BaseClass;
import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.pages.AdminPage;
import com.example.hrm.utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAdminPage extends BaseClass {
    private WebDriver webDriver;
    private AdminPage adminPage;
    private ScreenShotUtil screenShotUtil;
    @BeforeClass
    public void init() throws NullValueException {
        webDriver = BaseClass.webDriver;
        adminPage = new AdminPage(webDriver);
        screenShotUtil = new ScreenShotUtil(webDriver);
    }

    // test to click on the admin tab in the sidebar
    // if the method returns false it logs the report and captures the screenshot and throws the exception
    @Test
    public void gotoAdmintab() throws InterruptedException {
        try {
            Assert.assertTrue(adminPage.goToAdminPage());
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("gotoAdmintab");
            Reporter.log("Admin page not opened.. Terminating the tests....");
            throw e;
        }
        Reporter.log("Clicked on Admin Tab");
    }
    // method to press on job titles in the dropdown
    // if the method returns false it logs the report and captures the screenshot and throws the exception
    @Test(dependsOnMethods = "gotoAdmintab")
    public void clickonJobTitle() throws InterruptedException, NullValueException {
        try {
            Assert.assertTrue(adminPage.gotoJob());
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("clickonJobTitle");
            Reporter.log("Admin page not opened.. Terminating the tests....");
            throw e;
        }
        Reporter.log("Clicked on Job -> Job Titles");
        Thread.sleep(2000);
    }

    //    // test to retrieve the whole data present in the table
//    // if the method returns false it logs the report and captures the screenshot and throws the exception
    @Test(dependsOnMethods = "clickonJobTitle")
    public void getData() throws InterruptedException {
        try {
            int count = adminPage.getAllJobs();
            Assert.assertNotEquals(count, 0);
            Reporter.log("got " + count + " results");
        } catch (AssertionError e) {
            screenShotUtil.captureScreenshot("getData");
            Reporter.log("Admin page not opened.. Terminating the tests....");
            throw e;
        }

    }

}
