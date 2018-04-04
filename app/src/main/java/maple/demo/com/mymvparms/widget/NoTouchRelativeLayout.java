package maple.demo.com.mymvparms.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Gaoguanqi on 2018/4/4.
 */

public class NoTouchRelativeLayout extends RelativeLayout {
    public NoTouchRelativeLayout(Context context) {
        super(context);
    }

    public NoTouchRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoTouchRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
