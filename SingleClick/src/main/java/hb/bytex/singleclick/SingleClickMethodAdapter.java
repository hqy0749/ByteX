package hb.bytex.singleclick;

import com.android.ddmlib.Log;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;
import org.objectweb.asm.commons.AdviceAdapter;


public final class SingleClickMethodAdapter extends AdviceAdapter implements Opcodes {
    private static final String COST_ANNOTATION_DESC = "Lhb/bytex/singleclickUtil//XNoSingleClick;";
    private static final String COST_CLICKUTILS = "hb/bytex/singleclick/Util/ClickUtils";
    /**
     * 是否使用@XNoSingleClick标记
     */
    private boolean noSingleClick = false;

    private String className;

    private String methodName;

    private String desc;

    private boolean isStaticMethod;

    private Type[] argumentArrays;

    SingleClickMethodAdapter(int api, MethodVisitor mv, int access, String className, String methodName, String desc) {
        super(api, mv, access, methodName, desc);
        this.className = className;
        this.methodName = methodName;
        this.desc = desc;
        argumentArrays = Type.getArgumentTypes(desc);
        isStaticMethod = ((access & Opcodes.ACC_STATIC) != 0);
    }
//    public SingleClickMethodAdapter(MethodVisitor mv) {
//        super(Opcodes.ASM5, mv);
//    }


    @Override
    public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
        Log.e("xxxxxxxxxx","visitInsnAnnotation=desc="+desc);
        return super.visitInsnAnnotation(typeRef, typePath, desc, visible);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        Log.e("xxxxxxxxxx","visitAnnotation=desc="+desc);
        if (COST_ANNOTATION_DESC.equals(desc)) {
            noSingleClick = true;
            Log.e("xxxxxxxxxx","哈哈哈哈哈哈");
        }
        AnnotationVisitor defaultAv = super.visitAnnotation(desc, visible);
        return defaultAv;
    }


    @Override
    public void visitCode() {
        super.visitCode();

        //没有使用@NoSingleClick标记的onclick方法插入快速点击显示
        if (!noSingleClick) {
    /*        mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "hb/AopAsm/singleclick/ClickUtils",
                    "isFastDoubleClick", "(Landroid/view/View;)Z", false);
            mv.visitVarInsn(ISTORE, 2);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitVarInsn(ILOAD, 2);
            Label l2 = new Label();
            mv.visitJumpInsn(IFEQ, l2);
            mv.visitInsn(RETURN);
            mv.visitLabel(l2);*/

            mv.visitMethodInsn(INVOKESTATIC, COST_CLICKUTILS, "isQuickClick", "()Z", false);
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(31, l2);
            mv.visitInsn(RETURN);
            mv.visitLabel(l1);
        }
    }

}
