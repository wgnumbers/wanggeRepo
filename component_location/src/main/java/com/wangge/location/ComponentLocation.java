package com.wangge.location;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.wangge.app.ComponentC;
import com.wangge.location.fragment.LocationFragment;

@SuppressWarnings("deprecation")
public class ComponentLocation implements IComponent {
    @Override
    public String getName() {
        return ComponentC.ComponentLocation.ComponentName;
    }

    @Override
    public boolean onCall(CC cc) {
        String actionName = cc.getActionName();
        if(ComponentC.ComponentLocation.Start_Fragment.equals(actionName)){
            CC.sendCCResult(cc.getCallId(), CCResult.success(ComponentC.ComponentLocation.LocationFragment_Value, new LocationFragment()));
        }
        return false;
    }


}
