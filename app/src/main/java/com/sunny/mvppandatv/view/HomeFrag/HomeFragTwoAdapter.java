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
import com.sunny.mvppandatv.bean.HomeNewsMess;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/4.
 */

public class HomeFragTwoAdapter extends RecyclerView.Adapter<HomeFragTwoAdapter.Holder> implements View.OnClickListener {
    public HomeFragTwoAdapter(List<HomeNewsMess.DataBean.AreaBean.ListscrollBean> listscroll, Context context) {
        this.listscroll = listscroll;
        this.context = context;
    }

    private List<HomeNewsMess.DataBean.AreaBean.ListscrollBean> listscroll;
    private Context context;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_frag_two_item, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.home_frag_two_item_title.setText(listscroll.get(position).getTitle());
        Glide.with(context).load(listscroll.get(position).getImage()).into(holder.home_frag_two_item_img);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return listscroll.isEmpty()?0:listscroll.size();
    }

    @Override
    public void onClick(View view) {
        if (shortListener!=null){
            shortListener.setOnShortListener(view,(int)view.getTag());
        }

    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView home_frag_two_item_img;
        public TextView home_frag_two_item_title;
        public Holder(View itemView) {
            super(itemView);
            this.home_frag_two_item_img = (ImageView) itemView.findViewById(R.id.home_frag_two_item_img);

            this.home_frag_two_item_title = (TextView) itemView.findViewById(R.id.home_frag_two_item_title);

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
