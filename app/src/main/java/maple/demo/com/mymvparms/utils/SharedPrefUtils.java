package maple.demo.com.mymvparms.utils;

import android.content.Context;
import android.content.SharedPreferences;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import maple.demo.com.mymvparms.app.AppController;

/**
 * @author Sunkist created at 16:17 2016/5/27
 * @description 分为用户的配置文件和全局的配置文件，用户的配置文件以userid为文件名，所以你懂的，只能登陆后才能那啥
 **/
public class SharedPrefUtils {

    /**
     * 保存在手机里面的文件名，退出登录后会被删除
     */
    public static String FILE_NAME = AppController.getInstance().getApplication().getPackageName();
    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 获取保存的Set<String>集合数据
     *
     * @param key
     * @return
     */
    public static Set<String> getStringSet(String key) {
        return SharedPreferencesCompat.getStringSet(AppController.getInstance().getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE), key, null);
    }

    /**
     * 保存Set<String>集合数据
     *
     * @param key
     * @param set
     */
    public static void putStringSet(String key, Set<String> set) {
        SharedPreferences.Editor editor = AppController.getInstance().getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        SharedPreferencesCompat.putStringSet(editor, key, set);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author Sunkist
     */
    private static class SharedPreferencesCompat {
        private static Method sApplyMethod;
        private static Method sGetStringSetMethod;
        private static Method sPutStringSetMethod;
        private static final String SEPERATOR = "|";

        static {
            try {
                Class<?>[] arrayOfClass = new Class[0];
                sApplyMethod = SharedPreferences.Editor.class.getMethod("apply", arrayOfClass);
            } catch (NoSuchMethodException localNoSuchMethodException) {
                sApplyMethod = null;
            }
            try {
                Class<?>[] arrayOfClass = new Class[]{String.class, Set.class};
                sGetStringSetMethod = SharedPreferences.class.getMethod("getStringSet", arrayOfClass);
                sPutStringSetMethod = SharedPreferences.Editor.class.getMethod("putStringSet", arrayOfClass);
            } catch (NoSuchMethodException localNoSuchMethodException) {
                sGetStringSetMethod = null;
                sPutStringSetMethod = null;
            }
        }

        public static void apply(SharedPreferences.Editor paramEditor) {
            if (sApplyMethod != null) {
                try {
                    Method localMethod = sApplyMethod;
                    Object[] arrayOfObject = new Object[0];
                    localMethod.invoke(paramEditor, arrayOfObject);
                    return;
                } catch (IllegalAccessException localIllegalAccessException) {
                } catch (InvocationTargetException localInvocationTargetException) {
                }
            }
            paramEditor.commit();
        }

        public static Set<String> getSets(SharedPreferences pref, String key, Set<String> set) {
            String val = pref.getString(key, null);
            if (val == null || val.equals("")) return set;
            String[] vals = val.split("\\" + SEPERATOR);
            HashSet<String> ret = new HashSet<String>(Arrays.asList(vals));
            return ret;
        }

        @SuppressWarnings("unchecked")
        public static Set<String> getStringSet(SharedPreferences pref, String key, Set<String> set) {
            Set<String> ret = null;
            if (sGetStringSetMethod != null) {
                try {
                    Method localMethod = sGetStringSetMethod;
                    Object[] arrayOfObject = new Object[]{key, set};
                    Object o = localMethod.invoke(pref, arrayOfObject);
                    if (o == null) return set;
                    ret = (Set<String>) o;
                    return ret;
                } catch (IllegalAccessException localIllegalAccessException) {
                    // ignore this, will to the final
                } catch (InvocationTargetException localInvocationTargetException) {
                    // ignore this, will to the final
                }
            }
            // if anything wrong, will be here
            ret = getSets(pref, key, set);
            return ret;
        }

        public static void putSets(SharedPreferences.Editor editor, String key, Set<String> set) {
            if (set == null) {
                editor.remove(key);
                return;
            }
            StringBuilder val = new StringBuilder();
            boolean first = true;
            for (String s : set) {
                //if (s.equals("")) continue;
                if (!first) {
                    val.append(SEPERATOR);
                } else {
                    first = false;
                }
                val.append(s);
            }
            editor.putString(key, val.toString());
        }

        public static void putStringSet(SharedPreferences.Editor editor, String key, Set<String> set) {
            if (sPutStringSetMethod != null) {
                try {
                    Method localMethod = sPutStringSetMethod;
                    Object[] arrayOfObject = new Object[]{key, set};
                    localMethod.invoke(editor, arrayOfObject);
                    return;
                } catch (IllegalAccessException localIllegalAccessException) {
                } catch (InvocationTargetException localInvocationTargetException) {
                }
            }
            putSets(editor, key, set);
        }
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public static String getString(String key, String value) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, value);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public static void putString(String key, String value) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    //    byFileName putBoolean
    public static void putBoolean(String key, boolean b) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, b);
        SharedPreferencesCompat.apply(editor);
    }
    //以UserID为文件名的用户私有文件，应该经行加密处理

    /**
     * @param key
     * @param defvalue
     * @return
     */
    public static boolean getBoolean(String key, boolean defvalue) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defvalue);
    }

    /**
     * @author Sunkist created at 16:31 2016/5/27
     **/
    public static void putStringByFileName(String userid, String key, String value) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(userid, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * @author Sunkist created at 16:31 2016/5/27
     **/
    public static String getStringByFileName(String userid, String key, String value) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(userid, Context.MODE_PRIVATE);
        return sp.getString(key, value);
    }

    /**
     * @author Sunkist created at 16:31 2016/5/27
     **/
    public static boolean getBooleanByFileName(String fileName, String key, boolean defValue) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * @author Sunkist created at 16:31 2016/5/27
     **/
    public static void putBooleanByFileName(String fileName, String key, boolean value) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.apply(editor);
    }
    //END

    /**
     * 清除所有数据
     */
    public static void clearDataByFileName(String fileName) {
        SharedPreferences sp = AppController.getInstance().getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }
}
