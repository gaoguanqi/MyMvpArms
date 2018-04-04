package maple.demo.com.mymvparms.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.utils.LogUtils;

import maple.demo.com.mymvparms.R;

/**
 * Created by Gaoguanqi on 2018/4/4.
 */

public class LoadingButton extends RelativeLayout {

    private float width;
    private float height;

    private NoTouchRelativeLayout mRelativeLayout;
    private Button mButton;
    private ProgressBar mProgressBar;

    public LoadingButton(Context context) {
        this(context,null,0);
    }

    public LoadingButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if(!isInEditMode()){//解决可视化编辑器无法识别自定义控件的问题
            // 在构造函数中将Xml中定义的布局解析出来。
            View view =  LayoutInflater.from(context).inflate(R.layout.loading_button, this, true);
            mRelativeLayout = (NoTouchRelativeLayout)view.findViewById(R.id.relativeLayout);
            mButton = (Button)view.findViewById(R.id.button);
            mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        width = params.width;
        height = params.height;

        //获取在下面step4引用布局时在XML文件中定义的属性值
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.loadingButton);
        width = a.getDimension(R.styleable.loadingButton_btn_width,width);
        height = a.getDimension(R.styleable.loadingButton_btn_height,height);
        mButton.setText("确定");
        a.recycle();
        mButton.setEnabled(true);
        mButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        mButton.setText("");
                        mProgressBar.setVisibility(VISIBLE);
                        mButton.setEnabled(false);
                        break;
                }
                return false;
            }
        });
    }
}
