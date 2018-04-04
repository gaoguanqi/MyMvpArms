package maple.demo.com.mymvparms.mvp.ui.adapter.main;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import maple.demo.com.mymvparms.R;
import maple.demo.com.mymvparms.entity.ShileEntity;

/**
 * Created by Gaoguanqi on 2018/4/3.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public  final int HEADER = 0;
    public  final int LIST = 1;
    public  final int FOOTER = 2;


    private Context context;
    private LayoutInflater mInflater;

    private List<ShileEntity> mListData;
    private List<String> mHeaderData;
    private String mFooterData;

    private BannerPagerAdapter bannerPagerAdapter;
    public MainAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            return new MainHeaderHolder(mInflater.inflate(R.layout.item_main_header,parent,false));
        } else if (viewType == FOOTER) {
            return new MainFooterHolder(mInflater.inflate(R.layout.item_main_footer,parent,false));
        }else{
            return new MainListHolder(mInflater.inflate(R.layout.item_main_list,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainHeaderHolder) {
            ((MainHeaderHolder) holder).setData(mHeaderData);
        }else if(holder instanceof MainFooterHolder){
            ((MainFooterHolder) holder).setData(mFooterData);
        }else{
            ((MainListHolder) holder).setData(mListData.get(position-1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if( position == 0){
            return HEADER;
        }else if(position == mListData.size()+2-1){
            return FOOTER;
        }else{
            return LIST;
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderData == null || mListData == null || mListData.isEmpty() ? 0:mListData.size() + 2;
    }

    public void setListData(List<ShileEntity> listData) {
        this.mListData = listData;
        notifyDataSetChanged();
    }


    public void setHeaderData(List<String> headerData) {
        this.mHeaderData = headerData;
        notifyDataSetChanged();
    }

    public void setFooterData(String footerData) {
        this.mFooterData = footerData;
        notifyDataSetChanged();
    }
}
