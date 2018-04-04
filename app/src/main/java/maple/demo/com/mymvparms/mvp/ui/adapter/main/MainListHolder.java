package maple.demo.com.mymvparms.mvp.ui.adapter.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.entity.ShileEntity;

/**
 * Created by Gaoguanqi on 2018/4/3.
 */

public class MainListHolder extends RecyclerView.ViewHolder  {

    @BindView(R.id.item_list_iv)
    ImageView iv;
    @BindView(R.id.item_list_tv)
    TextView tv;
    private ShileEntity data;

    public MainListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(ShileEntity data) {
        this.data = data;
        tv.setText(data.getUsername());
    }
}
