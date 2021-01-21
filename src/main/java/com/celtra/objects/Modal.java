package com.celtra.objects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Modal extends Base {

    private String url = "http://test.celtra.com/preview/e68048a5#deviceType=Phone&overrides.deviceInfo.deviceType=Phone";

    public Modal(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        appiumDriver.get(url);
    }

    public String text() {
        By scrollContainer = By.cssSelector("div.scroll-container");
        waitElementVisible(scrollContainer, Duration.ofSeconds(5));
        return appiumDriver.findElement(scrollContainer).getText();
    }

    private WebElement celtraLogo() {
        return appiumDriver.findElement(By.cssSelector("div.celtra-picture.celtra-screen-object.celtra-view.touchable"));
    }

    public Boolean isCeltraLogoVisible() {
        return celtraLogo().isDisplayed();
    }

    public void tapLogo() {
        celtraLogo().click();
    }

    public void close() {
        appiumDriver.findElement(By.cssSelector("img.celtra-close-button.touchable.celtra-close-button-up")).click();
    }

    public Boolean isModalVisible() {
        try {
            return appiumDriver.findElement(By.cssSelector("div.celtra-screen-container")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
