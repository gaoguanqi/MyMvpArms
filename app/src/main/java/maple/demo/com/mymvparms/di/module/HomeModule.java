package maple.demo.com.mymvparms.di.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

import maple.demo.com.mymvparms.mvp.contract.HomeContract;
import maple.demo.com.mymvparms.mvp.model.HomeModel;
import maple.demo.com.mymvparms.mvp.ui.activity.HomeActivity;
import maple.demo.com.mymvparms.mvp.ui.adapter.HomePagerAdapter;
import maple.demo.com.mymvparms.mvp.ui.fragment.MainFragment;
import maple.demo.com.mymvparms.mvp.ui.fragment.MineFragment;


@Module
public class HomeModule {
    private HomeContract.View view;
    /**
     * 构建HomeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HomeModule(HomeContract.View view) {
        this.view = view;
    }


    @ActivityScope
    @Provides
    HomeContract.View provideHomeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HomeContract.Model provideHomeModel(HomeModel model) {
        return model;
    }





    @ActivityScope
    @Provides
    List<Fragment> providesFragments() {
        List<Fragment> mList = new ArrayList<>();
        mList.add(MainFragment.getInstance());
        mList.add(MineFragment.getInstance());
        return mList;
    }

    @ActivityScope
    @Provides
    HomePagerAdapter providesHomePagerAdapter(List<Fragment> mList) {
       return new HomePagerAdapter(((HomeActivity)view.getActivity()).getSupportFragmentManager(),mList);
    }
}