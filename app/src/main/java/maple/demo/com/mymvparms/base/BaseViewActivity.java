package maple.demo.com.mymvparms.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.base.delegate.IActivity;
import com.jess.arms.integration.cache.Cache;
import com.jess.arms.integration.cache.CacheType;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.manager.status.StatusLayoutManager;
import maple.demo.com.mymvparms.manager.toolbar.ToolbarManager;
import maple.demo.com.mymvparms.utils.KeyboardUtils;
import maple.demo.com.mymvparms.widget.MaterialProgress;

import static com.jess.arms.utils.ThirdViewUtil.convertAutoView;

/**
 * <p>该类基于UI封装
 * 主要功能是显示网络错误页和封装Toolbar
 * {@link LoginActivity} 可参考的使用方式
 * </p>
 * <p>
 * 注意：如果你的页面需要错误页、加载页、空页面等，请重写{@link BaseViewActivity#initStatusView}
 * 否则请重写{@link BaseViewActivity#initView}
 * </p>
 *
 * @author Yang
 * @date 2017/12/12
 */
public abstract class BaseViewActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, ActivityLifecycleable {
    private Unbinder mUnbinder;
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();
    private Cache<String, Object> mCache;
    @Inject
    protected P mPresenter;
    protected ImmersionBar mImmersionBar;
    protected StatusLayoutManager mStatusLayoutManager;
    protected ToolbarManager mToolbarManager;

    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            mCache = ArmsUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = convertAutoView(name, context, attrs);
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutResID = initView(savedInstanceState);
            if (layoutResID != 0) {//如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
                setContentView(layoutResID);
            }
            int contentLayoutResId = initStatusView(savedInstanceState);
            if (contentLayoutResId != 0) {
                setContentView(R.layout.layout_base);
                initStatusLayout(contentLayoutResId);
                if (isNeedKeepToolbar()) {
                    ViewStub viewStubTitle = findViewById(R.id.view_stub_title);
                    if (viewStubTitle != null) {
                        viewStubTitle.inflate();
                    }
                }
                LinearLayout mainLl = findViewById(R.id.ll_base);
                mainLl.addView(mStatusLayoutManager.getRootLayout());
            }
            mUnbinder = ButterKnife.bind(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initToolbar();
        if (isNeedImmersionBar()) {
            initImmersionBar();
        }
        initData(savedInstanceState);
    }

    /**
     * 初始化带有错误页和空页面等布局
     *
     * @param layoutResID 内容布局id
     */
    protected void initStatusLayout(int layoutResID) {
        mStatusLayoutManager = StatusLayoutManager.newBuilder(this)
                .contentView(layoutResID)
                .emptyDataView(R.layout.layout_empty)
                .emptyDataRetryViewId(R.id.ll_empty)
                .errorView(R.layout.layout_error)
                .errorRetryViewId(R.id.ll_error)
                .netWorkErrorView(R.layout.layout_timeout)
                .netWorkErrorRetryViewId(R.id.ll_timeout)
                .loadingView(R.layout.layout_loading)
                .onRetryListener(() -> {
                    MaterialProgress materialProgress = findViewById(R.id.loading_progress);
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
     * 初始化Toolbar
     */
    protected void initToolbar() {
        View toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        setSupportActionBar((android.support.v7.widget.Toolbar) toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        if (!TextUtils.isEmpty(getTitle())) {
            findViewById(R.id.toolbar_title).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.toolbar_title)).setText(getTitle());
        }
        if (findViewById(R.id.toolbar_back) != null) {
            findViewById(R.id.toolbar_back).setVisibility(View.VISIBLE);
            findViewById(R.id.toolbar_back).setOnClickListener(v -> {
                //TODO:这里隐藏软键盘
                KeyboardUtils.hideInput(this);
                onBackClick();
            });
        }
        mToolbarManager = ToolbarManager.create(this, toolbar);
    }

    /**
     * 初始化沉浸式状态栏
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度
        mImmersionBar.fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .keyboardEnable(true)
                .navigationBarWithKitkatEnable(false)
                .navigationBarEnable(false)
                .statusBarColor(R.color.status_color)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

    /**
     * 错误页和空页面点击重试
     */
    protected void onStatusViewRetry() {

    }

    /**
     * 返回键点击,默认为onBackPressed()
     */
    protected void onBackClick() {
        onBackPressed();
    }

    /**
     * 初始化普通布局,注意与{@link BaseViewActivity#initStatusView}选择重写一个即可
     */
    @Override
    public int initView(Bundle savedInstanceState) {
        return 0;
    }

    /**
     * 初始化带有错误页和加载页的布局,注意与{@link BaseViewActivity#initView}选择重写一个即可
     */
    protected int initStatusView(Bundle savedInstanceState) {
        return 0;
    }

    /**
     * 是否需要在错误页和加载页保留Toolbar,默认为保留(true)
     */
    protected boolean isNeedKeepToolbar() {
        return true;
    }

    /**
     * 是否使用沉浸式状态栏,默认为使用(true)
     */
    protected boolean isNeedImmersionBar() {
        return true;
    }

    /**
     * 是否使用eventBus,默认为使用(true)
     */
    @Override
    public boolean useEventBus() {
        return true;
    }

    /**
     * 这个Activity是否会使用Fragment,框架会根据这个属性判断是否注册{@link android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回false,那意味着这个Activity不需要绑定Fragment,那你再在这个Activity中绑定继承于 {@link com.jess.arms.base.BaseFragment} 的Fragment将不起任何作用
     */
    @Override
    public boolean useFragment() {
        return true;
    }
}
