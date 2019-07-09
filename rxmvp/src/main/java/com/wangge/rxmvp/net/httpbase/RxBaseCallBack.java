package com.wangge.rxmvp.net.httpbase;

import android.content.Context;
import android.util.Log;

import com.wangge.rxmvp.net.Model.ApiResponse;

import io.reactivex.observers.DisposableObserver;

/**
 * rx的回调
 *
 * @author fanlei
 * @version 1.0 2019/4/22 0022
 * @since JDK 1.8
 */
public abstract class RxBaseCallBack<T> extends DisposableObserver<ApiResponse<T>> implements LoadCancelListener {
    protected final int DEFAULT_FAIL_CODE = -1;
    protected final Context mContext;

    /**
     * @param context 上下文
     */
    public RxBaseCallBack(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onStart() {
        Log.i("RxBaseCallBack", "onStart:");
    }

    @Override
    public void onNext(ApiResponse<T> response) {
        if (response.getCode() == 200) {
            onSuc(response.getBody());
        } else {//失败响应
            onFail(response.getMsg(), response.getCode());
        }

    }

    @Override
    public void onError(Throwable t) {
        try {
            onFail(NetError.buildError(t).getErrorMessage(), DEFAULT_FAIL_CODE);
        } finally {
            onLoadCance();
        }

    }

    @Override
    public void onComplete() {
        onLoadCance();
    }

    @Override
    public void onLoadCance() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    protected abstract void onSuc(T response);

    protected abstract void onFail(String message, int failCode);

}
