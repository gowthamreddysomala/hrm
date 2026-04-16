package com.example.hrm.pages;

import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class AdminPage {

    By adminBtn = By.linkText("Admin");
    By jobDropDown = By.xpath("//span[contains(text(), 'Job')]");
    By dropDownElement = By.linkText("Job Titles");
    By listOfJobs = By.xpath("//div[@class='orangehrm-container']/div/div/div");

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;
    private PropertiesReader propertiesReader;

    // Parameterized Constructor taking the WebDriver and setting it to global variable
    // setting webdriver wait until the whole elements are loaded threashold is 20s
    public AdminPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(20000));
        propertiesReader = new PropertiesReader();
    }

    // waiting until the admin btn is visible
    // after visibility is true then it clicks it and returns true;
    public boolean goToAdminPage() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(adminBtn)).click();
        return true;
    }

    // btn to click on job titles in the dropdown menu on job drop down
    // using webdriver wait to be able to prevent from no such element exception
    // validating by comparing the URL present in object.prop file to current URL
    public boolean gotoJob() throws NullValueException {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(jobDropDown)).click();
        WebElement jobTitles = webDriverWait.until(ExpectedConditions.elementToBeClickable(dropDownElement));
        jobTitles.click();
        String actTitle = webDriver.getCurrentUrl();
       return actTitle.equals(propertiesReader.getProperty("adminpage.jobtitles"));
    }

    // get all the job present in the current page and print them in console
    // using lambda expressions to print them to console
    // returning the size of the list available
    public int getAllJobs() throws InterruptedException {
        List<WebElement> ls = webDriver.findElements(listOfJobs);
        ls.stream().forEach(e -> System.out.println(e.getText()));
        return ls.size();
    }


}
