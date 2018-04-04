package maple.demo.com.mymvparms.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import maple.demo.com.mymvparms.entity.ResultEntity;
import maple.demo.com.mymvparms.entity.ShileEntity;


public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        Activity getActivity();

        void requestDataSucess(List<ShileEntity> data);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<ResultEntity<List<ShileEntity>>> requestData(String type, String page);
    }
}
