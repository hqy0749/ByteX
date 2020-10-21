package hb.bytex.singleclick;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.BaseContext;

import org.gradle.api.Project;

public class SingleClickContext extends BaseContext<SingleClickExtension> {
    public SingleClickContext(Project project, AppExtension android, SingleClickExtension extension) {
        super(project, android, extension);
    }
}
