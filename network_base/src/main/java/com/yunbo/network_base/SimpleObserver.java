package com.wangge.network_base;


import android.util.Log;

import com.wangge.app.tools.C;
import com.wangge.app.tools.LogUtils;
import com.wangge.app.tools.SPUtils;
import com.wangge.app.tools.ToastUtil;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by huangyang on 2018/5/25.
 */

public abstract class SimpleObserver<T> extends DisposableObserver<ApiResponse<T>> {
    private static final String CODE_SUCESS = "0";//0代表成功
    private static final String CODE_FAIL = "1";
    private static final String CODE_TOKEN_FAIL = "999";//token过期

    public SimpleObserver() {
    }


    @Override
    public final void onNext(ApiResponse<T> t) {
        if (t.getCode().equals(CODE_SUCESS)) {
            if (t.getBody() != null) {
                try {
                    onSuccess(t.getBody());
                }catch (Exception e){
                    e.printStackTrace();
                    ToastUtil.show("服务异常，请稍后再试");
                }
            } else {
                onError(null, t.getMsg() + "");
                onToastShow(t.getMsg());
            }
        } else if (t.getCode().equals(CODE_TOKEN_FAIL)) {
            SPUtils.putString(C.Constant.TOKEN, "");
            SPUtils.putString(C.Constant.USERID, "");
            onError(null, t.getMsg() + "");
            onToastShow(t.getMsg());
        } else {
            Log.e("sx", "SimpleObserver onNext else onError");
            onError(null, t.getMsg() + "");
        }
    }

    @Override
    public final void onError(Throwable throwable) {
        try {
            LogUtils.e(throwable.getMessage());

        } catch (Exception e) {

        } finally {
            ToastUtil.show("网络连接服务器失败");
//            onError(throwable, "");
            onFinish();
        }
    }

    @Override
    public final void onComplete() {
        onFinish();
//        Logger.e(throwable.getMessage());
    }

    public void onToastShow(String msg) {
        LogUtils.e(msg);
    }

    protected abstract void onSuccess(T t);

    /**
     * 该方法是当throwablem为null时候 说明网络正常，此时读取errorMsg里面信息 是服务器接口返回的信息，当throwablem不为null时 说明网络存在问题 直接读取throwablem.getMessage()
     *
     * @param throwable 网络异常信息对象
     * @param errorMsg  错误信息返回
     */
    protected abstract void onError(Throwable throwable, String errorMsg);

    protected void onFinish() {

    }
}
