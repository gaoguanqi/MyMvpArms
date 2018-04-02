package maple.demo.com.mymvparms.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.manager.status.StatusLayoutManager;
import maple.demo.com.mymvparms.manager.toolbar.ToolbarManager;
import maple.demo.com.mymvparms.widget.MaterialProgress;

/**
 * <p>该类基于UI封装
 * 主要功能是显示网络错误页和封装Toolbar
 * {@link HomePageFragment} 可参考的使用方式
 * </p>
 * <p>
 * 注意：如果你的页面需要错误页、加载页、空页面等，请重写{@link BaseViewFragment#initStatusView}
 * 否则请重写{@link BaseViewFragment#initView}
 * </p>
 *
 * @author Yang
 * @date 2017/12/12
 */
public abstract class BaseViewFragment<P extends IPresenter> extends BaseFragment<P> {

    protected StatusLayoutManager mStatusLayoutManager;
    protected ToolbarManager mToolbarManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = initStatusView(inflater, container, savedInstanceState);
        if (contentView != null) {
            initStatusLayout(contentView);
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_base, container, false);
            if (isNeedKeepToolbar()) {
                ViewStub viewStubTitle = rootView.findViewById(R.id.view_stub_title);
                if (viewStubTitle != null) {
                    viewStubTitle.inflate();
                }
            }
            rootView.addView(mStatusLayoutManager.getRootLayout());
            initToolbar(rootView);
            return rootView;
        } else {
            View view = initView(inflater, container, savedInstanceState);
            initToolbar(view);
            return view;
        }
    }

    /**
     * 初始化Toolbar
     */
    protected void initToolbar(View rootView){
        View toolbar = rootView.findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        mToolbarManager = ToolbarManager.create(getActivity(), toolbar);
    }

    /**
     * 初始化带有错误页和空页面等布局
     *
     * @param contentView 内容布局
     */
    protected void initStatusLayout(View contentView) {
        mStatusLayoutManager = StatusLayoutManager.newBuilder(getActivity())
                .contentInflateView(contentView)
                .emptyDataView(R.layout.layout_empty)
                .emptyDataRetryViewId(R.id.ll_empty)
                .errorView(R.layout.layout_error)
                .errorRetryViewId(R.id.ll_error)
                .netWorkErrorView(R.layout.layout_timeout)
                .netWorkErrorRetryViewId(R.id.ll_timeout)
                .loadingView(R.layout.layout_loading)
                .onRetryListener(() -> {
                    MaterialProgress materialProgress = getActivity().findViewById(R.id.loading_progress);
                    if (materialProgress != null) {
                        materialProgress.reset();
                    }
                    mStatusLayoutManager.showLoading();
                    Observable.timer(1, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .compose(RxLifecycleUtils.bindToLifecycle(this))
                            .subscribe(aLong -> onStatusViewRetry());
                }).build();
    }

    /**
     * 初始化普通布局,注意与{@link BaseViewFragment#initStatusView}选择重写一个即可
     */
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    /**
     * 初始化带有错误页和加载页的布局,注意与{@link BaseViewFragment#initView}选择重写一个即可
     */
    protected View initStatusView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    /**
     * 是否需要在错误页和加载页保留Toolbar,默认为保留(true)
     */
    protected boolean isNeedKeepToolbar() {
        return true;
    }

    /**
     * 错误页和空页面点击重试
     */
    protected void onStatusViewRetry() {

    }
}
