package maple.demo.com.mymvparms.net.api;

import maple.demo.com.mymvparms.BuildConfig;

/**
 * Created by Gaoguanqi on 2018/3/28.
 */

public interface IApiConfig {
    /**
     * 服务器地址
     */
    String GAREN_SERVER_URL = BuildConfig.GAREN_SERVER_URL;
    /**
     * 请求成功状态码
     */
    int RequestSuccess = 200;

    /**
     * 基础地址
     */
    String APP_DOMAIN = "https://api.xiaoxinyong.com";
}
