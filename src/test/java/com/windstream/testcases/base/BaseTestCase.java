package com.windstream.testcases.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.windstream.config.Config;
import com.windstream.pageObjects.BasePageObject;
import org.testng.annotations.*;

public class BaseTestCase {

    public ExtentReports extent;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        //Extent Reporting
        // initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        // attach  HtmlReporter
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Config.getScreenShotLocation() + "\\BVSuite_Test.html");
        extent.attachReporter(htmlReporter);;
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        BasePageObject.beforeTest();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        BasePageObject.beforeMethod();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        BasePageObject.afterMethod();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        BasePageObject.afterTest();
    }

    @AfterSuite
    public void afterSuite(){
        extent.flush();
        Config.closeDriver();
        Config.quitDriver();
    }

}
