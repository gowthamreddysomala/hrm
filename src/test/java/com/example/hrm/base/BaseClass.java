package com.example.hrm.base;

import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.exceptions.InvalidBrowserName;
import com.example.hrm.utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.example.hrm.utils.DriverManager;

import java.io.IOException;


public class BaseClass {
    protected static WebDriver webDriver;
    private DriverManager driverManager;
    private ScreenShotUtil screenShotUtil;

    @BeforeSuite
    public void initialize() throws InvalidBrowserName, NullValueException {
        driverManager = new DriverManager();
        webDriver = driverManager.getWebDriver();
        screenShotUtil = new ScreenShotUtil(webDriver);
    }

    @AfterSuite
    public void dismantle() throws IOException {
        try{
        driverManager.killDriver();
        Reporter.log("Terminated Instance / Killed the Driver");
    } catch(AssertionError e)
    {
        screenShotUtil.captureScreenshot("");
        throw e;
    }
}
}
