package com.celtra.utils;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class RetryerUtils {

    public <T> T retry(Retryer<T> retryer, Callable<T> retryTask) {
        try {
            return retryer.call(retryTask);
        } catch (RetryException e) {
            throw new RuntimeException(e.getCause());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}