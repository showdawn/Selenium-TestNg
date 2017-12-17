package com.windstream.pageObjects;

import com.windstream.config.Config;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePageObject {

    @FindBy(id = "txtUsername")
    private WebElement userNameTxtBox;

    @FindBy(id = "txtPassword")
    private WebElement passwordTxtBox;

    @FindBy(name = "btnLogin")
    private WebElement signInBtn;

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.elementToBeClickable(signInBtn);
    }

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    private void sendUserName(String userName) {
        userNameTxtBox.clear();
        userNameTxtBox.sendKeys(userName);
    }

    private void sendPassword(String password) {
        passwordTxtBox.clear();
        passwordTxtBox.sendKeys(password);
    }

    private void clickLogin() {
        waitForPageToLoad(getPageLoadCondition());
        signInBtn.click();
    }

    public void login(String userName, String password) {

        //Config
        getDriver().get(Config.getBaseURL());
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Config.getTimeOut(), TimeUnit.SECONDS);

        sendUserName(userName);
        sendPassword(password);

        //Screenshot
        captureScreenForPDF();

        clickLogin();
    }

}
