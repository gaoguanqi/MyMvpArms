package maple.demo.com.mymvparms.mvp.presenter;

import android.app.Application;
import android.content.Intent;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import maple.demo.com.mymvparms.mvp.ui.activity.HomeActivity;
import maple.demo.com.mymvparms.mvp.ui.activity.WelcomeActivity;
import maple.demo.com.mymvparms.widget.CountDownView;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import maple.demo.com.mymvparms.mvp.contract.SplashContract;


@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView
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

    public void countDownView(CountDownView countDownView) {
        countDownView.start();
        countDownView.setOnLoadingFinishListener(new CountDownView.OnLoadingFinishListener() {
            @Override
            public void finish() {
                mRootView.launchActivity(new Intent(mRootView.getActivity(),HomeActivity.class));
                mRootView.killMyself();
            }
        });
    }


    public void onSkip(CountDownView countDownView) {
        if(countDownView != null){
            countDownView.cancle();
        }
        mRootView.launchActivity(new Intent(mRootView.getActivity(),HomeActivity.class));
        mRootView.killMyself();
    }
}
