package com.amazon.AmazonUI.factory;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.amazon.AmazonUI.Utils.Browser;

public class WebDriverFactory {

    private static final String WEB_DRIVER_FOLDER = "webdrivers";

    public static WebDriver createWebDriver(DesiredCapabilities capabilities) {
        Browser browser = Browser.fromString(capabilities.getCapability("browser").toString());
        String driverFileName = browser.getName() + "driver";
        String driverFilePath = driversFolder(new File("webdrivers").getAbsolutePath());
        System.setProperty("webdriver." + browser.getName() + ".driver", driverFilePath + driverFileName);
        return browser.getDriver();
    }

    private static String driversFolder(String path) {
        File file = new File(path);
        for (String item : file.list()) {
            if (WEB_DRIVER_FOLDER.equals(item)) {
                return file.getAbsolutePath() + "/" + WEB_DRIVER_FOLDER + "/";
            }
        }
        return driversFolder(file.getParent());
    }
}