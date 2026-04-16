package com.example.hrm.utils;

import com.example.hrm.exceptions.NullValueException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class ScreenShotUtil {
    private PropertiesReader propertiesReader;
    private String path;
    private File srcPath;
    private TakesScreenshot takesScreenshot;
    private WebDriver webDriver;

    // used to initialize all the variables
    public ScreenShotUtil(WebDriver webDriver) throws NullValueException {
        this.webDriver = webDriver;
        propertiesReader = new PropertiesReader();
        path = propertiesReader.getProperty("screenshots.path");
        takesScreenshot = (TakesScreenshot) webDriver;
    }

    // methods return string of the current time and date
    // method uses Instant.now() method to get current time and replace the unusable file name chars
    // and removes the millisecond seperated by . .
    // and returns the usable datetime string
    public String getCurrentTimestamp(){
        String timeStamp = Instant.now().toString();
        String[] arr = timeStamp.split("\\.");
        return arr[0].replace(":","").replace("-","")
                .replace("T","_");
    }

    // method used to capture the screenshot
    // it waits explicitly to prevent blank white screens
    // and saves the captured screenshot in the screenshot directory
    // screenshot name is a combination of filename and timestamp
    public void captureScreenshot( String filename){
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            Thread.sleep(1000);
        String fileName = System.getProperty("user.dir")+propertiesReader.getProperty("screenshots.path")+filename+"_"+getCurrentTimestamp()+".png";
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        System.out.println(fileName);
        FileUtils.copyFile(src,new File(fileName));}
        catch (IOException e){
        }catch (NullValueException nullValueException){
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
