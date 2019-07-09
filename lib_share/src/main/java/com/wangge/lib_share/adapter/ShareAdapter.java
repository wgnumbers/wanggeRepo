package com.wangge.lib_share.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangge.lib_share.R;
import com.wangge.lib_share.entity.ShareBtnEnum;

import java.util.List;

/**
 * Created by apple on 18/6/22.
 * 分享弹框adapter
 */

public class ShareAdapter extends BaseQuickAdapter<ShareBtnEnum,BaseViewHolder> {
    public ShareAdapter(@Nullable List<ShareBtnEnum> data) {
        super(R.layout.share_item_dialog_share, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ShareBtnEnum item) {
        switch (item){
            case qq:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_qq);
                helper.setText(R.id.mine_text,"QQ好友");
                break;
            case wechatfriends:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_wechatfriends);
                helper.setText(R.id.mine_text,"微信好友");
                break;
            case spaceqq:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_spaceqq);
                helper.setText(R.id.mine_text,"QQ空间");
                break;
            case friendscircle:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_friendscircle);
                helper.setText(R.id.mine_text,"朋友圈");
                break;
            case weibo:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_weibo);
                helper.setText(R.id.mine_text,"微博");
                break;
            case copylink:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_copylink);
                helper.setText(R.id.mine_text,"复制链接");
                break;
            case report:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_report);
                helper.setText(R.id.mine_text,"举报");
                break;
            case savephotoalbum:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_savephotoalbum);
                helper.setText(R.id.mine_text,"保存相册");
                break;
            case collect:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_collect);
                helper.setText(R.id.mine_text,"收藏");
                break;
            case nointerest:
                helper.setImageResource(R.id.mine_image,R.mipmap.icon_share_nointerest);
                helper.setText(R.id.mine_text,"不感兴趣");
                break;
        }

    }



}
