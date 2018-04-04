package maple.demo.com.mymvparms.mvp.ui.adapter.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import maple.demo.com.mymvparms.R;

/**
 * Created by Gaoguanqi on 2018/4/3.
 */

public class MainFooterHolder extends  RecyclerView.ViewHolder {

    @BindView(R.id.item_footer_tv)
    TextView tv;
    private String data;

    public MainFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(String data) {
        this.data = data;
        tv.setText(data);
    }
}
