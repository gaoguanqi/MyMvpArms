package maple.demo.com.mymvparms.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * 自定义ApplicationLike
 * 注意：tinker需要你开启MultiDex,你需要在dependencies中进行配置compile "com.android.support:multidex:1.0.1"
 * 才可以使用MultiDex.install方法； SampleApplicationLike这个类是Application的代理类，以前所有在Application的
 * 实现必须要全部拷贝到这里，在onCreate方法调用SDK的初始化方法，在onBaseContextAttached中调用Beta.installTinker(this);。
 */
public class SampleApplicationLike extends DefaultApplicationLike {
    public static final String TAG = "Tinker.SampleApplicationLike";

    public SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        AppController.getInstance().init(getApplication(), getApplication().getApplicationContext());
    }

    /**
     * 这里会在 {@link SampleApplicationLike#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }
}