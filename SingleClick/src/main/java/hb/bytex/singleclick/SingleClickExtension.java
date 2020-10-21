package hb.bytex.singleclick;

import com.ss.android.ugc.bytex.common.BaseExtension;

public class SingleClickExtension extends BaseExtension {

    @Override
    public String getName() {
        //在gradle中写插件配置dsl时的名字
        //the name of the plugin to configure dsl in gradle
        return "SingleClick";
    }
}
