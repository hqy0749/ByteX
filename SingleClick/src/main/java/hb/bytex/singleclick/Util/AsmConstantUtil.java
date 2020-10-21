package hb.bytex.singleclick.Util;


import org.objectweb.asm.Opcodes;

/**
 * TODO 描述
 *
 * @author hqy
 * @data 2020/8/22
 */
public class AsmConstantUtil implements Opcodes {
    public static int getInt(Object value) {
        if (value == null) {
            return ICONST_0;
        }
        if (value instanceof Integer) {
            return getInt(Integer.parseInt(value.toString()));
        }
        return ICONST_0;
    }

    public static int getInt(int value) {
        switch (value) {
            case 1:
                return ICONST_1;
            case 2:
                return ICONST_2;
            case 3:
                return ICONST_3;
            case 4:
                return ICONST_4;
            case 5:
                return ICONST_5;
        }
        return ICONST_0;
    }
}
