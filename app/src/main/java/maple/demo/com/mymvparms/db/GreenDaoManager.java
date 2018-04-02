package maple.demo.com.mymvparms.db;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

import maple.demo.com.mymvparms.BuildConfig;
import maple.demo.com.mymvparms.app.AppController;
import maple.demo.com.mymvparms.entity.DaoMaster;
import maple.demo.com.mymvparms.entity.DaoSession;

/**
 * GreenDaoManager是数据库管理类
 * 这里提供一些单例供全局使用
 * 但具体的表封装不在这里实现
 */
public class GreenDaoManager {
    private static final String DB_NAME = "greendao";
    private static GreenDaoManager mInstance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    private GreenDaoManager() {
        if (mInstance == null) {
            MigrationHelper.DEBUG = BuildConfig.DEBUG; //如果你想查看日志信息，请将DEBUG设置为true
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(AppController.getInstance().getContext(), DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
            Database db = helper.getWritableDb();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }
}
