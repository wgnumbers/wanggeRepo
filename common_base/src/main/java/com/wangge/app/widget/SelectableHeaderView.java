package com.wangge.app.widget;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.wangge.app.R;

public class SelectableHeaderView extends LinearLayout {

    private final ImageView back;
    private final ImageView search;
    private final TextView leftTV;
    private final TextView rightTV;
    private OnClickListener leftListener;
    private OnClickListener rightListener;

    public SelectableHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_selectable_header_view, this);
        leftTV = findViewById(R.id.tv_left);
        rightTV = findViewById(R.id.tv_right);
        back = findViewById(R.id.iv_back);
        search = findViewById(R.id.iv_sousuo);
        setDefault();
        leftTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBG("left");
                leftListener.onClick(v);
            }
        });
        rightTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBG("right");
                rightListener.onClick(v);
            }
        });
    }

    private void setDefault() {
        leftTV.setSelected(true);
        changeBG("left");
    }

    private void changeBG(String tag) {
        if (tag.equals("left")) {
            rightTV.setSelected(false);
            leftTV.setSelected(true);
            leftTV.setBackground(getDrawableSelector(getContext(), R.drawable.base_layerlist_product_header0));
            leftTV.setTextColor(getResources().getColor(R.color.color_FDFDFD));
            rightTV.setTextColor(getResources().getColor(R.color.color_137DF4));
        } else {
            leftTV.setSelected(false);
            rightTV.setSelected(true);
            rightTV.setBackground(getDrawableSelector(getContext(), R.drawable.base_layerlist_product_header1));
            rightTV.setTextColor(getResources().getColor(R.color.color_FDFDFD));
            leftTV.setTextColor(getResources().getColor(R.color.color_137DF4));
        }
    }

    public void setLeftText(String text) {
        leftTV.setText(text);
    }

    public void setRightText(String text) {
        rightTV.setText(text);
    }

    public void setLeftClickListener(OnClickListener listener) {
        this.leftListener = listener;
    }

    public void setRightClickListener(OnClickListener listener) {
        this.rightListener = listener;
    }

    public void setBackClickListener(OnClickListener listener) {
        back.setOnClickListener(listener);
    }

    public void setSearchClickListener(OnClickListener listener) {
        search.setOnClickListener(listener);
    }

    public static StateListDrawable getDrawableSelector(Context ctx, @DrawableRes int selectedResId) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, ctx.getDrawable(selectedResId));
        stateListDrawable.addState(new int[]{}, null);
        return stateListDrawable;
    }

}
