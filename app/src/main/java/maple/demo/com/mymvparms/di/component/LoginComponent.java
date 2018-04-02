package maple.demo.com.mymvparms.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import maple.demo.com.mymvparms.di.module.LoginModule;

import maple.demo.com.mymvparms.mvp.ui.activity.LoginActivity;

@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}