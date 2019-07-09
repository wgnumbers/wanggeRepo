package com.wangge.network_base;

import com.wangge.app.tools.ToastUtil;

public abstract class HttpCallBackIml<T> implements HttpCallBack<T>{
    @Override
    public abstract void onSuccess(T t);

    @Override
    public void onFail(Throwable throwable, String errorMsg) {
        if(throwable == null)//说明网络没问题
        {
            if(!errorMsg.equals(""))
                ToastUtil.show(errorMsg);
        }else //网络异常
        {
            ToastUtil.show(throwable.getMessage());
        }
    }
}
