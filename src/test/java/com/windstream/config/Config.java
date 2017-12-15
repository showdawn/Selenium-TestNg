package com.windstream.config;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;


public class Config {

    private static final String LOCAL_CONFIG_FILE_NAME = "app.properties";

    private static final String CHROME_DRIVER_PATH = "chromeDriver/chromedriver.exe";

    private static final String FIREFOX_DRIVER_PATH = "firefoxDriver/geckodriver.exe";

    private static final String IE_DRIVER_PATH = "ieDriver/IEDriverServer.exe";

    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());

    private static Properties prop = new Properties();

    private static WebDriver driver;


    private static String getProp(String key) {
        String value = null;
        try {
            prop.load(new FileInputStream("src/test/resources/" + LOCAL_CONFIG_FILE_NAME));
            value = prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }


    private static WebDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.addArguments("process-per-site");
        chromeOptions.addArguments("dns-prefetch-disable");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        File chromeDriver = new File(ClassLoader.getSystemResource(CHROME_DRIVER_PATH).getPath());
        System.setProperty("webdriver.chrome.driver", chromeDriver.getPath());
        if (driver == null || !(driver instanceof ChromeDriver)) {
            driver = new ChromeDriver(caps);
        }
        return driver;
    }


    private static WebDriver getFirefoxDriver() {
        File firefoxDriver = new File(ClassLoader.getSystemResource(FIREFOX_DRIVER_PATH).getPath());
        System.setProperty("webdriver.gecko.driver", firefoxDriver.getPath());
        if (driver == null || !(driver instanceof FirefoxDriver)) {
            driver = new FirefoxDriver();
        }
        return driver;
    }


    private static WebDriver getIEDriver() {
        File ieDriver = new File(ClassLoader.getSystemResource(IE_DRIVER_PATH).getPath());
        System.setProperty("webdriver.ie.driver", ieDriver.getPath());
        if (driver == null || !(driver instanceof InternetExplorerDriver)) {
            driver = new InternetExplorerDriver();
        }
        return driver;
    }


    public static WebDriver getDriver() {
        String browser = getBrowser();
        if ("chrome".equals(browser)) {
            return getChromeDriver();
        } else if ("firefox".equals(browser)) {
            return getFirefoxDriver();
        } else if ("ie".equals(browser)) {
            return getIEDriver();
        } else {
            return null;
        }
    }


    public static void closeDriver() {
        driver.close();
    }


    public static void quitDriver() {
        try {
            driver.quit();
            driver = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return null if window is supposed to be maximised, Dimension if any other size is demanded
     */
    public static Dimension getBrowserSize() {
        String size = getProp("browserSize");
        if (StringUtils.isNotBlank(size) || "maximised".equals(size) || size.split("x").length == 2) {
            if ("maximised".equals(size)) {
                return null;
            } else {
                return new Dimension(Integer.valueOf(size.split("x")[0]), Integer.valueOf(size.split("x")[1]));
            }
        } else {
            throw new WebDriverException("browser size: " + size + " is not a proper value");
        }
    }


    public static String getBrowser() {
        return getProp("browser");
    }


    public static String getBaseURL() {
        return getProp("baseURL");
    }


    public static Long getTimeOut() {
        return Long.valueOf(getProp("timeout"));
    }


    public static String getScreenShotLocation() {
        return getProp("failedScreenShotLocation");
    }


    public static String getTestReportLocation() {
        return getProp("excelReportLocation");
    }
}
