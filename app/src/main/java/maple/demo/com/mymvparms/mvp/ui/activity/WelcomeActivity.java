package maple.demo.com.mymvparms.mvp.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.BarParams;
import com.gyf.barlibrary.ImmersionBar;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;
    @BindView(R.id.btn_start)
    Button btnStart;


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
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar).transparentBar().init();
        if (ImmersionBar.hasNavigationBar(this)) {
            BarParams barParams = mImmersionBar.getBarParams();
            if (barParams.fullScreen)
                mImmersionBar.fullScreen(false).navigationBarColor(R.color.black).init();
            else
                mImmersionBar.fullScreen(true).transparentNavigationBar().init();
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        View oneView = inflater.inflate(R.layout.layout_welcome_one,null);
        View twoView = inflater.inflate(R.layout.layout_welcome_two,null);
        View threeView = inflater.inflate(R.layout.layout_welcome_three,null);
        views.add(oneView);
        views.add(twoView);
        views.add(threeView);
        mAdapter = new WelcomePagerAdapter(this,views);
        viewPager.setAdapter(mAdapter);
        btnStart = threeView.findViewById(R.id.btn_start);
        btnStart.setOnClickListener(v->{
            ArmsUtils.startActivity(new Intent(WelcomeActivity.this,HomeActivity.class));
            WelcomeActivity.this.finish();
        });
    }
}
