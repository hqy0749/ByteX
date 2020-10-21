

package hb.bytex.singleclick.Util;




public final class ClickUtils {

    private static long FROZEN_TIME_MILLIS = 500L;
    private ClickUtils() {
        throw new UnsupportedOperationException("Do not need instantiate!");
    }

/*
    public static boolean isFastDoubleClick(View targetView) {
        Object object = targetView.getTag(R.id.asm_view_click_time_1q2w3e4r);
        if(object == null){
            targetView.setTag(R.id.asm_view_click_time_1q2w3e4r,System.currentTimeMillis());
            return false;
        }
        long preTime = (long)object;
        if (System.currentTimeMillis() - preTime <FROZEN_TIME_MILLIS) {
            Log.e("xxxxxxx","快速点击了");
            return true;
        }
        targetView.setTag(R.id.asm_view_click_time_1q2w3e4r,System.currentTimeMillis());
        return false;
    }*/



    public static void setFrozenTimeMillis(long frozenTimeMillis) {
        FROZEN_TIME_MILLIS = frozenTimeMillis;
    }



    private static long lastClickTime;

    /**
     * 是否快速点击`
     */
    public static boolean isQuickClick() {
        long time = System.currentTimeMillis();
        if (Math.abs(time - lastClickTime) < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
