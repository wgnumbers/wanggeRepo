package com.wangge.lib_share.view;

import android.app.Dialog;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wangge.lib_share.R;
import com.wangge.lib_share.adapter.ShareAdapter;
import com.wangge.lib_share.entity.ShareBtnEnum;
import com.wangge.lib_share.entity.ShareInfoEntity;
import com.wangge.lib_share.utils.ShareDialogUtil;

import java.util.List;

public class SharePublicDialog extends Dialog {
    private Context context;
    private ImageView iv_share_close;
    private RecyclerView rv_share_social_platform;
    private RecyclerView rv_share_other_platform;
    ShareAdapter adapter_social_platform;
    ShareAdapter adapter_other_platform;

    private BaseQuickAdapter.OnItemClickListener moreItemClickListener;
    ShareDialogUtil shareDialogUtil;
    ShareInfoEntity shareInfoEntity;


    public SharePublicDialog(Context context, ShareInfoEntity shareInfoEntity) {
        super(context, R.style.share_BottomSettingShareDialog);
        this.context = context;
        this.shareInfoEntity=shareInfoEntity;
        shareDialogUtil=new ShareDialogUtil(context);
        initView(shareInfoEntity.getType());
    }



    private void initView(int type) {
        setContentView(R.layout.share_setting_share_dialog);
        getWindow().setGravity(Gravity.BOTTOM); //显示在底部

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        final WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT; //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        iv_share_close = findViewById(R.id.iv_share_close);
        rv_share_social_platform = findViewById(R.id.rv_share_social_platform);
        rv_share_other_platform = findViewById(R.id.rv_share_other);

        iv_share_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //社交平台
        LinearLayoutManager llm_social_platform = new LinearLayoutManager(context);
        llm_social_platform.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_share_social_platform.setLayoutManager(llm_social_platform);
        adapter_social_platform = new ShareAdapter(shareDialogUtil.getPublicShare());
        rv_share_social_platform.setAdapter(adapter_social_platform);

        adapter_social_platform.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                shareDialogUtil.OnItemClickSocialShare(shareInfoEntity, (ShareBtnEnum) adapter.getData().get(position));
                dismiss();

            }
        });

        //其他平台
        LinearLayoutManager llm_other_platform = new LinearLayoutManager(context);
        llm_other_platform.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_share_other_platform.setLayoutManager(llm_other_platform);
        adapter_other_platform = new ShareAdapter(shareDialogUtil.getOtherFunction(type));
        rv_share_other_platform.setAdapter(adapter_other_platform);

        adapter_other_platform.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShareBtnEnum btnEnum= (ShareBtnEnum) adapter.getData().get(position);
                if (btnEnum==ShareBtnEnum.copylink){
                   boolean flag= shareDialogUtil.putTextIntoClip(context,shareInfoEntity.getTitle(),shareInfoEntity.getUrl());
                   if (flag){
                       Toast.makeText(context,"已复制",Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(context,"复制失败",Toast.LENGTH_SHORT).show();
                   }
                    dismiss();
                }else {
                    if (moreItemClickListener!=null){

                        moreItemClickListener.onItemClick(adapter,view,position);
                        dismiss();
                    }
                }

            }
        });
        adapter_other_platform.bindToRecyclerView(rv_share_other_platform);
    }

    //复制已经统一处理
    public SharePublicDialog setMoreItemClickListener(BaseQuickAdapter.OnItemClickListener listener) {
        this.moreItemClickListener = listener;
        return this;
    }


    public int collectStatus;

    public void setCollectStatus(int status) {
        int pos=-1;
        List<ShareBtnEnum> enumList= adapter_other_platform.getData();
        for (int i = 0; i <enumList.size() ; i++) {
            if (enumList.get(i)==ShareBtnEnum.collect){
                pos=i;
                break;
            }
        }
        if (pos>=0){
            ImageView image = (ImageView) adapter_other_platform.getViewByPosition(pos, R.id.mine_image);
            TextView textView = (TextView) adapter_other_platform.getViewByPosition(pos, R.id.mine_text);
            textView.setText(status == 0 ? "收藏" : "取消收藏");
            image.setImageResource(status == 0 ? R.mipmap.icon_share_collect:R.mipmap.icon_share_collect_already);
            collectStatus = status;
        }

    }


}
