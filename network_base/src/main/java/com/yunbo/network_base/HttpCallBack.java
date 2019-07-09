package com.wangge.network_base;

public interface HttpCallBack<T> {
    void onSuccess(T t);
    void onFail(Throwable throwablem,String errorMsg);
}
