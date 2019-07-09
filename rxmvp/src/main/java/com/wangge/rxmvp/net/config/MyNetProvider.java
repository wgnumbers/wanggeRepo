package com.wangge.rxmvp.net.config;

import com.wangge.rxmvp.BuildConfig;
import com.wangge.rxmvp.net.httpbase.NetProvider;
import com.wangge.rxmvp.net.interceptor.HeaderInterceptor;
import com.wangge.rxmvp.net.interceptor.RequestHeader;

import okhttp3.Interceptor;


/**
 * 网络配置
 *
 * @author fanlei
 * @version 1.0 2019/4/24 0024
 * @since JDK 1.8
 */
public class MyNetProvider implements NetProvider {
    @Override
    public long configConnectTimeoutMills() {
        return 15;
    }

    @Override
    public long configReadTimeoutMills() {
        return 15;
    }

    @Override
    public Interceptor[] configInterceptors() {
        return new Interceptor[0];
    }

    @Override
    public boolean configLogEnable() {
        return BuildConfig.DEBUG ? true : false;
    }

    @Override
    public RequestHeader configHeader() {
        return new HeaderInterceptor();
    }
}
