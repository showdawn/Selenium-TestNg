package com.windstream.testcases.base;

import com.windstream.config.Config;
import com.windstream.pageObjects.BasePageObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BaseTestCase {

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
}
