package com.windstream.testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.windstream.testcases.base.BaseTestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomizeDashboardTest  extends BaseTestCase {

    @Test
    public void customizeboard_hidewidgets() throws InterruptedException {

/*        ExtentTest test  = extent.createTest("Customize DashBoard by Removing widgets");
        test.log(Status.INFO, "CustomizeBoard_Remove Widgets" + " Scenario Started");

        customiseDashboard.button_CustomizeDashboard(driver).click();
        test.log(LogStatus.INFO, "Customize dashboard link clicked");
        Thread.sleep(5000);
        driver.switchTo().frame(0);
        List<WebElement> options = driver.findElements(By.xpath(".//*[@id='customizeWidgets_DashboardListBox']/div/ul/li"));
        for (WebElement option : options) {
            String sValue = option.getText();
            if (sValue.equalsIgnoreCase("My Call Groups")) {
                option.click();
            }
        }
        Thread.sleep(2000);
        customiseDashboard.lstbx_Customwidgets_arrow1(driver).click();
        test.log(LogStatus.INFO, "Widgets selected for hiding");
        Thread.sleep(1000);
        customiseDashboard.savebutton_customizeWidgets(driver).click();
        test.log(LogStatus.INFO, "MyCallgroups widget deleted successfully");
        Thread.sleep(8000);

        try {
            driver.findElement(By.xpath(".//*[@id='ctl00_AccountContent_WidgetsListView_UserControl3_btnChangeSettingCallGroup']"));
        } catch (NoSuchElementException e) {
            System.out.println("Testcase passed");

        }

        test.log(LogStatus.INFO, "MyCallgroups widget not displayed in the dashboard");
        Thread.sleep(3000);*/
    }

}
