package com.wangge.app;


import android.app.Application;


@SuppressWarnings("deprecation")
public class BaseApplication extends Application {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }

    public static BaseApplication getInstance() {
        return application;
    }

}
