package com.windstream.model;

public class Orders implements Comparable {

    private String orderNumber;
    private String orderStatus;
    private String failedScreenshotPath;

    public Orders() {
    }

    public Orders(String orderNumber, String orderStatus) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
    }

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

    public String getFailedScreenshotPath() {
        return failedScreenshotPath;
    }

    public void setFailedScreenshotPath(String failedScreenshotPath) {
        this.failedScreenshotPath = failedScreenshotPath;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders that = (Orders) o;
        return (orderNumber != null ? orderNumber.equals(that.orderNumber) : that.orderNumber == null) && (orderStatus != null ? orderStatus.equals(that.orderStatus) : that.orderStatus == null);
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object other) {
        if (getOrderNumber().equals(((Orders) other).getOrderNumber())) {
            return 0;
        } else {
            return 1;
        }
    }
}
