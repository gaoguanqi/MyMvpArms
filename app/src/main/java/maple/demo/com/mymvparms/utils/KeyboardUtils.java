package maple.demo.com.mymvparms.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Gaoguanqi on 2018/3/30.
 */

public class KeyboardUtils {
    /**
     * 隐藏软键盘
     */
    public static void hideInput(Activity context) {
        if (context.getCurrentFocus() != null) {
            InputMethodManager systemService = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (systemService != null) {
                systemService.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
