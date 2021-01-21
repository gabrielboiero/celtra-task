package com.celtra;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Main {

    @Getter
    protected AppiumDriver appiumDriver;

    private String platform;
    private DesiredCapabilities desiredCapabilities;
    private DeviceController deviceController;

    public Main() {
        // System parameters from command line using the option '-D' and default values were chosen according to most used platform and browser
        platform = System.getProperty("platform", "android");
        String browser = System.getProperty("browser", "Chrome");
        boolean clearCache = System.getProperty("clearCache", "false") == "true";
        String chromeDriverPathToExec = System.getProperty("chromeDriver", "");

        // Initialization
        deviceController = DeviceController.valueOf(platform.toUpperCase());
        desiredCapabilities = deviceController.getDesiredCapabilities(browser);
        URL url = deviceController.getAppiumUrl("http://127.0.0.1", 4723);
        appiumDriver = new AppiumDriver(url, desiredCapabilities);

        if (clearCache) {
            appiumDriver.manage().deleteAllCookies();
            /*
            Note: Instead of deleting all cookies, it is possible to use per cookie: appiumDriver.manage().deleteCookieNamed("...any Celtra cookie...");
            But since for the scope of this task, there is no need to identify all required cookies and delete.
             */
        }

        if (!chromeDriverPathToExec.isEmpty()) {
            desiredCapabilities.setCapability("chromedriverExecutable", chromeDriverPathToExec);
        }
    }

}
