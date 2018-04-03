package maple.demo.com.mymvparms.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;
import maple.demo.com.mymvparms.di.module.MainModule;
import maple.demo.com.mymvparms.di.module.MineModule;
import maple.demo.com.mymvparms.mvp.ui.fragment.MainFragment;
import maple.demo.com.mymvparms.mvp.ui.fragment.MineFragment;

@FragmentScope
@Component(modules = MineModule.class, dependencies = AppComponent.class)
public interface MineComponent {
    void inject(MineFragment fragment);
}