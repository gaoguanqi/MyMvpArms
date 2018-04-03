package maple.demo.com.mymvparms.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import javax.inject.Inject;

import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.base.BaseLazyFragment;
import maple.demo.com.mymvparms.mvp.contract.MineContract;
import maple.demo.com.mymvparms.mvp.presenter.MinePresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by Gaoguanqi on 2018/4/2.
 */

public class MineFragment extends BaseLazyFragment<MinePresenter> implements MineContract.View{

    @Inject
    MinePresenter mPresenter;

    public static Fragment getInstance() {
        return new MineFragment();
    }
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
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
        checkNotNull(message);
        ArmsUtils.snackbarTextWithLong(message);
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}
