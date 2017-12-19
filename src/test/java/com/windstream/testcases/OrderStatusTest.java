package com.windstream.testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.windstream.config.Config;
import com.windstream.dataProviders.OrderStatusDataProvider;
import com.windstream.model.Orders;
import com.windstream.pageObjects.BasePageObject;
import com.windstream.pageObjects.LoginPage;
import com.windstream.pageObjects.MenuPage;
import com.windstream.pageObjects.OrdersPage;
import com.windstream.reports.Report;
import com.windstream.testcases.base.BaseTestCase;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrderStatusTest extends BaseTestCase {

    private Report report = new Report();

    @BeforeSuite
    public void beforeSuite() {
        super.beforeSuite();
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        super.beforeTest();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        super.beforeMethod();
    }

    @Test(dataProvider = "orderStatusDataProvider", dataProviderClass = OrderStatusDataProvider.class)
    public void testOrderStatus(String username, String password, String orderNumber, String orderStatus) {
        ExtentTest extentTest = extent.createTest("Order Status Test - " + orderNumber, "Validate Status of Order")
                .pass("Order Number : " + orderNumber + "  Order Status: " + orderStatus);
        try {
            extentTest.assignCategory("Orders");
            //Login
            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);
            //Navigate to Orders Page
            MenuPage menu = new MenuPage();
            menu.navigateToOrders();
            //Filter Orders
            OrdersPage ordersPage = new OrdersPage();
            Orders orderDataFromBrowser = ordersPage.getOrderDetailsFromPage(orderNumber);

            Orders orderDataFromDataSource = new Orders(orderNumber, orderStatus);
            System.out.println("From Browser " + orderDataFromBrowser);
            System.out.println("From Excel " + orderDataFromDataSource);
            //Assertions
            if (!orderDataFromBrowser.equals(orderDataFromDataSource)) {
                extentTest.fail("Failed for Order No: " + orderNumber + "\n Expected Status: " + orderStatus + " \n Received Status: " + orderDataFromBrowser.getOrderStatus(),
                        MediaEntityBuilder.createScreenCaptureFromPath(orderDataFromBrowser.getFailedScreenshotPath()).build());
                Assert.fail();
            }
            extentTest.pass("Pass for Order No: " + orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
            extentTest.fail("Test Failed due to error" + e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        report.createPDF("Orders_Test_Case", Config.getScreenShotLocation(), BasePageObject.getScreenshots());
        super.afterMethod();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        super.afterTest();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        super.afterSuite();
    }

}
