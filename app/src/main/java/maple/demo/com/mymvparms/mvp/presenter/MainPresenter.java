package maple.demo.com.mymvparms.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import maple.demo.com.mymvparms.db.UserDao;
import maple.demo.com.mymvparms.entity.ResultEntity;
import maple.demo.com.mymvparms.entity.ShileEntity;
import maple.demo.com.mymvparms.entity.UserEntity;
import maple.demo.com.mymvparms.helper.observer.ProgressObserver;
import maple.demo.com.mymvparms.mvp.contract.MainContract;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

@FragmentScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void requestData(String type,String page) {
        mModel.requestData(type,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ProgressObserver<ResultEntity<List<ShileEntity>>>(mErrorHandler) {
                    @Override
                    public void onSuccess(ResultEntity<List<ShileEntity>> response) {
                        mRootView.showMessage("onSuccess"+response.getData());
                    }

                    @Override
                    public void onFail(ResultEntity<List<ShileEntity>> response) {
                        mRootView.showMessage("onFail");
                    }
                });
    }
}
