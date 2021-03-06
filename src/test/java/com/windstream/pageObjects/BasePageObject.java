package com.windstream.pageObjects;

import com.windstream.config.Config;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePageObject {

    private static List<Object> screenshots;
    private static WebDriver driver;

    public static void beforeTest() {
    }

    public static void beforeMethod() {
        driver = Config.getDriver();
        screenshots = new ArrayList<>();
    }

    public static void afterMethod() {
        Config.closeDriver();
        Config.quitDriver();
        screenshots = null;

    }

    public static void afterTest() {
    }


    static WebDriver getDriver() {
        return Config.getDriver();
    }

    public static List<Object> getScreenshots() {
        return screenshots;
    }

    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }

    void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
        waitForPageToLoad(10L, pageLoadCondition);
    }

    private void waitForPageToLoad(Long refreshTime, ExpectedCondition pageLoadCondition) {
        Wait wait = new FluentWait(Config.getDriver())
                .withTimeout(Config.getTimeOut(), TimeUnit.SECONDS)
                .pollingEvery(refreshTime, TimeUnit.MILLISECONDS);
        wait.until(pageLoadCondition);
    }

    void closeAlertAndGetItsText(WebDriver driver) throws Exception {
        String alertText = null;
        try {
            Alert alert = driver.switchTo().alert();
            alertText = alert.getText();
            alert.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String captureScreenForPDF() {
        screenshots.add(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = Config.getScreenShotLocation() + "\\failed.png";
        try {
            FileUtils.copyFile(file, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}
