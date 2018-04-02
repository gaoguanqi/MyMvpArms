package maple.demo.com.mymvparms.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.base.BaseViewActivity;
import maple.demo.com.mymvparms.di.component.DaggerHomeComponent;
import maple.demo.com.mymvparms.di.module.HomeModule;
import maple.demo.com.mymvparms.mvp.contract.HomeContract;
import maple.demo.com.mymvparms.mvp.presenter.HomePresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeActivity extends BaseViewActivity<HomePresenter> implements HomeContract.View {

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
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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


}
