package com.wangge.network_base;


import com.google.gson.annotations.SerializedName;

/**
 * api返回值
 */

public class ApiResponse<T> {


    @SerializedName("code")
    private String code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private T body;


    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


    public T getBody() {
        return body;
    }


}
