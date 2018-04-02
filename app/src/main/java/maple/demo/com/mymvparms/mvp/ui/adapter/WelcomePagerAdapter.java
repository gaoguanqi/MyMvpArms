package maple.demo.com.mymvparms.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Gaoguanqi on 2018/3/30.
 */

public class WelcomePagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<View> mViews;

    public WelcomePagerAdapter( Context context,List<View> views) {
        this.mContext = context;
        this.mViews = views;
    }
    //当view销毁时移除
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    //添加view
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    //返回当前view的数量
    @Override
    public int getCount() {
        return mViews == null? 0 : mViews.size() ;
    }

    //判断当前的view是不是需要的对象
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
