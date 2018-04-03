package maple.demo.com.mymvparms.helper.observer;

import android.app.Activity;
import android.text.TextUtils;

import com.jess.arms.utils.ArmsUtils;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.Disposable;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.entity.ResultEntity;
import maple.demo.com.mymvparms.widget.dialog.LoadingDialog;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * <p>带进度条的网络请求观察者
 * 注意此观察者只能够用于网络请求
 * 如果有需要线程来做的异步事件
 * 请使用{@link BackgroundObserver}
 * </p>
 *
 * @author Yang
 * @date 2017/12/17
 */

public abstract class ProgressObserver<T extends ResultEntity> extends ErrorHandleSubscriber<T> {
    private Activity activity;
    private LoadingDialog loadingDialog;
    private boolean hasLoading = true;

    protected ProgressObserver(RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        hasLoading = false;
    }

    protected ProgressObserver(Activity activity, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        this.activity = activity;
        loadingDialog = new LoadingDialog(activity);
        loadingDialog.setMsg(activity.getString(R.string.loading));
        loadingDialog.setNotCancel();//设置dialog不自动消失
        loadingDialog.show();
    }

    protected ProgressObserver(Activity activity, RxErrorHandler rxErrorHandler, String loadingText) {
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
        if (response.isSuccess()) {
            onSuccess(response);
        } else {
            onFail(response);
        }
    }

    private void dismissProgress() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (hasLoading) {
            super.onError(e);
        }
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

    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {
        String msg = response.getMsg();
        if (TextUtils.isEmpty(msg)) {
            ArmsUtils.snackbarText(activity.getString(R.string.response_return_error));
        } else {
            ArmsUtils.snackbarText(msg);
        }
    }
}
