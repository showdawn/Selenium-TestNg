package com.windstream.model;

public class BrowserModel {

    private String browserName;
    private String os;
    private String browserWidth;
    private String browserHeight;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowserWidth() {
        return browserWidth;
    }

    public void setBrowserWidth(String browserWidth) {
        this.browserWidth = browserWidth;
    }

    public String getBrowserHeight() {
        return browserHeight;
    }

    public void setBrowserHeight(String browserHeight) {
        this.browserHeight = browserHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrowserModel that = (BrowserModel) o;

        return browserName.equals(that.browserName) && (os != null ? os.equals(that.os) : that.os == null);
    }

    @Override
    public int hashCode() {
        int result = browserName.hashCode();
        result = 31 * result + (os != null ? os.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BrowserModel{" +
                "browserName='" + browserName + '\'' +
                ", os='" + os + '\'' +
                ", browserWidth='" + browserWidth + '\'' +
                ", browserHeight='" + browserHeight + '\'' +
                '}';
    }
}
