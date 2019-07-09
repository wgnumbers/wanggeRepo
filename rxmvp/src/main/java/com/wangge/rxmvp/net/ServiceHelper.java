package com.wangge.rxmvp.net;


import androidx.annotation.StringDef;

import com.wangge.rxmvp.net.config.MyNetProvider;
import com.wangge.rxmvp.net.config.UrlConfig;
import com.wangge.rxmvp.net.httpbase.HttpClient;

import java.util.HashMap;
import java.util.Map;


/**
 * service的帮助类
 *
 * @author fanlei
 * @version 1.0 2019/4/24 0024
 * @since JDK 1.8
 */
public class ServiceHelper {
    public final static String MESSAGE = "message";
    public final static String AUTH = "auth";
    public final static String VERCODE = "vercode";
    public final static String BASE = "base";
    private Object s;
    Map<String, Object> serviceMap = new HashMap();

    private ServiceHelper() {
    }

    private static ServiceHelper serviceHelper;

    public static synchronized ServiceHelper init() {
        if (null == serviceHelper) {
            serviceHelper = new ServiceHelper();
        }
        return serviceHelper;
    }

    @StringDef(value = {MESSAGE, AUTH, VERCODE, BASE})
    public @interface UrlType {
    }

    @SuppressWarnings("ALL")
    public <S> S getService(@UrlType String urlType, Class<S> service) {
        String key = new StringBuilder(urlType).append(service.getSimpleName()).toString();
        if (serviceMap.get(key) != null) {
            return (S) serviceMap.get(key);
        }

        String baseUrl;
        switch (urlType) {
            case MESSAGE:
                baseUrl = UrlConfig.MESSAGE_URL;
                break;
            case AUTH:
                baseUrl = UrlConfig.AUTH_URL;
                break;
            case VERCODE:
                baseUrl = UrlConfig.VERCODE_URL;
                break;
            default:
                baseUrl = UrlConfig.BASE_URL;
        }
        s = HttpClient.getInstance().getRetrofit(baseUrl, new MyNetProvider(), true).create(service);
        serviceMap.put(key, s);
        return (S) s;
    }

    public static void clearCache() {
        init().serviceMap.clear();
    }


}
