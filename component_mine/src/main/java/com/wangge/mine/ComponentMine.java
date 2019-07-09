package com.wangge.mine;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.wangge.app.ComponentC;
import com.wangge.mine.fragment.MineFragment;

@SuppressWarnings("deprecation")
public class ComponentMine implements IComponent {
    @Override
    public String getName() {
        return ComponentC.ComponentMine.ComponentName;
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        if(ComponentC.ComponentMine.Start_Fragment.equals(actionName)){
            CC.sendCCResult(cc.getCallId(), CCResult.success(ComponentC.ComponentMine.MineFragment_Value, new MineFragment()));
        }
        return false;
    }
}
