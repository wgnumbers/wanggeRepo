package com.wangge.news;

import android.os.Build;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.wangge.app.ComponentC;
import com.wangge.news.fragment.NewsFragment;

@SuppressWarnings("deprecation")
public class ComponentNews implements IComponent {
    @Override
    public String getName() {
        return ComponentC.ComponentNews.ComponentName;
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        if(ComponentC.ComponentNews.Start_Fragment.equals(actionName)){
            CC.sendCCResult(cc.getCallId(), CCResult.success(ComponentC.ComponentNews.NewsFragment_Value, new NewsFragment()));
        }
        return false;
    }


}
