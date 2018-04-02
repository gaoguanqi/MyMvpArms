package maple.demo.com.mymvparms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import maple.demo.com.mymvparms.base.BaseViewActivity;
import maple.demo.com.mymvparms.di.component.DaggerLoginComponent;
import maple.demo.com.mymvparms.di.module.LoginModule;
import maple.demo.com.mymvparms.mvp.contract.LoginContract;
import maple.demo.com.mymvparms.mvp.presenter.LoginPresenter;

import maple.demo.com.mymvparms.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LoginActivity extends BaseViewActivity<LoginPresenter> implements LoginContract.View {


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {

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
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


}
