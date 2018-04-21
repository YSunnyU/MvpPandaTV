package com.sunny.mvppandatv.view.PandaLive;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.mvppandatv.R;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class PandaLiveChildOneAdapter extends RecyclerView.Adapter<PandaLiveChildOneAdapter.Holder> {
    List<PandaLiveSonBean.ListBean> list;
    private Context context;

    public PandaLiveChildOneAdapter(List<PandaLiveSonBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_panda_live_list_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.mPandaLiveImage);
        holder.mPandaLiveContent.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.isEmpty()?0:list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView mPandaLiveImage;
        public ImageView image;
        public ImageView image1;
        public TextView mPandaLiveContent;
        public Holder(View itemView) {
            super(itemView);
            this.mPandaLiveImage = (ImageView) itemView.findViewById(R.id.mPandaLiveImage);
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.image1 = (ImageView) itemView.findViewById(R.id.image1);
            this.mPandaLiveContent = (TextView) itemView.findViewById(R.id.mPandaLiveContent);
        }
    }
}
