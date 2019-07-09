package com.wangge.lib_share.utils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class MobShareUtils {

    public static final int SHARE_QQ=1;//分享到qq好友
    public static final int SHARE_QZONE=2;//分享到qq空间
    public static final int SHARE_WECHAT=3;//分享到微信好友
    public static final int SHARE_WECHAT_FRIENDS=4;//分享到微信朋友圈
    public static final int SHARE_WEI_BO=5;//分享到微博
    static MobShareUtils mobShareUtils;


    public static MobShareUtils getInstance(){
        if (mobShareUtils==null){
            mobShareUtils=new MobShareUtils();
        }
        return mobShareUtils;
    };
    static SocialShareListener socialShareListener;
    /**
     * 分享视频到qq好友
     *
     */
    public  void shareToQQ(String videoUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);//非常重要：一定要设置分享属性
        sp.setTitle(title);  //分享标题
        sp.setText("人人咖短视频记录美好生活");
        sp.setTitleUrl(videoUrl);
        sp.setImageUrl(imageUrl);
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_QQ);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        }); // 设置分享事件回调
        // 执行分享
        qq.share(sp);
    }

    /**
     * 分享视频到qq空间
     */
    public  void shareToQZone(String videoUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setText("人人咖短视频记录美好生活");
        sp.setTitle(title);
        sp.setUrl(videoUrl);
        sp.setTitleUrl(videoUrl);
        sp.setImageUrl(imageUrl);
        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
        qzone.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_QZONE);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        });
        qzone.share(sp);

    }

    /**
     * 分享视频到微信好友
     *
     */
    public  void shareToWechat(String videoUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setText("人人咖短视频记录美好生活");
        sp.setImageUrl(imageUrl);
        sp.setUrl(videoUrl);
        sp.setTitle(title);
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_WECHAT);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        });
        wechat.share(sp);
    }

    /**
     * 分享视频到微信朋友圈
     * @param
     */
    public  void shareToWechatFriends(String videoUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setText("人人咖短视频记录美好生活");
        sp.setImageUrl(imageUrl);
        sp.setUrl(videoUrl);
        sp.setTitle(title);
        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
        wechatMoments.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_WECHAT_FRIENDS);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        });
        wechatMoments.share(sp);
    }

    /**
     * 分享视频到微博
     */
    public  void shareToWeiBo(String videoUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_LINKCARD);
        sp.setText("人人咖短视频记录美好生活");
        sp.setTitle(title);
        sp.setImageUrl(imageUrl);
        Platform weiBo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weiBo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_WEI_BO);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        });
        weiBo.share(sp);
    }

    /**
     * 分享音乐到qq好友
     *
     */
    public  void shareMusicToQQ(String musicUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_MUSIC);//非常重要：一定要设置分享属性
        sp.setTitle(title);  //分享标题
        sp.setUrl(musicUrl);
        sp.setImageUrl(imageUrl);
        sp.setText(title);
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_QQ);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        }); // 设置分享事件回调
        // 执行分享
        qq.share(sp);
    }

    /**
     * 分享音乐到qq空间
     */
    public  void shareMusicToQZone(String musicUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_MUSIC);
        sp.setText(title);
        sp.setImageUrl(imageUrl);
        sp.setUrl(musicUrl);
        sp.setTitle(title);
        Platform qzone = ShareSDK.getPlatform(QZone.NAME);
        qzone.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_QZONE);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        });
        qzone.share(sp);

    }

    /**
     * 分享音乐到微信好友
     *
     */
    public  void shareMusicToWechat(String musicUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_MUSIC);
        sp.setText(title);
        sp.setImageUrl(imageUrl);
        sp.setUrl(musicUrl);
        sp.setTitle(title);
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_WECHAT);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        });
        wechat.share(sp);
    }

    /**
     * 分享视频到微信朋友圈
     * @param
     */
    public  void shareMusicToWechatFriends(String videoUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_MUSIC);
        sp.setText(title);
        sp.setImageUrl(imageUrl);
        sp.setUrl(videoUrl);
        sp.setTitle(title);
        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
        wechatMoments.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_WECHAT_FRIENDS);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        });
        wechatMoments.share(sp);
    }

    /**
     * 分享音乐到微博
     */
    public  void shareMusicToWeiBo(String musicUrl,String imageUrl,String title){
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setShareType(Platform.SHARE_MUSIC);
        sp.setText(title);
        sp.setTitle(title);
        sp.setImageUrl(imageUrl);
        sp.setImageUrl(musicUrl);
        Platform weiBo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weiBo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                socialShareListener.shareSuccess(SHARE_WEI_BO);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                socialShareListener.shareFail();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                socialShareListener.shareCancel();
            }
        });
        weiBo.share(sp);
    }





    public void setSocialShareListener(SocialShareListener listener){
        this.socialShareListener=listener;
    }
    public interface SocialShareListener{
        void shareSuccess(int type);
        void shareFail();
        void shareCancel();
    }
}
