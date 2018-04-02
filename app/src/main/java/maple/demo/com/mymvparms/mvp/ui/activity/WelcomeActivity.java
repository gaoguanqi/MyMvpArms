package maple.demo.com.mymvparms.mvp.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.base.BaseViewActivity;
import maple.demo.com.mymvparms.mvp.ui.adapter.WelcomePagerAdapter;

public class WelcomeActivity extends BaseViewActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;


    private LayoutInflater inflater;
    private WelcomePagerAdapter mAdapter;
    private List<View> views;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_welcome;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.layout_welcome_one,null));
        views.add(inflater.inflate(R.layout.layout_welcome_two,null));
        views.add(inflater.inflate(R.layout.layout_welcome_three,null));
        mAdapter = new WelcomePagerAdapter(this,views);
        viewPager.setAdapter(mAdapter);
    }

    @OnClick(R.id.btn_start)
    public void onClick() {
        ArmsUtils.startActivity(new Intent(this,HomeActivity.class));
        this.finish();
    }
}
