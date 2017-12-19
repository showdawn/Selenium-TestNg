package com.windstream.config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
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

    private static final String EDGE_DRIVER_PATH = "edgeDriver/MicrosoftWebDriver.exe";

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
        if (driver == null || !(driver instanceof ChromeDriver)) {
            driver = null;
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("disable-notifications");
            chromeOptions.addArguments("process-per-site");
            chromeOptions.addArguments("dns-prefetch-disable");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            File chromeDriver = new File(ClassLoader.getSystemResource(CHROME_DRIVER_PATH).getPath());
            //System.setProperty("webdriver.chrome.driver", chromeDriver.getPath());
            System.setProperty("webdriver.chrome.driver", "C:\\Sachi\\Broadview\\Prop\\Test_Framework\\src\\test\\resources\\chromeDriver\\chromedriver.exe");
            driver = new ChromeDriver(caps);
        }
        return driver;
    }


    private static WebDriver getFirefoxDriver() {
        if (driver == null || !(driver instanceof FirefoxDriver)) {
            driver = null;
            File firefoxDriver = new File(ClassLoader.getSystemResource(FIREFOX_DRIVER_PATH).getPath());
            //System.setProperty("webdriver.gecko.driver", firefoxDriver.getPath());
            System.setProperty("webdriver.chrome.driver", "C:\\Sachi\\Broadview\\Prop\\Test_Framework\\src\\test\\resources\\firefoxDriver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }


    private static WebDriver getIEDriver() {
        if (driver == null || !(driver instanceof InternetExplorerDriver)) {
            driver = null;
            File ieDriver = new File(ClassLoader.getSystemResource(IE_DRIVER_PATH).getPath());
            //System.setProperty("webdriver.ie.driver", ieDriver.getPath());
            System.setProperty("webdriver.ie.driver", "C:\\Sachi\\Broadview\\Prop\\Test_Framework\\src\\test\\resources\\ieDriver\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

    private static WebDriver getEdgeDriver() {
        if (driver == null || !(driver instanceof EdgeDriver)) {
            driver = null;
            File edgeDriver = new File(ClassLoader.getSystemResource(EDGE_DRIVER_PATH).getPath());
            //System.setProperty("webdriver.edge.driver", edgeDriver.getPath());
            System.setProperty("webdriver.edge.driver", "C:\\Sachi\\Broadview\\Prop\\Test_Framework\\src\\test\\resources\\edgeDriver\\MicrosoftWebDriver.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.edge();
            driver = new EdgeDriver(capabilities);
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
        } else if ("edge".equals(browser)) {
            return getEdgeDriver();
        } else {
            return null;
        }
    }


    public static void closeDriver() {
        if(driver != null){
            try {
                driver.close();
            } catch (Exception e) {
                //Swallow Exception
            }
        }
    }


    public static void quitDriver() {
        try {
            if(driver != null){
                driver.quit();
            }
        } catch (Exception e) {
            //Swallow Exception
        }finally {
            driver = null;
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
        return getProp("screenShotsLocation");
    }


    public static String getTestReportLocation() {
        return getProp("excelExportLocation");
    }

    public static String getExtentReportLocation() {
        return getProp("extentReportLocation");
    }

    public static String getInputExcelPath() {
        return getProp("inputExcelPath");
    }
}
