package com.windstream.pageObjects;

import com.windstream.config.Config;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class LoginPageObject extends BasePageObject{

    @FindBy(id = "username")
    private WebElement userNameTxtBox;

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(css = "input.SignInButtonNew")
    private WebElement signInBtn;

    public LoginPageObject() {
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.elementToBeClickable(signInBtn);
    }

    public void login(String userName, String password){
        getDriver().get(Config.getBaseURL());
        double startTime  = System.currentTimeMillis();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Config.getTimeOut(), TimeUnit.SECONDS);
        //Screenshot
        captureScreenForPDF();
        waitForPageToLoad(getPageLoadCondition());
        double finish = System.currentTimeMillis();

        getExcelPojo().setLoginPageLoadTime(String.valueOf((finish-startTime)/1000));

        userNameTxtBox.clear();
        userNameTxtBox.sendKeys(userName);
        passwordTxtBox.clear();
        passwordTxtBox.sendKeys(password);
        //Screenshot
        captureScreenForPDF();
        signInBtn.click();
    }
}
