package com.windstream.pageObjects;

import com.windstream.model.OrderModel;
import com.windstream.pageObjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class OrdersPage extends BasePageObject {

    @Override
    public ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.presenceOfElementLocated(By.className("rgMasterTable"));
    }

    public OrdersPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(name = "ctl00_AccountContent_cmbOrders_Arrow")
    private WebElement orderTopFilterButton;

    @FindBy(name = "ctl00$AccountContent$RadgridOrderStatus$ctl00$ctl02$ctl02$Filter_OrderID")
    private WebElement orderNumberFilterButton;

    @FindBy(xpath = "//*[@id=\"ctl00_AccountContent_cmbOrders_DropDown\"]/div/ul/li[5]")
    private WebElement allOrdersOption;

    @FindBy(name = "ctl00$AccountContent$RadgridOrderStatus$ctl00$ctl02$ctl02$FilterTextBox_OrderID")
    private WebElement orderNumberFilterText;

    @FindBy(xpath = "//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr")
    private List<WebElement> orderRowsTable;


    @FindBy(css = "status")
    private List<WebElement> orderStatus;

    private ExpectedCondition getPageLoadConditionForOrderNumberFilter() {
        return ExpectedConditions.elementToBeClickable(orderNumberFilterButton);
    }

    public void filterByOrderNumber(String orderNumber) {
        waitForPageToLoad(getPageLoadConditionForOrderNumberFilter());
        orderNumberFilterText.clear();
        orderNumberFilterText.sendKeys(orderNumber);
        orderNumberFilterText.sendKeys(Keys.ENTER);
    }

    public void setTopOrderFilterToAllOrders() {
        //Change selection of Orders to All
        orderTopFilterButton.click();
        //Choose All Orders from Dropdown
        allOrdersOption.click();
    }

    public OrderModel readOrderDetailsFromGrid() {
        OrderModel orderModel = new OrderModel();
        try {
            orderModel.setOrderNumber(getDriver().findElement(By.xpath("//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr[1]" + "/td[2]")).getText());
            orderModel.setOrderStatus(getDriver().findElement(By.xpath("//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr[1]" + "/td[3]")).getText());
            orderModel.setOrderDate(getDriver().findElement(By.xpath("//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr[1]" + "/td[4]")).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(orderModel);
        return orderModel;
    }


    public List<OrderModel> getOrderStatuses(List<OrderModel> orders) {
        List<OrderModel> ordersList = new ArrayList<>();
        //Choose all orders
        setTopOrderFilterToAllOrders();
        //Try to find status of All order numbers listed
        for (int i = 1; i <= orders.size(); i++) {

            //Filter by Order Number
            filterByOrderNumber(orders.get(i).getOrderNumber());

            //Read Order details displayed
            ordersList.add(readOrderDetailsFromGrid());
        }
        return ordersList;
    }
}
