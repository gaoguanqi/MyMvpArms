package maple.demo.com.mymvparms.helper.observer;

import android.app.Activity;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import io.reactivex.disposables.Disposable;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.widget.dialog.LoadingDialog;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * <p>带进度条的异步线程观察者
 * 注意此观察者不能够用于网络请求
 * 只是处理作为线程的异步事件
 * 网络请求请使用{@link ProgressObserver}
 * 不需要进度条请使用{@link ErrorHandleSubscriber}
 * </p>
 *
 * @author Yang
 * @date 2017/12/17
 */

public abstract class BackgroundObserver<T> extends ErrorHandleSubscriber<T> {
    private Activity activity;
    private LoadingDialog loadingDialog;

    public BackgroundObserver(Activity activity, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        this.activity = activity;
        loadingDialog = new LoadingDialog(activity);
        loadingDialog.setMsg(activity.getString(R.string.loading));
        loadingDialog.setNotCancel();//设置dialog不自动消失
        loadingDialog.show();
    }

    public BackgroundObserver(Activity activity, RxErrorHandler rxErrorHandler, String loadingText) {
        super(rxErrorHandler);
        this.activity = activity;
        loadingDialog = new LoadingDialog(activity);
        if (TextUtils.isEmpty(loadingText)) {
            loadingDialog.setMsg(activity.getString(R.string.loading));
        } else {
            loadingDialog.setMsg(loadingText);
        }
        loadingDialog.setNotCancel();//设置dialog不自动消失
        loadingDialog.show();
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        dismissProgress();
        onSuccess(response);
    }

    private void dismissProgress() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        Logger.e(e.getMessage());
        dismissProgress();
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);
}
