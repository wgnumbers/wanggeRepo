package com.wangge.app.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class SystemUtils {

    public static int getScreenWidth(Context ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager mWm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        mWm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

}
