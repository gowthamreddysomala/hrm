package com.example.hrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CleanUp {
    private By dropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
    private By logoutLink = By.linkText("Logout");

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    // Parameterized Constructor taking the WebDriver and setting it to global variable
    public CleanUp(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(10000));
    }


    // method for logging out
    // the logout btn is present in the dropdown
    // using explicit wait to prevent errors and then it presses on logout btn
    public void logout() throws InterruptedException {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(dropdown)).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).click();

    }

}
