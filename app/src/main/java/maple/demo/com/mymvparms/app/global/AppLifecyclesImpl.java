package maple.demo.com.mymvparms.app.global;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.meituan.android.walle.WalleChannelReader;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;

import butterknife.ButterKnife;
import maple.demo.com.mymvparms.BuildConfig;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.app.AppController;
import timber.log.Timber;

/**
 * Created by Gaoguanqi on 2018/3/30.
 */

public class AppLifecyclesImpl  implements AppLifecycles {

    @Override
    public void attachBaseContext(Context base) {
//          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        if (BuildConfig.DEBUG) {//Timber初始化
            //Timber 是一个日志框架容器,外部使用统一的Api,内部可以动态的切换成任何日志框架(打印策略)进行日志打印
            //并且支持添加多个日志框架(打印策略),做到外部调用一次 Api,内部却可以做到同时使用多个策略
            //比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器
            Timber.plant(new Timber.DebugTree());
            // 如果你想将框架切换为 Logger 来打印日志,请使用下面的代码,如想切换为其他日志框架请根据下面的方式扩展
//                    Logger.addLogAdapter(new AndroidLogAdapter());
//                    Timber.plant(new Timber.DebugTree() {
//                        @Override
//                        protected void log(int priority, String tag, String message, Throwable t) {
//                            Logger.log(priority, tag, message, t);
//                        }
//                    });
            ButterKnife.setDebug(true);
        }
        //leakCanary内存泄露检查
        ArmsUtils.obtainAppComponentFromContext(application).extras().put(RefWatcher.class.getName(), BuildConfig.DEBUG ? LeakCanary.install(application) : RefWatcher.DISABLED);
        //扩展 AppManager 的远程遥控功能
        ArmsUtils.obtainAppComponentFromContext(application).appManager().setHandleListener((appManager, message) -> {
            switch (message.what) {
                //case 0:
                //do something ...
                //   break;
            }
        });
        //Usage:
        //Message msg = new Message();
        //msg.what = 0;
        //AppManager.post(msg); like EventBus
        initBugly(application);
    }

    private void initBugly(Application application) {
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = true;
        Beta.autoDownloadOnWifi = true;
        Beta.largeIconId = R.mipmap.ic_launcher;
        Beta.smallIconId = R.mipmap.ic_launcher;
        Beta.defaultBannerId = R.drawable.icon_default_loading;
        Beta.storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //已经确认过的弹窗在APP下次启动自动检查更新时会再次显示;
        Beta.showInterruptedStrategy = true;
       // Beta.canShowUpgradeActs.add(MainActivity.class);
       // Beta.canShowUpgradeActs.add(AboutUsActivity.class);
        BuglyStrategy strategy = new BuglyStrategy();
        // 获取当前进程名
        String processName = AppController.getProcessName(android.os.Process.myPid());
        strategy.setUploadProcess(processName == null || processName.equals(application.getPackageName()));
        //设置app渠道号
        String channel = WalleChannelReader.getChannel(application);
        strategy.setAppChannel(channel);
        Bugly.init(application, BuildConfig.BUGLY_APPID, BuildConfig.DEBUG, strategy);
    }

    @Override
    public void onTerminate(Application application) {

    }
}
