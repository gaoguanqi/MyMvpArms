package maple.demo.com.mymvparms.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import maple.demo.com.mymvparms.di.module.SettingModule;

import maple.demo.com.mymvparms.mvp.ui.activity.SettingActivity;

@ActivityScope
@Component(modules = SettingModule.class, dependencies = AppComponent.class)
public interface SettingComponent {
    void inject(SettingActivity activity);
}