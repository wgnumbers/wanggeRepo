package com.wangge.rxmvp.net.config;

import com.wangge.rxmvp.BuildConfig;

/**
 * url配置
 *
 * @author fanlei
 * @version 1.0 2019/4/24 0024
 * @since JDK 1.8
 */
public class UrlConfig {
    private static String TEST_URL = "http://api.laifudao.com/";
    private static String TEST_MESSAGE_URL = "http://api.laifudao.com/";
    private static String TEST_AUTH_URL = "http://api.laifudao.com/";
    private static String TEST_VERCODE_URL = "http://api.laifudao.com/";


    //===============================================================================
    // 请勿动以下正式环境下的url

    private static String PRO_URL = "http://api.laifudao.com/";
    private static String PRO_MESSAGE_URL = "http://api.laifudao.com/";
    private static String PRO_AUTH_URL = "http://api.laifudao.com/";
    private static String PRO_VERCODE_URL = "http://api.laifudao.com/";

    public static final String BASE_URL;
    public static final String MESSAGE_URL;
    public static final String AUTH_URL;
    public static final String VERCODE_URL;

    static {
        if (!BuildConfig.DEBUG) {
            // release
            BASE_URL = PRO_URL;
            MESSAGE_URL = PRO_MESSAGE_URL;
            AUTH_URL = PRO_AUTH_URL;
            VERCODE_URL = PRO_VERCODE_URL;
        } else {
            // debug
            BASE_URL = TEST_URL;
            MESSAGE_URL = TEST_MESSAGE_URL;
            AUTH_URL = TEST_AUTH_URL;
            VERCODE_URL = TEST_VERCODE_URL;
        }
    }
}
