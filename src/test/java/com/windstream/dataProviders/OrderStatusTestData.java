package com.windstream.dataProviders;

import com.windstream.model.BrowserModel;
import com.windstream.model.LoginModel;
import com.windstream.model.OrderModel;

import java.util.List;

public class OrderStatusTestData {

    private LoginModel loginCredential;
    private OrderModel order;
    private BrowserModel browser;

    public LoginModel getLoginCredential() {
        return loginCredential;
    }

    public void setLoginCredential(LoginModel loginCredential) {
        this.loginCredential = loginCredential;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public BrowserModel getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserModel browser) {
        this.browser = browser;
    }
}
