package com.windstream.reports.pojo;


public class ExcelPojo {
    private String IterationNumber;
    private String loginPageLoadTime;
    private String wolDasboardLoadTime;
    private String makePaymentLoadTime;
    private String conformationPageLaodTime;
    private String testStatus;

    public ExcelPojo() {
        testStatus = "FAIL";
    }

    public String getIterationNumber() {
        return IterationNumber;
    }

    public void setIterationNumber(String iterationNumber) {
        IterationNumber = iterationNumber;
    }

    public String getLoginPageLoadTime() {
        return loginPageLoadTime;
    }

    public void setLoginPageLoadTime(String loginPageLoadTime) {
        this.loginPageLoadTime = loginPageLoadTime;
    }

    public String getWolDasboardLoadTime() {
        return wolDasboardLoadTime;
    }

    public void setWolDasboardLoadTime(String wolDasboardLoadTime) {
        this.wolDasboardLoadTime = wolDasboardLoadTime;
    }

    public String getMakePaymentLoadTime() {
        return makePaymentLoadTime;
    }

    public void setMakePaymentLoadTime(String makePaymentLoadTime) {
        this.makePaymentLoadTime = makePaymentLoadTime;
    }

    public String getConformationPageLaodTime() {
        return conformationPageLaodTime;
    }

    public void setConformationPageLaodTime(String conformationPageLaodTime) {
        this.conformationPageLaodTime = conformationPageLaodTime;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }
}
