package maple.demo.com.mymvparms.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import maple.demo.com.mymvparms.mvp.contract.SettingContract;
import maple.demo.com.mymvparms.mvp.model.SettingModel;


@Module
public class SettingModule {
    private SettingContract.View view;

    /**
     * 构建SettingModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SettingModule(SettingContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SettingContract.View provideSettingView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SettingContract.Model provideSettingModel(SettingModel model) {
        return model;
    }
}