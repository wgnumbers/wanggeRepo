package com.wangge.network_base;

import com.wangge.app.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: cxx
 * Date: 2019-03-13
 * GitHub: https://github.com/ccolorcat
 */
public class RxHelper<T> implements ObservableTransformer<T, T> {
    private BaseActivity mBase;

    public RxHelper(BaseActivity base) {
        mBase = base;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        BaseActivity activity = mBase;
        mBase = null;
        return upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.<T>bindToLifecycle());
    }
}
