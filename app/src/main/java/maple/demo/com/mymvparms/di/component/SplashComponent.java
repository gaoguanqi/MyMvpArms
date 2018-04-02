package maple.demo.com.mymvparms.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import maple.demo.com.mymvparms.di.module.SplashModule;

import maple.demo.com.mymvparms.mvp.ui.activity.SplashActivity;

@ActivityScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}