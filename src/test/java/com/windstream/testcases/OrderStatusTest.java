package com.windstream.testcases;

import com.windstream.config.Config;
import com.windstream.pageObjects.BasePageObject;
import com.windstream.pageObjects.LoginPage;
import com.windstream.pageObjects.MenuPage;
import com.windstream.pageObjects.orders.OrdersPage;
import com.windstream.reports.Report;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrderStatusTest extends BaseTestCase {

    private Report report = new Report();

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        super.beforeMethod();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        super.beforeMethod();
    }

    @Test
    public void testOrderStatus() {
        try {

            LoginPage loginPage = new LoginPage();
            loginPage.login("sachidananda.uat", "Sachi123!");

            MenuPage menu = new MenuPage();
            menu.navigateToOrders();

            OrdersPage ordersPage = new OrdersPage();
            ordersPage.extractStatus();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        report.createPDF("Test_Case_Screenshots", Config.getScreenShotLocation(), BasePageObject.getScreenshots());
        super.afterMethod();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        super.afterTest();
    }

}
