package maple.demo.com.mymvparms.utils;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/13
 *     desc  : 正则相关常量
 *     https://github.com/Blankj/AndroidUtilCode/blob/master/utilcode/src/main/java/com/blankj/utilcode/constant/RegexConstants.java
 * </pre>
 */

public class RegexConstants {


    /**
     * 正则：邀请码  前8位数字， 后2位字母
     */
    //public static final String REGEX_INVITATION_CODE    = "^[0-9]{8}[A-Za-z]{2}$";

    /**
     * 正则：邀请码  前8位数字， 后2位字母  或者  8位数字
     */
    public static final String REGEX_INVITATION_CODE    = "(^[0-9]{8}[A-Za-z]{2}$)|(^[0-9]{8})";



}
