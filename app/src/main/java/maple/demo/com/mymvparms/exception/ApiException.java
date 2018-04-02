package maple.demo.com.mymvparms.exception;

/**
 * 自定义异常
 */
public class ApiException extends RuntimeException {

    public ApiException() {

    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }
}

