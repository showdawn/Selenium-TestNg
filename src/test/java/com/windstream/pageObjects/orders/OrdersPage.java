package com.windstream.pageObjects.orders;

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

    @FindBy(name = "ctl00$AccountContent$cmbOrders")
    private WebElement orderTopFilter;

    @FindBy(name = "ctl00$AccountContent$RadgridOrderStatus$ctl00$ctl02$ctl02$Filter_OrderID")
    private WebElement orderNumberFilterButton;

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

    public List<OrderModel> extractStatus() {
        List<OrderModel> ordersList = new ArrayList<>();
        //Iterate on Number of ROws
        for (int i = 1; i <= orderRowsTable.size(); i++) {
            OrderModel orderModel = new OrderModel();
            orderModel.setOrderNumber(getDriver().findElement(By.xpath("//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr[" + i + "]" + "/td[2]")).getText());
            orderModel.setOrderStatus(getDriver().findElement(By.xpath("//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr[" + i + "]" + "/td[3]")).getText());
            orderModel.setOrderDate(getDriver().findElement(By.xpath("//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr[" + i + "]" + "/td[4]")).getText());
            System.out.println(orderModel);
            ordersList.add(orderModel);
        }
        return ordersList;
    }
}
