package maple.demo.com.mymvparms.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import maple.demo.com.mymvparms.entity.ResultEntity;
import maple.demo.com.mymvparms.entity.ShileEntity;
import maple.demo.com.mymvparms.mvp.contract.MainContract;
import maple.demo.com.mymvparms.net.api.ApiService;


@FragmentScope
public class MainModel extends BaseModel implements MainContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<ResultEntity<List<ShileEntity>>> requestData(String type, String page) {
        return  mRepositoryManager.obtainRetrofitService(ApiService.class).requestData(type,page);
    }

}