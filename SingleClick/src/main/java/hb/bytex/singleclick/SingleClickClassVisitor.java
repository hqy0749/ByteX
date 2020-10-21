package hb.bytex.singleclick;

import com.android.ddmlib.Log;
import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import hb.bytex.singleclick.Util.FilterUtil;

public class SingleClickClassVisitor extends BaseClassVisitor {
    private SingleClickExtension extension;
    //是否命中相关类
    private boolean mIsHintClass = false;

    private String mSuperName;
    private String mClassName;
    private String[] mInterfaces;

    public SingleClickClassVisitor(SingleClickExtension extension) {
        this.extension = extension;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        mInterfaces = interfaces;
        mIsHintClass = FilterUtil.isMatchingClass(interfaces);
        for (String anInterface : interfaces) {
            Log.e("xxxxxxxxxx1", "interfaces=" + anInterface);
        }
        this.mSuperName = superName;
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        //命中相关类（实现OnClickListener的类）并且命中相关方法（onclick方法）
        if ((mIsHintClass && FilterUtil.isMatchingMethod(name, desc))
                || FilterUtil.isMatchingButterKnife(mSuperName, name, desc)
            /*|| FilterUtil.isMatchinglambda(name, desc)*/) {
            Log.e("xxxxxxxxxx2", "符合条件");
            return mv == null ? null : new SingleClickMethodAdapter(Opcodes.ASM5, mv, access, mClassName, name, desc);
        }
        {
            Log.e("xxxxxxxxxx2", "不符合条件");
            return mv;
        }

    }
}
