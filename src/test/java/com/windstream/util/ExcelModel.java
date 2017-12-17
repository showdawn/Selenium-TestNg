package com.windstream.util;

import com.windstream.model.BrowserModel;
import com.windstream.model.LoginModel;
import com.windstream.model.OrderModel;

import java.util.List;

public class ExcelModel {

    private List<LoginModel> loginCredentials;
    private List<OrderModel> orders;
    private List<BrowserModel> browsersList;

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

    public List<BrowserModel> getBrowsersList() {
        return browsersList;
    }

    public void setBrowsersList(List<BrowserModel> browsersList) {
        this.browsersList = browsersList;
    }
}
