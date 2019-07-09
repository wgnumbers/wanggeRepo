package com.wangge.rxmvp.net;

import android.content.Context;

import com.wangge.rxmvp.net.Model.ApiResponse;
import com.wangge.rxmvp.net.httpbase.RxBaseCallBack;


/**
 * 业务层的网络回调
 *
 * @author fanlei
 * @version 1.0 2019/4/24 0024
 * @since JDK 1.8
 */
public abstract class MyCallBack<T> extends RxBaseCallBack<ApiResponse<T>> {
    /**
     * @param context 上下文
     */
    public MyCallBack(Context context) {
        super(context);
    }


    @Override
    protected void onSuc(ApiResponse<T> t) {
        if (t.getCode() == 0) {
            if (t.getBody() != null) {
                onSuccess(t.getBody());
            } else {
                onFail(t.getMsg(), t.getCode());
            }
        } else {
            onFail(t.getMsg(), t.getCode());
        }
    }

    public abstract void onSuccess(T t);
}
