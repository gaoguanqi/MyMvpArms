package maple.demo.com.mymvparms.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.mvp.IPresenter;

/**
 * <p>懒加载的Fragment,主要用于状态栏、UI和数据的延迟加载
 * 如果子类需要懒加载view和data
 * 则重写{@link BaseLazyFragment#lazyLoadView()}
 * 则重写{@link BaseLazyFragment#lazyLoadData()}
 * 且这两个方法只会走一次
 * 如果不需要懒加载则继承{@link BaseViewFragment}
 * </p>
 *
 * @author Yang
 * @date 2017/12/17
 */
public abstract class BaseLazyFragment<P extends IPresenter> extends BaseViewFragment<P> {
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     */
    protected boolean mIsPrepare;
    /**
     * 是否加载过view
     */
    protected boolean mHasLoadedView;
    /**
     * 是否加载过数据
     */
    protected boolean mHasLoadedData;
    /**
     * 沉浸式以及bar的管理
     */
    protected ImmersionBar mImmersionBar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isLazyLoad()) {
            mIsPrepare = true;
        } else {
            if (isImmersionBarEnabled())
                initImmersionBar();
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (mIsVisible && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) mImmersionBar.destroy();
        this.mImmersionBar = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
            if (mIsPrepare && mIsVisible && isImmersionBarEnabled()) {
                initImmersionBar();
            }
            if (mIsPrepare && mIsVisible && !mHasLoadedView) {
                mHasLoadedView = true;
                lazyLoadView();
            }
            if (mIsPrepare && mIsVisible && !mHasLoadedData) {
                lazyLoadData();
            }
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).navigationBarEnable(false);
    }

    /**
     * 是否懒加载
     */
    protected boolean isLazyLoad() {
        return true;
    }

    /**
     * 是否在Fragment使用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 懒加载页面view
     */
    protected void lazyLoadView() {

    }

    /**
     * 懒加载页面网络数据
     */
    protected void lazyLoadData() {

    }

    /**
     * 页面不可见的时候回调
     */
    protected void onInvisible() {

    }

    /**
     * 页面可见的时候回调
     */
    protected void onVisible() {

    }
}
