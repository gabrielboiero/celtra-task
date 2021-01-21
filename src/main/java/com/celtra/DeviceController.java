package com.celtra;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public enum DeviceController {

    ANDROID {
        public DesiredCapabilities getDesiredCapabilities(String browser) {
            // Basic Android capabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);

            // Timeouts modified to allow proper debugging
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, "280000");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "60000");
            desiredCapabilities.setCapability("newCommandTimeout", "280000");
            return desiredCapabilities;
        }
    },

    IOS {
        public DesiredCapabilities getDesiredCapabilities(String browser) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
            return desiredCapabilities;
        }
    },

    WEB {
        public DesiredCapabilities getDesiredCapabilities(String browser) {
            // For possible future use
            return null;
        }
    };

    public abstract DesiredCapabilities getDesiredCapabilities(String browser);

    protected URL getAppiumUrl(String address, Integer port) {
        try {
            return new URL(address + ":" + port + "/wd/hub");
        } catch (MalformedURLException e) {
            throw new IllegalStateException(String.format("Appium driver was not created for current platform"));
        }
    }

}