package maple.demo.com.mymvparms.di.module;

import android.support.v7.widget.LinearLayoutManager;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;
import maple.demo.com.mymvparms.mvp.contract.HomeContract;
import maple.demo.com.mymvparms.mvp.contract.MainContract;
import maple.demo.com.mymvparms.mvp.model.HomeModel;
import maple.demo.com.mymvparms.mvp.model.MainModel;


@Module
public class MainModule {
    private MainContract.View view;

    /**
     * 构建MainModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    MainContract.View provideMainView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    MainContract.Model provideMainModel(MainModel model) {
        return model;
    }

    @FragmentScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(view.getActivity());
    }
}