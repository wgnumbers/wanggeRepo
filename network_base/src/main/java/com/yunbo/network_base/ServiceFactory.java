package com.wangge.network_base;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.wangge.app.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * 接口调用单例
 */

public class ServiceFactory {
    private static final long MAX_CACHE_SIZE = 10 * 1024 * 1024;
    private static final int TIMEOUT = 15;
    private static final OkHttpClient OK_HTTP_CLIENT;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(new Cache(new File(BaseApplication.getInstance().getCacheDir(), "retrofit"), MAX_CACHE_SIZE))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new CommonInterceptor())
                .followRedirects(true)
                .retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            //临时添加
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        OK_HTTP_CLIENT = builder.build();
    }

    public static OkHttpClient getOkhttoClient() {
        return OK_HTTP_CLIENT;
    }


    public static <T> T create(Class<T> clazz) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BuildConfig.APP_BASE_URL)
                .client(OK_HTTP_CLIENT)
                .build().create(clazz);
    }

    //获取验证码比较特殊
    public static <T> T createGetMessage(Class<T> clazz) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BuildConfig.APP_BASE_VERCODE_URL)
                .client(OK_HTTP_CLIENT)
                .build().create(clazz);
    }

    //短信登录比较特殊
    public static <T> T createMessageLogin(Class<T> clazz) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BuildConfig.APP_BASE_MESSAGE_URL)
                .client(OK_HTTP_CLIENT)
                .build().create(clazz);
    }

    //获取通知消息的端口不一样
    public static <T> T createNotifyMessage(Class<T> clazz) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BuildConfig.APP_BASE_NOTIFY_MESSAGE_URL)
                .client(OK_HTTP_CLIENT)
                .build().create(clazz);
    }

    //获取用户认证的
    public static <T> T createAuth(Class<T> clazz) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BuildConfig.APP_BASE_AUTH_URL)
                .client(OK_HTTP_CLIENT)
                .build().create(clazz);
    }

}
