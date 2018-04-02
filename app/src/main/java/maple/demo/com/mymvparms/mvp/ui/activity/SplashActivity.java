package maple.demo.com.mymvparms.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.base.BaseViewActivity;
import maple.demo.com.mymvparms.di.component.DaggerSplashComponent;
import maple.demo.com.mymvparms.di.module.SplashModule;
import maple.demo.com.mymvparms.mvp.contract.SplashContract;
import maple.demo.com.mymvparms.mvp.presenter.SplashPresenter;
import maple.demo.com.mymvparms.widget.CountDownView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SplashActivity extends BaseViewActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.cd_view)
    CountDownView countDownView;
    @Inject
    SplashPresenter mPersenter;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_splash; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPersenter.countDownView(countDownView);
    }

    @OnClick(R.id.btn_skip)
    public void onClick() {
        mPersenter.onSkip(countDownView);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(this, intent);
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
