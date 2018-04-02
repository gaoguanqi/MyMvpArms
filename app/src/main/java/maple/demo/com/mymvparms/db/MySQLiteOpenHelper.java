package maple.demo.com.mymvparms.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

import maple.demo.com.mymvparms.entity.DaoMaster;
import maple.demo.com.mymvparms.entity.UserEntityDao;

/**
 * MySQLiteOpenHelper是用来处理数据库升级的逻辑
 * 注意：这里的逻辑并不完善，每生成新的表管理类如{@link UserDao}
 * 就需要在此类方法{@link #onUpgrade}中加入相应的类
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, UserEntityDao.class);
    }
}
