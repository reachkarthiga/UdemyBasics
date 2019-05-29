package com.android.example.udemybasics;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.squareup.spoon.Spoon;

import org.hamcrest.Matcher;

import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.base.DefaultFailureHandler;

public class CustomFailureHandler implements FailureHandler {
    private final FailureHandler mDelegate;
    private String className;
    private String methodName = "";
    private Activity activity;

    public CustomFailureHandler(Context targetContext, String className, Activity activity) {
        mDelegate = new DefaultFailureHandler(targetContext);
        this.className = className;
        this.activity = activity;
    }

    @Override
    public void handle(Throwable error, Matcher<View> viewMatcher) {
        try {
            mDelegate.handle(error, viewMatcher);
        } catch (Exception e) {
            Spoon.screenshot(activity, "Espresso_Assertion_Failed", className, methodName);
            throw e;
        }
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
