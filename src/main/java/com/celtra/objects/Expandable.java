package com.celtra.objects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class Expandable extends Base {

    private String url = "http://test.celtra.com/preview/b4994593#deviceType=Phone&overrides.deviceInfo.deviceType=Phone";

    public Expandable(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        appiumDriver.get(url);
    }

    public String getBannerTitle() {
        By scrollContainer = By.cssSelector("div.scroll-container");
        waitElementVisible(scrollContainer, Duration.ofSeconds(10));
        return appiumDriver.findElement(scrollContainer).getText();
    }

    public Boolean isLogoVisible() {
        return appiumDriver.findElement(By.cssSelector("div.celtra-picture.celtra-screen-object.celtra-view")).isDisplayed();
    }

    public void openModal() {
        appiumDriver.findElement(By.cssSelector("div#celtra-banner")).click();
    }

    public String modalText() {
        By modal = By.cssSelector("div.scroll-container");
        waitElementVisible(modal, Duration.ofSeconds(2));
        return appiumDriver.findElement(modal).getText();
    }

    public void tapLogo() {
        appiumDriver.findElement(By.cssSelector("div.celtra-picture.celtra-screen-object.celtra-view.touchable")).click();
    }

    public Boolean isModalLogoVisible() {
        return appiumDriver.findElement(By.cssSelector("div.celtra-picture.celtra-screen-object.celtra-view.touchable")).isDisplayed();
    }

    public void closeModal() {
        appiumDriver.findElement(By.cssSelector("img.celtra-close-button.touchable.celtra-close-button-up")).click();
    }

    public Boolean isModalVisible() {
        return appiumDriver.findElement(By.cssSelector("div.scroll-container")).isDisplayed();
    }

    public Boolean isBannerVisible() {
        By banner = By.cssSelector("div#celtra-banner");
        waitElementVisible(banner, Duration.ofSeconds(2));
        return appiumDriver.findElement(banner).isDisplayed();
    }

}
