package maple.demo.com.mymvparms.config;


public class AppConstant {


    /**
     * 需要以键值对持久化到SharePerference文件中的Key常量值
     */
    public static class SaveInfoKey {
        public static final String HASLOGIN = "hasLogin";
        public static final String TOKEN = "token";
        public static final String PHONE = "phone";
        public static final String USER_ID = "userid";

        public static final String HASLANCHER = "hasLancher";
    }

    /**
     * Bundle中需要作为Key传递的常量EXTRA开头
     */
    public static class Global {
        /**
         * 进入WEBVIEW界面必须要传入的参数
         */
        public static final String EXTRA_TO_WEB_URL = "url";
        public static final String EXTRA_SHARE_OBJ = "shareObj";

        /**
         * 照相的requestCode;
         */
        public static final int CAMERA_WITH_DATA = 0x01;
    }

    /**
     * 存放参与API请求的常量
     */
    public static class ApiParams {

        /***
         * 请求短信
         */
        public static String SMS_TYPE_REGISTER = "register";
        public static String SMS_TYPE_RESETPAW = "find";

    }
}
