package maple.demo.com.mymvparms.mvp.ui.adapter.main;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.rd.PageIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.app.AppController;


/**
 * Created by Gaoguanqi on 2018/4/3.
 */

public class MainHeaderHolder extends  RecyclerView.ViewHolder {

    @BindView(R.id.item_header_vp)
    ViewPager vp;
    @BindView(R.id.item_header_piv)
    PageIndicatorView piv;

    private List<String> data;
    public MainHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(List<String> data) {
//        ArmsUtils.obtainAppComponentFromContext(AppController.getInstance().getContext()).imageLoader().loadImage(getActivity(), ImageConfigImpl
//                .builder()
//                .url(image)
//                .imageView(mAvatar)
//                .build());
    }
}
