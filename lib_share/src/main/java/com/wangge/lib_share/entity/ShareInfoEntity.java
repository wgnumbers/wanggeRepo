package com.wangge.lib_share.entity;

/**
 * @author liuf
 * @date 2019/4/16
 */
public class ShareInfoEntity {

    int type;//ShareDialogUtil.SHARE_*
   String url;//全地址url
   String imageUrl;//图片地址
   String title; //标题
   int userno;

    public ShareInfoEntity(int type,String url, String imageUrl, String title) {
        this.type=type;
        this.url = url;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }
}
