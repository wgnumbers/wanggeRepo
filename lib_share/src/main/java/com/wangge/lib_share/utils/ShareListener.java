package com.wangge.lib_share.utils;

/**
 * Created by ly on 18/7/12.
 */

public interface ShareListener {

//    @Override
//    public abstract void onComplete(Platform platform, int i, HashMap<String, Object> hashMap);
//
//    @Override
//    public abstract void onError(Platform platform, int i, Throwable throwable);
//
//    @Override
//    public abstract void onCancel(Platform platform, int i) ;
    public void success();
    public void error();
    public void cancle();
}
