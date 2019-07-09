//package com.yunbo.lib_share.view;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Display;
//import android.view.Gravity;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.yunbo.lib_share.R;
//import com.yunbo.lib_share.adapter.ShareAdapter;
//
//import java.util.ArrayList;
//@Deprecated
//public class TrendShareDialog extends Dialog {
//    private Context context;
//    private ImageView iv_share_close;
//    private RecyclerView rv_share_nearest_user;
//    private RecyclerView rv_share_social_platform;
//    private RecyclerView rv_share_other_platform;
//    BaseQuickAdapter<ShareAdapter.ShareEntity,BaseViewHolder> adapter_nearest_platform;
//    BaseQuickAdapter<ShareAdapter.ShareEntity,BaseViewHolder> adapter_social_platform;
//    BaseQuickAdapter<ShareAdapter.ShareEntity,BaseViewHolder> adapter_other_platform;
//
//    private static ArrayList<ShareAdapter.ShareEntity> social_platforms = new ArrayList<>();
//    private static ArrayList<ShareAdapter.ShareEntity> nearest_platforms = new ArrayList<>();
//    private static ArrayList<ShareAdapter.ShareEntity> other_platforms = new ArrayList<>();
//
//    private ShareResultListener shareResultListener;
//    private BaseQuickAdapter.OnItemClickListener itemClickListener;
//    private BaseQuickAdapter.OnItemClickListener socialItemClickListener;
//    private int videoId;
//    private int userId;
//    static {
////        social_platforms.add(new ShareEntity(R.mipmap.icon_share_goodfriends, "站内信"));
//        social_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_qq, "QQ好友"));
//        social_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_wechatfriends, "微信好友"));
//        social_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_spaceqq, "QQ空间"));
//        social_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_friendscircle, "朋友圈"));
//        social_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_weibo, "微博"));
//
//        other_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_report,"举报"));
//        other_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_savephotoalbum,"保存相册"));
//        other_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_collect,"收藏"));
//        other_platforms.add(new ShareAdapter.ShareEntity(R.mipmap.icon_share_copylink,"复制链接"));
////        other_platforms.add(new ShareEntity(R.mipmap.icon_share_qrcode,"二维码"));
////        other_platforms.add(new ShareEntity(R.mipmap.icon_share_speedtop,"速推"));
//
////        nearest_platforms.add(new ShareEntity(R.mipmap.icon_share_goodfriends,"牙巴用亚瑟"));
//
//    }
//
//
//
//    public TrendShareDialog(Context context) {
//        super(context, R.style.share_BottomSettingShareDialog);
//        this.context =context;
//        initView();
//    }
//
//    public TrendShareDialog(Context context, int themeResId) {
//        super(context, themeResId);
//        this.context = context;
//        initView();
//    }
//
//    private void initView()
//    {
//        setContentView(R.layout.share_setting_share_dialog);
//        getWindow().setGravity(Gravity.BOTTOM); //显示在底部
//
//        WindowManager m = getWindow().getWindowManager();
//        Display d = m.getDefaultDisplay();
//        WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.width = WindowManager.LayoutParams.MATCH_PARENT; //设置dialog的宽度为当前手机屏幕的宽度
//        getWindow().setAttributes(p);
//        getWindow().getDecorView().setPadding(0, 0, 0, 0);
//        iv_share_close = findViewById(R.id.iv_share_close);
//        rv_share_nearest_user = findViewById(R.id.rv_share_nearest_user);
//        rv_share_social_platform = findViewById(R.id.rv_share_social_platform);
//        rv_share_other_platform = findViewById(R.id.rv_share_other);
//
//        iv_share_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//        //最近联系人
//        LinearLayoutManager llm_nearest_user = new LinearLayoutManager(context);
//        llm_nearest_user.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rv_share_nearest_user.setLayoutManager(llm_nearest_user);
//        adapter_nearest_platform = new ShareAdapter(nearest_platforms);
//        rv_share_nearest_user.setAdapter(adapter_nearest_platform);
//
//        //社交平台
//        LinearLayoutManager llm_social_platform = new LinearLayoutManager(context);
//        llm_social_platform.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rv_share_social_platform.setLayoutManager(llm_social_platform);
//        adapter_social_platform = new ShareAdapter(social_platforms);
//        rv_share_social_platform.setAdapter(adapter_social_platform);
//
//        //其他平台
//        LinearLayoutManager llm_other_platform = new LinearLayoutManager(context);
//        llm_other_platform.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rv_share_other_platform.setLayoutManager(llm_other_platform);
//        adapter_other_platform = new ShareAdapter(other_platforms);
//        rv_share_other_platform.setAdapter(adapter_other_platform);
//
//
//        adapter_other_platform.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                if (itemClickListener!=null){
//                    itemClickListener.onItemClick(adapter,view,position);
//                }
//            }
//        });
//        adapter_social_platform.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (socialItemClickListener!=null){
//                    socialItemClickListener.onItemClick(adapter,view,position);
//                }
//            }
//        });
//        adapter_other_platform.bindToRecyclerView(rv_share_other_platform);
//    }
//
//    public interface ShareResultListener {
//
//        void onShareSucceed();
//
//        void onShareFailed();
//    }
//
//    public void setSocialItemClickListener(BaseQuickAdapter.OnItemClickListener listener){
//        this.socialItemClickListener=listener;
//    }
//
//    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener listener){
//        this.itemClickListener=listener;
//    }
//
//    public void setShareResultListener(ShareResultListener listener) {
//        this.shareResultListener = listener;
//    }
//
//    public void setVideoId(int videoId,int userId){
//        this.videoId=videoId;
//        this.userId = userId;
//    }
//
//    public int collectStatus;
//    public void setCollectStatus(int status){
//        ImageView image=  (ImageView) adapter_other_platform.getViewByPosition(2,R.id.mine_image);
//        TextView textView=  (TextView) adapter_other_platform.getViewByPosition(2,R.id.mine_text);
//        textView.setText(status==0?"收藏":"取消收藏");
//        collectStatus=status;
//    }
//
//}
