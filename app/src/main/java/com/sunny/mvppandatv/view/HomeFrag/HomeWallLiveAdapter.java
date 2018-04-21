package com.sunny.mvppandatv.view.HomeFrag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.bean.HomeNewsMess;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/13.
 */

public class HomeWallLiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    List<HomeNewsMess.DataBean.WallliveBean.ListBeanXX> dataList;
//    private List<PandHome.DataBean.WallliveBean.ListBeanX> dataList;
    private Context context;

    public HomeWallLiveAdapter(List<HomeNewsMess.DataBean.WallliveBean.ListBeanXX> list, Context context) {
        this.dataList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        View view = View.inflate(context, R.layout.home_panda_live_list_item, null);
        holder=new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            Glide.with(context).load(dataList.get(position).getImage()).into(((ViewHolder) holder).mPandaLiveImage);
            ((ViewHolder) holder).mPandaLiveContent.setText(dataList.get(position).getTitle());
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View view) {
        if (shortListener!=null){
            shortListener.setOnShortListener(view,(int)view.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView mPandaLiveImage;
        public ImageView image;
        public ImageView image1;
        public TextView mPandaLiveContent;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mPandaLiveImage = (ImageView) rootView.findViewById(R.id.mPandaLiveImage);
            this.image = (ImageView) rootView.findViewById(R.id.image);
            this.image1 = (ImageView) rootView.findViewById(R.id.image1);
            this.mPandaLiveContent = (TextView) rootView.findViewById(R.id.mPandaLiveContent);
        }

    }
    public interface OnShortListener{
            void setOnShortListener(View view,int position);
        }
        private OnShortListener shortListener;
        public void OnShortListener(OnShortListener shortListener){
            this.shortListener=shortListener;
        };
}
