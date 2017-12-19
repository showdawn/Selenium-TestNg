package com.windstream.pageObjects;

import com.windstream.model.Orders;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrdersPage extends BasePageObject {

    @FindBy(name = "ctl00$AccountContent$cmbOrders")
    private WebElement orderTopFilterButton;

    @FindBy(name = "ctl00$AccountContent$RadgridOrderStatus$ctl00$ctl02$ctl02$Filter_OrderID")
    private WebElement orderNumberFilterButton;

    @FindBy(xpath = "//*[@id=\"ctl00_AccountContent_cmbOrders_DropDown\"]/div/ul/li[5]")
    private WebElement allOrdersOption;

    @FindBy(name = "ctl00$AccountContent$RadgridOrderStatus$ctl00$ctl02$ctl02$FilterTextBox_OrderID")
    private WebElement orderNumberFilterText;

    @FindBy(xpath = "//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]")
    private WebElement orderRowsTableHeader;

    @FindBy(xpath = "//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr")
    private List<WebElement> orderRowsTable;

    @FindBy(css = "status")
    private List<WebElement> orderStatus;

    public OrdersPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    public ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.presenceOfElementLocated(By.className("rgMasterTable"));
    }

    private ExpectedCondition getPageLoadConditionForOrderNumberFilter() {
        return ExpectedConditions.elementToBeClickable(orderNumberFilterText);
    }

    private ExpectedCondition getPageLoadConditionForOrderDetails() {
        return ExpectedConditions.elementToBeClickable(orderRowsTableHeader);
    }

    private void setTopOrderFilterToAllOrders() {
        //Change selection of Orders to All
        orderTopFilterButton.click();
        //Capture Screen
        captureScreenForPDF();
        //Choose All Orders from Dropdown
        delay(1000);
        allOrdersOption.click();
        captureScreenForPDF();
    }

    private void filterByOrderNumber(String orderNumber) {
        waitForPageToLoad(getPageLoadConditionForOrderNumberFilter());
        orderNumberFilterText.clear();
        delay(1000);
        orderNumberFilterText.sendKeys(orderNumber);
        delay(1000);
        orderNumberFilterText.sendKeys(Keys.ENTER);
        delay(1000);
        waitForPageToLoad(getPageLoadConditionForOrderDetails());
    }

    private Orders readOrderDetailsFromGrid() {
        Orders orders = new Orders();
        try {
            delay(1000);
            orders.setFailedScreenshotPath(captureScreenForPDF());
            orders.setOrderNumber(getDriver().findElement(By.xpath("//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr[1]" + "/td[2]")).getText());
            orders.setOrderStatus(getDriver().findElement(By.xpath("//*[@id=\"ctl00_AccountContent_RadgridOrderStatus_ctl00\"]/tbody/tr[1]" + "/td[3]")).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Orders getOrderDetailsFromPage(String orderNumber) {
        //Choose all orders
        setTopOrderFilterToAllOrders();
        //Filter by Order Number
        filterByOrderNumber(orderNumber);
        return readOrderDetailsFromGrid();
    }


    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
