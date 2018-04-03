package maple.demo.com.mymvparms.di.component;

import android.app.Activity;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;
import dagger.Module;
import maple.demo.com.mymvparms.di.module.HomeModule;
import maple.demo.com.mymvparms.di.module.MainModule;
import maple.demo.com.mymvparms.mvp.ui.activity.HomeActivity;
import maple.demo.com.mymvparms.mvp.ui.fragment.MainFragment;

@FragmentScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainFragment fragment);
}