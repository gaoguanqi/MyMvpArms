package maple.demo.com.mymvparms.manager.toolbar;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import maple.demo.com.mymvparms.R;


/**
 * <p>Toolbar相关设置类</p>
 *
 * @author Yang
 * @date 2018/1/8
 */
public class ToolbarManager {
    private Context context;
    private View toolbar;
    private ImageView ivLeftImage;
    private TextView tvLeftText;
    private TextView tvTitle;
    private TextView tvRightText;
    private ImageView ivRightImage;

    private ToolbarManager(Context context, View toolbar) {
        this.context = context;
        this.toolbar = toolbar;
        tvTitle = toolbar.findViewById(R.id.toolbar_title);
        ivLeftImage = toolbar.findViewById(R.id.toolbar_back);
        tvLeftText = toolbar.findViewById(R.id.toolbar_left_text);
        ivRightImage = toolbar.findViewById(R.id.toolbar_right_image);
        tvRightText = toolbar.findViewById(R.id.toolbar_right_text);
    }

    public static ToolbarManager create(Context context, View toolbar) {
        return new ToolbarManager(context, toolbar);
    }

    public ToolbarManager setBackground(@DrawableRes int backgroundDrawable) {
        if (backgroundDrawable != 0) {
            toolbar.setBackground(ContextCompat.getDrawable(context, backgroundDrawable));
        }
        return this;
    }

    public ToolbarManager setBackgroundColor(@ColorRes int backgroundColor) {
        if (backgroundColor != 0) {
            toolbar.setBackgroundColor(context.getResources().getColor(backgroundColor));
        }
        return this;
    }

    public ToolbarManager setBackgroundRes(@DrawableRes int backgroundRes) {
        if (backgroundRes != 0) {
            toolbar.setBackgroundResource(backgroundRes);
        }
        return this;
    }

    public ToolbarManager setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        return this;
    }

    public ToolbarManager setTitleId(@StringRes int titleId) {
        if (titleId != 0) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(context.getString(titleId));
        }
        return this;
    }

    public ToolbarManager setTitleColor(@ColorRes int titleColor) {
        if (titleColor != 0) {
            tvTitle.setTextColor(context.getResources().getColor(titleColor));
        }
        return this;
    }

    public ToolbarManager setLeftText(String leftText) {
        if (!TextUtils.isEmpty(leftText)) {
            tvLeftText.setVisibility(View.VISIBLE);
            tvLeftText.setText(leftText);
        }
        return this;
    }

    public ToolbarManager setLeftTextColor(@ColorRes int leftTextColor) {
        if (leftTextColor != 0) {
            tvLeftText.setTextColor(context.getResources().getColor(leftTextColor));
        }
        return this;
    }

    public ToolbarManager setLeftImage(@DrawableRes int leftImage) {
        if (leftImage != 0) {
            ivLeftImage.setVisibility(View.VISIBLE);
            ivLeftImage.setImageResource(leftImage);
        }
        return this;
    }

    public ToolbarManager setLeftDrawable(@DrawableRes int leftDrawable) {
        if (leftDrawable != 0) {
            ivLeftImage.setVisibility(View.VISIBLE);
            ivLeftImage.setImageDrawable(ContextCompat.getDrawable(context, leftDrawable));
        }
        return this;
    }

    public ToolbarManager setRightText(String rightText) {
        if (!TextUtils.isEmpty(rightText)) {
            tvRightText.setVisibility(View.VISIBLE);
            tvRightText.setText(rightText);
        }
        return this;
    }

    public ToolbarManager setRightTextColor(@ColorRes int rightTextColor) {
        if (rightTextColor != 0) {
            tvRightText.setTextColor(context.getResources().getColor(rightTextColor));
        }
        return this;
    }

    public ToolbarManager setRightImage(@DrawableRes int rightImage) {
        if (rightImage != 0) {
            ivRightImage.setVisibility(View.VISIBLE);
            ivRightImage.setImageResource(rightImage);
        }
        return this;
    }

    public ToolbarManager setRightDrawable(@DrawableRes int rightDrawable) {
        if (rightDrawable != 0) {
            ivRightImage.setVisibility(View.VISIBLE);
            ivRightImage.setImageDrawable(ContextCompat.getDrawable(context, rightDrawable));
        }
        return this;
    }
}
