package com.wangge.rxmvp.net.Model;


import com.google.gson.annotations.SerializedName;

/**
 * 业务层的数据封装类
 *
 * @author fanlei
 * @version 1.0 2019/4/24 0024
 * @since JDK 1.8
 */
public class ApiResponse<T> {


    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private T body;


    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }


    public T getBody() {
        return body;
    }


}
