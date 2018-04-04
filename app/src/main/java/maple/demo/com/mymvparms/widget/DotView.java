package maple.demo.com.mymvparms.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import maple.demo.com.mymvparms.R;

/**
 * Created by Gaoguanqi on 2018/4/4.
 */

public class DotView extends View {
    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 圆半径
     */
    private float mRadius = 50;

    /**
     * 圆点颜色
     */
    private int dotColor = Color.parseColor("#000000");;

    public DotView(Context context) {
        this(context,null,0);
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.dotView);
        mRadius = typedArray.getDimension(R.styleable.dotView_dot_radius,mRadius);
        dotColor = typedArray.getColor(R.styleable.dotView_dot_color,dotColor);
        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true); //抗锯齿
        mPaint.setColor(dotColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawCircle(width / 2, height / 2,  mRadius, mPaint);
    }
}
