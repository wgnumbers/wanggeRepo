package com.wangge.app;


import com.mob.MobSDK;

public class MyApplyaction extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);

    }
}
