package maple.demo.com.mymvparms.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.di.component.AppComponent;

import javax.inject.Inject;

import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.base.BaseLazyFragment;
import maple.demo.com.mymvparms.di.component.DaggerHomeComponent;
import maple.demo.com.mymvparms.di.module.HomeModule;
import maple.demo.com.mymvparms.mvp.contract.MainContract;
import maple.demo.com.mymvparms.mvp.presenter.MainPresenter;

/**
 * Created by Gaoguanqi on 2018/4/2.
 */

public class MainFragment extends BaseLazyFragment<MainPresenter> implements MainContract.View{
    @Inject
    MainPresenter mPresenter;
    public static Fragment getInstance() {
        return new MainFragment();
    }
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}
