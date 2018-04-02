package maple.demo.com.mymvparms.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 获取全局数据
 */
public class AppController {

    private static AppController sInstance = null;
    private Application mApplication = null;
    private Context mContext = null;

    public void init(Application application, Context context) {
        this.mApplication = application;
        this.mContext = context;
    }

    /**
     * 获取实例
     *
     * @return AppController的实例
     */
    public static AppController getInstance() {
        if (sInstance == null) {
            synchronized (AppController.class) {
                if (sInstance == null) {
                    sInstance = new AppController();
                }
            }
        }
        return sInstance;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public Context getContext() {
        return mContext;
    }

    public Application getApplication() {
        return mApplication;
    }

}