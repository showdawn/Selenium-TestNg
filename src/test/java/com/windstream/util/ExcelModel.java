package com.windstream.util;

import com.windstream.model.BrowserModel;
import com.windstream.model.LoginModel;
import com.windstream.model.OrderModel;

import java.util.List;

public class ExcelModel {

    private BrowserModel browser;
    private List<LoginModel> loginCredentials;
    private List<OrderModel> orders;

    public BrowserModel getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserModel browser) {
        this.browser = browser;
    }

    public List<LoginModel> getLoginCredentials() {
        return loginCredentials;
    }

    public void setLoginCredentials(List<LoginModel> loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
