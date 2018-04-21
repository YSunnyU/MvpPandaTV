package com.sunny.mvppandatv.view.LiveChina;

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
 * Created by 张玗 on 2018/4/15.
 */

public class ChinaLiveFragAdapter extends RecyclerView.Adapter<ChinaLiveFragAdapter.Holder> {
    List<ChinaLiveBean.LiveBean> live;
    private Context context;

    public ChinaLiveFragAdapter(List<ChinaLiveBean.LiveBean> live, Context context) {
        this.live = live;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View live_china = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_china_frag_item, parent, false);
        Holder holder = new Holder(live_china);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final ChinaLiveBean.LiveBean liveBean = live.get(position);
        Glide.with(context).load(liveBean.getImage()).asBitmap().into(holder.live_china_frag_item_image);
        holder.live_china_frag_item_itemTitle.setText("[正在直播]"+liveBean.getTitle());
        holder.live_china_frag_item_bottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.live_china_frag_item_content.setVisibility(View.VISIBLE);
                holder.live_china_frag_item_content.setText(liveBean.getBrief());
                holder.live_china_frag_item_topBtn.setVisibility(View.VISIBLE);
                holder.live_china_frag_item_bottomBtn.setVisibility(View.GONE);
            }
        });
        holder.live_china_frag_item_topBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.live_china_frag_item_content.setVisibility(View.GONE);
                holder.live_china_frag_item_topBtn.setVisibility(View.GONE);
                holder.live_china_frag_item_bottomBtn.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return live.isEmpty()?0:live.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView live_china_frag_item_image;
        public ImageView live_china_frag_item_bottomBtn;
        public TextView live_china_frag_item_content;
        public ImageView live_china_frag_item_topBtn;
        public TextView live_china_frag_item_itemTitle;
        public Holder(View itemView) {

            super(itemView);
            this.live_china_frag_item_image = (ImageView) itemView.findViewById(R.id.live_china_frag_item_image);
            this.live_china_frag_item_bottomBtn = (ImageView) itemView.findViewById(R.id.live_china_frag_item_bottomBtn);
            this.live_china_frag_item_content = (TextView) itemView.findViewById(R.id.live_china_frag_item_content);
            this.live_china_frag_item_topBtn = (ImageView) itemView.findViewById(R.id.live_china_frag_item_topBtn);
            this.live_china_frag_item_itemTitle = (TextView) itemView.findViewById(R.id.live_china_frag_item_itemTitle);
        }
    }


}
