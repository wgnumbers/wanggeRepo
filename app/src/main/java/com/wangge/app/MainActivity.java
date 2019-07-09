package com.wangge.app;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.fl_content)
    FrameLayout flContent;
    @BindView(R2.id.ll_build)
    LinearLayout llBuild;
    @BindView(R2.id.ll_location)
    LinearLayout llLocation;
    @BindView(R2.id.ll_news)
    LinearLayout llNews;
    @BindView(R2.id.ll_mine)
    LinearLayout llMine;
    @BindView(R2.id.iv_work)
    ImageView ivWork;

    @BindView(R2.id.iv_build)
    ImageView ivBuild;
    @BindView(R2.id.iv_location)
    ImageView ivLocation;
    @BindView(R2.id.iv_news)
    ImageView ivNews;
    @BindView(R2.id.iv_mine)
    ImageView ivMine;

    private FragmentManager mFragmentManager;
    private Fragment buildFragment, locationFragment, newsFragment, mineFragment;
    private final String mTagBuildFragment = "TagBuildFragment", mTagLocationFragment = "TagLocationFragment", mTagNewsFragment = "TagNewsFragment", mTagMineFragment = "TagMineFragment";
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        mFragmentManager = getSupportFragmentManager();
        CC.enableDebug(BuildConfig.DEBUG);  //普通调试日志，会提示一些错误信息
        CC.enableVerboseLog(BuildConfig.DEBUG);  //组件调用的详细过程日志，用于跟踪整个调用过程
        transaction = mFragmentManager.beginTransaction();
        settingStatus(R.id.ll_build);
        if (buildFragment == null) {
            CCResult ccResult = CC.obtainBuilder(ComponentC.ComponentBuild.ComponentName)
                    .setActionName(ComponentC.ComponentBuild.Start_Fragment)
                    .setContext(MainActivity.this)
                    .build()
                    .call();
            buildFragment = ccResult.getDataItem(ComponentC.ComponentBuild.BuildFragment_Value);
            transaction.add(R.id.fl_content, buildFragment, mTagBuildFragment);
            //设置图标的selector
            StateListDrawable image = getImgSelector(this, R.mipmap.icon_main_store, R.mipmap.icon_main_store_sel);
            ivBuild.setImageDrawable(image);
        }
        transaction.show(buildFragment);
        transaction.commitAllowingStateLoss();
        llBuild.setSelected(true);
    }


    @OnClick({R2.id.ll_build, R2.id.ll_location, R2.id.ll_news, R2.id.ll_mine, R2.id.iv_work})
    public void onClick(View v) {
        transaction = mFragmentManager.beginTransaction();
        int id = v.getId();
        settingStatus(id);
        if (id == R.id.ll_build) {
            if (buildFragment == null) {
                CCResult ccResult = CC.obtainBuilder(ComponentC.ComponentBuild.ComponentName)
                        .setActionName(ComponentC.ComponentBuild.Start_Fragment)
                        .setContext(MainActivity.this)
                        .build()
                        .call();
                buildFragment = ccResult.getDataItem(ComponentC.ComponentBuild.BuildFragment_Value);
                transaction.add(R.id.fl_content, buildFragment, mTagBuildFragment);
                //设置图标的selector
                StateListDrawable image = getImgSelector(this, R.mipmap.icon_main_store, R.mipmap.icon_main_store_sel);
                ivBuild.setImageDrawable(image);
            }
            transaction.show(buildFragment);
            llBuild.setSelected(true);
        } else if (id == R.id.ll_location) {
            if (locationFragment == null) {
                CCResult ccResult = CC.obtainBuilder(ComponentC.ComponentLocation.ComponentName)
                        .setActionName(ComponentC.ComponentLocation.Start_Fragment)
                        .setContext(MainActivity.this)
                        .build()
                        .call();
                locationFragment = ccResult.getDataItem(ComponentC.ComponentLocation.LocationFragment_Value);
                transaction.add(R.id.fl_content, locationFragment, mTagLocationFragment);
                //设置图标的selector
                StateListDrawable image = getImgSelector(this, R.mipmap.icon_main_market, R.mipmap.icon_main_market_sel);
                ivLocation.setImageDrawable(image);
            }
            transaction.show(locationFragment);
            llLocation.setSelected(true);
        } else if (id == R.id.ll_news) {
            if (newsFragment == null) {
                CCResult ccResult = CC.obtainBuilder(ComponentC.ComponentNews.ComponentName)
                        .setActionName(ComponentC.ComponentNews.Start_Fragment)
                        .setContext(MainActivity.this)
                        .build()
                        .call();
                newsFragment = ccResult.getDataItem(ComponentC.ComponentNews.NewsFragment_Value);
                transaction.add(R.id.fl_content, newsFragment, mTagNewsFragment);
                //设置图标的selector
                StateListDrawable image = getImgSelector(this, R.mipmap.icon_main_message, R.mipmap.icon_main_message);
                ivNews.setImageDrawable(image);
            }
            transaction.show(newsFragment);
            llNews.setSelected(true);
        } else if (id == R.id.ll_mine) {
            if (mineFragment == null) {
                CCResult ccResult = CC.obtainBuilder(ComponentC.ComponentMine.ComponentName)
                        .setActionName(ComponentC.ComponentMine.Start_Fragment)
                        .setContext(MainActivity.this)
                        .build()
                        .call();
                mineFragment = ccResult.getDataItem(ComponentC.ComponentMine.MineFragment_Value);
                transaction.add(R.id.fl_content, mineFragment, mTagMineFragment);
                //设置图标的selector
                StateListDrawable image = getImgSelector(this, R.mipmap.icon_main_mine, R.mipmap.icon_main_mine);
                ivMine.setImageDrawable(image);
            }
            transaction.show(mineFragment);
            llMine.setSelected(true);
        } else if (id == R.id.iv_work) {
            // TODO: 2019/7/9

        }
        transaction.commitAllowingStateLoss();
    }

    //点击拍摄时不隐藏其他fragment
    private void settingStatus(int id) {
        if (id == R.id.iv_work) return;
        buildFragment = mFragmentManager.findFragmentByTag(mTagBuildFragment);
        locationFragment = mFragmentManager.findFragmentByTag(mTagLocationFragment);
        newsFragment = mFragmentManager.findFragmentByTag(mTagNewsFragment);
        mineFragment = mFragmentManager.findFragmentByTag(mTagMineFragment);
        llBuild.setSelected(false);
        llLocation.setSelected(false);
        llNews.setSelected(false);
        llMine.setSelected(false);
        if (buildFragment != null) {
            transaction.hide(buildFragment);
        }
        if (locationFragment != null) {
            transaction.hide(locationFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    public static StateListDrawable getImgSelector(Context ctx, @DrawableRes int normalResId, @DrawableRes int selectedResId) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, ctx.getDrawable(selectedResId));
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, ctx.getDrawable(selectedResId));
        stateListDrawable.addState(new int[]{}, ctx.getDrawable(normalResId));
        return stateListDrawable;
    }
}
