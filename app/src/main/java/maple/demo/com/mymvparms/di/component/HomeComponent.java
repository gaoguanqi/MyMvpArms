package maple.demo.com.mymvparms.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import maple.demo.com.mymvparms.di.module.HomeModule;

import maple.demo.com.mymvparms.mvp.ui.activity.HomeActivity;

@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
}