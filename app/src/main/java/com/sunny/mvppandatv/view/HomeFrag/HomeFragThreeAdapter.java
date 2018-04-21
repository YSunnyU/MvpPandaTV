package com.sunny.mvppandatv.view.HomeFrag;

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
 * Created by 张玗 on 2018/4/8.
 */

public class HomeFragThreeAdapter extends RecyclerView.Adapter<HomeFragThreeAdapter.Holder> implements View.OnClickListener {
    private Context context;
    private List<HomeThreeBean.ListBean> list;

    public HomeFragThreeAdapter(Context context, List<HomeThreeBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_frag_three_item, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).asBitmap().into(holder.home_frag_three_item_img);
        holder.home_frag_three_item_title.setText(list.get(position).getTitle());
        holder.home_frag_three_item_time.setText(list.get(position).getDaytime());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.isEmpty()?0:list.size();
    }

    @Override
    public void onClick(View view) {
        if (shortListener!=null){
            shortListener.setOnShortListener(view,(int)view.getTag());
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView home_frag_three_item_img;
        public TextView home_frag_three_item_title;
        public TextView home_frag_three_item_time;
        public Holder(View itemView) {
            super(itemView);
            this.home_frag_three_item_img = (ImageView) itemView.findViewById(R.id.home_frag_three_item_img);
            this.home_frag_three_item_title = (TextView) itemView.findViewById(R.id.home_frag_three_item_title);
            this.home_frag_three_item_time = (TextView) itemView.findViewById(R.id.home_frag_three_item_time);
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
