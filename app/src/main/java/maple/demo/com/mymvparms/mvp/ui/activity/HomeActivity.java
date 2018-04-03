package maple.demo.com.mymvparms.mvp.ui.activity;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.base.BaseViewActivity;
import maple.demo.com.mymvparms.config.AppConstant;
import maple.demo.com.mymvparms.di.component.DaggerHomeComponent;
import maple.demo.com.mymvparms.di.module.HomeModule;
import maple.demo.com.mymvparms.mvp.contract.HomeContract;
import maple.demo.com.mymvparms.mvp.presenter.HomePresenter;
import maple.demo.com.mymvparms.mvp.ui.adapter.HomePagerAdapter;
import maple.demo.com.mymvparms.mvp.ui.fragment.MainFragment;
import maple.demo.com.mymvparms.mvp.ui.fragment.MineFragment;
import maple.demo.com.mymvparms.utils.SharedPrefUtils;
import maple.demo.com.mymvparms.widget.NoSlidingViewPaper;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeActivity extends BaseViewActivity<HomePresenter> implements HomeContract.View {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.viewPaper)
    NoSlidingViewPaper viewPager;
    @BindView(R.id.btottomnav_view)
    BottomNavigationView bottomNavigationView;
    @Inject
    HomePresenter mPresenter;
    @Inject
    HomePagerAdapter mPagerAdapter;

    private long lastBackPressedMillis;



    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_home; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        SharedPrefUtils.putBoolean(AppConstant.SaveInfoKey.HASLANCHER,false);

        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(0);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_main:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.home_mine:
                    viewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true)
                .navigationBarWithKitkatEnable(false)
                .navigationBarEnable(false)
                .titleBar(R.id.title)
                .init();
    }

    @Override
    protected boolean isShowBack() {
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @OnClick({R.id.iv_heard,R.id.tv_heard,R.id.rl_setting})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_heard:
            case R.id.tv_heard:
                showMessage("个人中心");
                break;
            case R.id.rl_setting:
                launchActivity(new Intent(this,SettingActivity.class));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (lastBackPressedMillis + 2000 > System.currentTimeMillis()) {
                moveTaskToBack(true);
                //ArmsUtils.killAll();
            } else {
                lastBackPressedMillis = System.currentTimeMillis();
                showMessage(getString(R.string.app_exit));
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public Activity getActivity() {
        return this;
    }
}
