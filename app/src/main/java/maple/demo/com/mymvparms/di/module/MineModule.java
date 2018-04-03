package maple.demo.com.mymvparms.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;
import maple.demo.com.mymvparms.mvp.contract.MainContract;
import maple.demo.com.mymvparms.mvp.contract.MineContract;
import maple.demo.com.mymvparms.mvp.model.MainModel;
import maple.demo.com.mymvparms.mvp.model.MineModel;


@Module
public class MineModule {
    private MineContract.View view;

    /**
     * 构建MineModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MineModule(MineContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    MineContract.View provideMineView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    MineContract.Model provideMineModel(MineModel model) {
        return model;
    }
}