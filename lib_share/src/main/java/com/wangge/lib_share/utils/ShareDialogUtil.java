package com.wangge.lib_share.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.annotation.IntDef;

import com.wangge.lib_share.entity.ShareBtnEnum;
import com.wangge.lib_share.entity.ShareInfoEntity;

import java.util.ArrayList;

/**
 * @author liuf
 * @date 2019/4/16
 */
public class ShareDialogUtil {
    //设置分享的其他类型
    public static final int SHARE_ZERO = 0;//全部不显示
    public static final int SHARE_COPY = 1;//只有复制链接
    public static final int SHARE_MUSIC = 2;//分享音乐
    public static final int SHARE_FRAGMENT = 3;//关注列表
    public static final int SHARE_ALL = 10;//所有

    Context context;
    public ShareDialogUtil(Context context){
        this.context=context;
    }
    /**
     * 获取公共分享组件
     * @return
     */
    public ArrayList<ShareBtnEnum> getPublicShare(){
        ArrayList<ShareBtnEnum> publicShare = new ArrayList<>();
        publicShare.add(ShareBtnEnum.qq);
        publicShare.add(ShareBtnEnum.wechatfriends);
        publicShare.add(ShareBtnEnum.spaceqq);
        publicShare.add(ShareBtnEnum.friendscircle);
        publicShare.add(ShareBtnEnum.weibo);
        return publicShare;
    }


    @IntDef({SHARE_ZERO, SHARE_COPY,SHARE_MUSIC,SHARE_FRAGMENT, SHARE_ALL})
    @interface SHARE_OTHER_PLATEFORM_TYPE{}
    public ArrayList<ShareBtnEnum>  getOtherFunction(@SHARE_OTHER_PLATEFORM_TYPE int type){
        ArrayList<ShareBtnEnum> otherFunction = new ArrayList<>();
         if(type==SHARE_COPY){
            otherFunction.add(ShareBtnEnum.copylink);
        }else if(type==SHARE_MUSIC){
            otherFunction.add(ShareBtnEnum.report);
            otherFunction.add(ShareBtnEnum.copylink);
        }else if(type==SHARE_FRAGMENT){
            otherFunction.add(ShareBtnEnum.report);
            otherFunction.add(ShareBtnEnum.savephotoalbum);
            otherFunction.add(ShareBtnEnum.collect);
            otherFunction.add(ShareBtnEnum.nointerest);
            otherFunction.add(ShareBtnEnum.copylink);
        }else if(type==SHARE_ALL){
             otherFunction.add(ShareBtnEnum.report);
             otherFunction.add(ShareBtnEnum.savephotoalbum);
             otherFunction.add(ShareBtnEnum.collect);
             otherFunction.add(ShareBtnEnum.nointerest);
             otherFunction.add(ShareBtnEnum.copylink);
         }
        return otherFunction;
    }

    /**
     * 社会化分享
    */
    public void OnItemClickSocialShare(ShareInfoEntity shareInfoEntity,ShareBtnEnum shareEnum)
    {
        MobShareUtils share = MobShareUtils.getInstance();
        switch (shareEnum){
            case qq:
                share.shareToQQ(shareInfoEntity.getUrl(), shareInfoEntity.getImageUrl(), shareInfoEntity.getTitle());
                break;
            case wechatfriends:
                share.shareToWechat(shareInfoEntity.getUrl(), shareInfoEntity.getImageUrl(), shareInfoEntity.getTitle());
                break;
            case spaceqq:
                share.shareToQZone(shareInfoEntity.getUrl(), shareInfoEntity.getImageUrl(), shareInfoEntity.getTitle());
                break;
            case friendscircle:
                share.shareToWechatFriends(shareInfoEntity.getUrl(), shareInfoEntity.getImageUrl(), shareInfoEntity.getTitle());
                break;
            case weibo:
                share.shareToWeiBo(shareInfoEntity.getUrl(), shareInfoEntity.getImageUrl(), shareInfoEntity.getTitle());
                break;
        }

        share.setSocialShareListener(new MobShareUtils.SocialShareListener() {
            @Override
            public void shareSuccess(int type) {
                //分享成功
            }

            @Override
            public void shareFail() {

            }

            @Override
            public void shareCancel() {

            }
        });
    }


    public  boolean putTextIntoClip(Context context, String key, String value) {
        boolean isSuccess = false;
        try {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            //创建ClipData对象
            ClipData clipData = ClipData.newPlainText(key, value);
            //添加ClipData对象到剪切板中
            clipboardManager.setPrimaryClip(clipData);
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;

    }





}
