package maple.demo.com.mymvparms.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jess.arms.base.App;
import com.jess.arms.base.delegate.AppDelegate;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.Preconditions;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;


public class SampleApplication extends TinkerApplication implements App{
    private AppLifecycles mAppDelegate;

    public SampleApplication() {
        /**
         * 参数1：tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
         * 参数2：delegateClassName Application代理类 这里填写你自定义的ApplicationLike
         * 参数3：loaderClassName Tinker的加载器，使用默认即可
         * 参数4：tinkerLoadVerifyFlag 加载dex或者lib是否验证md5，默认为false
         */
        super(ShareConstants.TINKER_ENABLE_ALL, "maple.demo.com.mymvparms.app.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null)
            this.mAppDelegate = new AppDelegate(base);
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppDelegate != null)
            this.mAppDelegate.onCreate(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null)
            this.mAppDelegate.onTerminate(this);
    }

    /**
     * 将 {@link AppComponent} 返回出去,供其它地方使用,{@link AppComponent} 中声明的方法所返回的实例
     * 在 {@link #getAppComponent()}拿到对象后都可以直接使用
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(mAppDelegate, "%s cannot be null", AppDelegate.class.getName());
        Preconditions.checkState(mAppDelegate instanceof App, "%s must be implements %s", AppDelegate.class.getName(), App.class.getName());
        return ((App) mAppDelegate).getAppComponent();
    }
}