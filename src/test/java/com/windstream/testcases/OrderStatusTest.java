package com.windstream.testcases;

import com.windstream.config.Config;
import com.windstream.dataProviders.ExcelDataProvider;
import com.windstream.model.OrderModel;
import com.windstream.pageObjects.BasePageObject;
import com.windstream.pageObjects.LoginPage;
import com.windstream.pageObjects.MenuPage;
import com.windstream.pageObjects.OrdersPage;
import com.windstream.reports.Report;
import com.windstream.testcases.base.BaseTestCase;
import com.windstream.util.ExcelModel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

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

    @Test(dataProvider = "orderStatusDataProvider", dataProviderClass = ExcelDataProvider.class)
    public void testOrderStatus(ExcelModel excelModel) {
        try {

            LoginPage loginPage = new LoginPage();
            loginPage.login(excelModel.getLoginCredentials().get(0).getUsername(), excelModel.getLoginCredentials().get(0).getPassword());

            MenuPage menu = new MenuPage();
            menu.navigateToOrders();

            OrdersPage ordersPage = new OrdersPage();
            List<OrderModel> orderDataFromBrowser =  ordersPage.getOrderStatuses(excelModel.getOrders());

            for()

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
