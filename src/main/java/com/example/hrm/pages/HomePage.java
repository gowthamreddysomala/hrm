package com.example.hrm.pages;

import com.example.hrm.exceptions.NullValueException;
import com.example.hrm.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver webDriver;
    private PropertiesReader propertiesReader;

    // setting the Webdriver to global driver variable
    public HomePage(WebDriver driver) {
        this.webDriver = driver;
        propertiesReader = new PropertiesReader();
    }

    // this is to Validate the homepage title to the title present in the object.properties
    // return the boolean if the expected title matches.
    public boolean validateTitle() throws NullValueException {
//        Thread.sleep(10000);
        System.out.println("validateTitle");
        String url = webDriver.getCurrentUrl();
        return url.contains(propertiesReader.getProperty("homepage.heading"));
    }


}
