package hb.bytex.singleclick;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.CommonPlugin;
import com.ss.android.ugc.bytex.common.TransformConfiguration;
import com.ss.android.ugc.bytex.common.visitor.ClassVisitorChain;

import org.gradle.api.Project;

import javax.annotation.Nonnull;

//支持注解方式注册
//annotation registration is supported
//@PluginConfig("bytex.sourcefile")
public class SingleClickPlugin extends CommonPlugin<SingleClickExtension, SingleClickContext> {
    @Override
    protected SingleClickContext getContext(Project project, AppExtension android, SingleClickExtension extension) {
        return new SingleClickContext(project, android, extension);
    }

    @Override
    public boolean transform(@Nonnull String relativePath, @Nonnull ClassVisitorChain chain) {
        //我们需要修改字节码，所以需要注册一个ClassVisitor
        //We need to modify the bytecode, so we need to register a ClassVisitor
        chain.connect(new SingleClickClassVisitor(extension));
        return super.transform(relativePath, chain);
    }

    @Nonnull
    @Override
    public TransformConfiguration transformConfiguration() {
        return new TransformConfiguration() {
            @Override
            public boolean isIncremental() {
                //插件默认是增量的，如果插件不支持增量，需要返回false
                //The plugin is incremental by default.It should return false if incremental is not supported by the plugin
                return true;
            }
        };
    }
}
