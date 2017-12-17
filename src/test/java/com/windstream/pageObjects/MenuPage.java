package com.windstream.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuPage extends BasePageObject {

    @FindBy(id = "orderStatus")
    private WebElement ordersLink;

    public MenuPage() {
        PageFactory.initElements(getDriver(), this);
    }

    private ExpectedCondition getPageLoadConditionForOrders() {
        return ExpectedConditions.presenceOfElementLocated(By.className("rgMasterTable"));
    }

    public void navigateToOrders() {
        ordersLink.click();
        waitForPageToLoad(getPageLoadConditionForOrders());
        captureScreenForPDF();
    }

}
