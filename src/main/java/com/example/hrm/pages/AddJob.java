package com.example.hrm.pages;

import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.utils.PropertiesReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class AddJob {

    private By addElement = By.xpath("//button[normalize-space()='Add']");
    private  By visibilityOfUsername = By.xpath("//label[@class='oxd-label oxd-input-field-required']");
    private By jobTitle = By.xpath("//div[@class='oxd-form-row']//input[contains(@class,'oxd-input--active')]");
    private By jobDesc = By.xpath("//textarea[@placeholder='Type description here']");
    private By uploadFileName = By.xpath("//input[@type='file']");
    private By userNote = By.xpath("//textarea[@placeholder='Add note']");
    private By submitBtn = By.xpath("//button[@type='submit']");

    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    private final PropertiesReader propertiesReader;

    // used to initialize the Variables
    public AddJob(WebDriver webDriver) throws NullValueException {
        this.webDriver = webDriver;
        propertiesReader = new PropertiesReader();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(10000));
        File file = new File(propertiesReader.getProperty("jobspecificationpathdetails"));
    }

    // method to click on + Add btn in the webpage
    public void clickOnAdd() {
        WebElement addbtn = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(addElement));
        addbtn.click();
    }

    // method returns boolean by comparing url present in the object.properties file
    // by comparing the one with current URL
    public boolean validateJobPage() throws NullValueException {
        return webDriver.getCurrentUrl().equals(propertiesReader.getProperty("jobtitles.add.url"));
    }

    // using Explicit wait to wait until the username is visible
    // and sending all the keys to be able to send data
    // after filling job desc, note and after uploading file click on submit
    public void injectUserData() throws NullValueException {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(visibilityOfUsername));
        int seedWord = new Random().nextInt(1000);
        String word = propertiesReader.getProperty("jobtitle");
        word += seedWord;
        webDriver.findElement(jobTitle).sendKeys(word);
        webDriver.findElement(jobDesc).sendKeys(propertiesReader.getProperty("jobdescription"));
        File uploadFile = new File(propertiesReader.getProperty("jobspecificationpathdetails"));
        WebElement uploadElement = webDriver.findElement(uploadFileName);
        uploadElement.sendKeys(uploadFile.getAbsolutePath());
        webDriver.findElement(userNote).sendKeys(propertiesReader.getProperty("note"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }
}
