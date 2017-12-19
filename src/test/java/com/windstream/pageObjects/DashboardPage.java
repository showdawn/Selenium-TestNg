package com.windstream.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    @FindBy(xpath = ".//*[@id='AccountContent_CustomizeDashboardButton']")
    private WebElement button_CustomizeDashboard;

    @FindBy(xpath = ".//*[@id='customizeWidgets_AvailableWidgetsListBox']/table/tbody/tr/td/a[2]")
    private WebElement lstbx_Customwidgets_arrow1;

    @FindBy(xpath = ".//*[@id='customizeWidgets_AvailableWidgetsListBox']/table/tbody/tr/td/a[1]")
    private WebElement lstbx_Customwidgets_arrow2;

    @FindBy(id = "customizeWidgets_CustomizeDashboardSaveClose")
    private WebElement savebutton_customizeWidgets;

}
