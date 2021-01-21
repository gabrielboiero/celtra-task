package com.celtra.objects;

import com.celtra.utils.RetryerUtils;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Base {

    protected AppiumDriver appiumDriver;

    protected void waitElementVisible(By element, Duration duration) {
        Retryer retryer = RetryerBuilder.newBuilder()
                .retryIfException()
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterDelay(duration.getSeconds(), TimeUnit.SECONDS))
                .build();
        Callable task = () -> elementVisible(element);
        new RetryerUtils().retry(retryer, task);
    }

    private Boolean elementVisible(By element) {
        if (!appiumDriver.findElement(element).isDisplayed()) {
            throw new IllegalStateException("Element not visible: " + element.toString());
        }
        return true;
    }

    public void mainView() {
        // Switch from NATIVE to WEBVIEW context
        Set<String> contexts = appiumDriver.getContextHandles();
        appiumDriver.context((String) contexts.toArray()[1]);
    }

    public void switchTo(int iframeNum) {
        appiumDriver.switchTo().defaultContent();
        appiumDriver.switchTo().frame(iframeNum);
    }

}
