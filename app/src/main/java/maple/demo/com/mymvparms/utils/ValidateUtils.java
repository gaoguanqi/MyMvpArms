package maple.demo.com.mymvparms.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 正则校验
 */
public class ValidateUtils {

    private ValidateUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * 验证邀请码
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isInvitationCode(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_INVITATION_CODE, input);
    }



}
