package com.example.hrm.utils;

import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.exceptions.InvalidBrowserName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Scanner;

public class DriverManager {
    private WebDriver webDriver;
    private PropertiesReader propertiesReader;
    private static Scanner scanner;


    // initializing the browser using browser property from object.properties file
    // deleting and clearing all the cookies and maximizing the window.
    // getting URL property from object.properties and setting it to driver.get
    public DriverManager() throws InvalidBrowserName, NullValueException {
        propertiesReader = new PropertiesReader();
        String browser = propertiesReader.getProperty("browser");
        //String browser = browserSelection();
        if (browser.equalsIgnoreCase("chrome")) webDriver = new ChromeDriver();
        else if (browser.equalsIgnoreCase("edge")) webDriver = new EdgeDriver();
        else if (browser.equalsIgnoreCase("firefox")) webDriver = new FirefoxDriver();
        else {
            throw new InvalidBrowserName();
        }
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.get(propertiesReader.getProperty("URL"));
    }

    // returning the webdriver after calling constructor
    public WebDriver getWebDriver() {
        return webDriver;
    }

    // clearing the browser cookies and
    // terminating the browser instance and closing the FileInputStream
    public void killDriver() throws IOException {
        webDriver.manage().deleteAllCookies();
        webDriver.quit();
        propertiesReader.killFileInputStream();
    }
}
