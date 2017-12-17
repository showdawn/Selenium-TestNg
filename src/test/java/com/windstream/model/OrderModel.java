package com.windstream.model;

import java.util.Date;

public class OrderModel implements Comparable {

    private String orderNumber;
    private String orderStatus;
    private String orderDate;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderModel that = (OrderModel) o;

        return (orderNumber != null ? orderNumber.equals(that.orderNumber) : that.orderNumber == null) && (orderStatus != null ? orderStatus.equals(that.orderStatus) : that.orderStatus == null);
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object other) {
        if (getOrderNumber().equals(((OrderModel) other).getOrderNumber())) {
            return 0;
        } else {
            return 1;
        }
    }
}
