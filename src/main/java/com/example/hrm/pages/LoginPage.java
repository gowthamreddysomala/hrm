package com.example.hrm.pages;

import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private By usernameElement = By.name("username");
    private By passwordElement = By.name("password");
    private By submitButton = By.xpath("//button[@type='submit']");

    private String title;
    private WebDriver driver;
    private PropertiesReader propertiesReader;

    // Parameterized Constructor taking the WebDriver and setting it to global variable
    // Creating the PropertiesReader object and setting its reference variable for further use.
    // Getting webpage title and setting from Properties Reader
    public LoginPage(WebDriver driver) throws NullValueException {
        propertiesReader = new PropertiesReader();
        title = propertiesReader.getProperty("login.title");
        this.driver = driver;
    }

    // method that return boolean by validating weather current title got by using driver.getTitle()
    // comparing it to the title present in the object.properties file
    public boolean validateTitle() {
        return title.equals(driver.getTitle());
    }

    // Login method is for logging in into the website
    // using webDriverWait to prevent NoSuchElementException and wait till website completely loads
    // have a EmptyValueException that throws when the object.properties file does not have value
    // and also Validates the current URL matches with the Login page URL in the object.properties
    public boolean Login() throws NullValueException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(250000));
        WebElement username = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(usernameElement));
        username.sendKeys(propertiesReader.getProperty("username"));
        driver.findElement(passwordElement).sendKeys(propertiesReader.getProperty("password"));
        driver.findElement(submitButton).click();
        webDriverWait.until(ExpectedConditions.urlToBe(driver.getCurrentUrl()));
        return (propertiesReader.getProperty("homepage.URL")).equals(driver.getCurrentUrl());
    }
}
