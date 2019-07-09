package com.wangge.lib_share.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class MobLoginUtils {


    private Context context;
    private Dialog dialog;

    /**
     * qq登录
     */
    public void qqLogin(Context context) {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(mPlatformActionListener);
        qq.SSOSetting(false);
        if (!qq.isClientValid()) {
//            RxToast.showToast("QQ未安装,请先安装QQ");
            Toast.makeText(context, "QQ未安装,请先安装QQ", Toast.LENGTH_SHORT).show();
        }
        authorize(qq);
    }

    /**
     * 微信登录
     */
    public void weiXinLogin(Context context, Dialog dialog) {
        this.context = context;
        this.dialog = dialog;
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        if (!wechat.isClientValid()) {
//            RxToast.showToast("微信未安装,请先安装微信");
            Toast.makeText(context, "微信未安装,请先安装微信", Toast.LENGTH_SHORT).show();
        }
        authorize(wechat);
        wechat.setPlatformActionListener(mPlatformActionListener);
        wechat.SSOSetting(false);

        if (dialog != null) {
            dialog.show();
        }
    }

    /**
     * 授权
     *
     * @param platform
     */
    private void authorize(Platform platform) {
        if (platform == null) {
            return;
        }
//        if (platform.isAuthValid()) {  //如果授权就删除授权资料
//            platform.removeAccount(true);
//        }
        platform.removeAccount(true);
        platform.showUser(null); //授权并获取用户信息
    }

    private PlatformActionListener mPlatformActionListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//        Toast.makeText(LoginActivity.this, "授权登陆成功", Toast.LENGTH_SHORT).show();
            String userId = platform.getDb().getUserId();//获取用户账号
            String userName = platform.getDb().getUserName();//获取用户名字
            String userIcon = platform.getDb().getUserIcon();//获取用户头像
            String userGender = platform.getDb().getUserGender(); //获取用户性别，m = 男, f = 女，如果微信没有设置性别,默认返回null
            final String unionid = (String) hashMap.get("unionid");
            final String nickname = (String) hashMap.get("nickname");
            final String headimgurl = (String) hashMap.get("headimgurl");
            int sex = (int) hashMap.get("sex");
            final String openid = (String) hashMap.get("openid");
            // 注意：此回调方法处于网络线程，一般情况下回调方法都不在主线程.需手动切换线程
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (otherLoginListener != null)
                        otherLoginListener.loginSuccess(openid, unionid, nickname, headimgurl, 1 + "");
                }
            });


//            RxToast.showToast("用户名：" + userName + "  性别：" + userGender);
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Log.i("LoginModle", "第三方登录失败：");

            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });


        }

        @Override
        public void onCancel(Platform platform, int i) {
            Log.i("LoginModle", "第三方登录取消：");
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });
        }
    };


    public OtherLoginListener otherLoginListener;

    public void setOtherLoginListener(OtherLoginListener otherLoginListener) {
        this.otherLoginListener = otherLoginListener;
    }

    public interface OtherLoginListener {
        void loginSuccess(String openid, String unionid, String nickname, String headimgurl, String sex);
    }
}
