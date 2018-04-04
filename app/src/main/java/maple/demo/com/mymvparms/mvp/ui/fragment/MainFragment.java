package maple.demo.com.mymvparms.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.app.AppController;
import maple.demo.com.mymvparms.base.BaseLazyFragment;
import maple.demo.com.mymvparms.base.BaseViewFragment;
import maple.demo.com.mymvparms.di.component.DaggerHomeComponent;
import maple.demo.com.mymvparms.di.component.DaggerMainComponent;
import maple.demo.com.mymvparms.di.module.HomeModule;
import maple.demo.com.mymvparms.di.module.MainModule;
import maple.demo.com.mymvparms.entity.ShileEntity;
import maple.demo.com.mymvparms.mvp.contract.MainContract;
import maple.demo.com.mymvparms.mvp.presenter.MainPresenter;
import maple.demo.com.mymvparms.mvp.ui.adapter.main.BannerPagerAdapter;
import maple.demo.com.mymvparms.mvp.ui.adapter.main.MainAdapter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by Gaoguanqi on 2018/4/2.
 */

public class MainFragment extends BaseViewFragment<MainPresenter> implements MainContract.View {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    MainPresenter mPresenter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    private MainAdapter mainAdapter;

    public static Fragment getInstance() {
        return new MainFragment();
    }

    @Override
    public View initStatusView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected boolean isNeedKeepToolbar() {
        return false;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        swipeRefreshLayout.setColorSchemeColors(ArmsUtils.getColor(getActivity(),R.color.colorAccent));
        mStatusLayoutManager.showContent();
        recyclerView.setLayoutManager(linearLayoutManager);
        mainAdapter = new MainAdapter(getActivity());
        recyclerView.setAdapter(mainAdapter);
        mPresenter.requestData("2","1");
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
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        getActivity().finish();
    }

    @Override
    public void requestDataSucess(List<ShileEntity> data) {
        mainAdapter.setListData(data);
        List<String> mHeaderList = new ArrayList<>();
        mHeaderList.add("https://www.baidu.com/img/bd_logo1.png?where=super");
        mHeaderList.add("https://www.baidu.com/img/bd_logo1.png?where=super");
        mHeaderList.add("https://www.baidu.com/img/bd_logo1.png?where=super");
        mHeaderList.add("https://www.baidu.com/img/bd_logo1.png?where=super");
        mHeaderList.add("https://www.baidu.com/img/bd_logo1.png?where=super");

        mainAdapter.setHeaderData(mHeaderList);
        String footerData = data.size()+"";
        mainAdapter.setFooterData(footerData);
    }
}
