package com.sunny.mvppandatv.view.PandaEyeFrag;

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
 * Created by 张玗 on 2018/4/13.
 */

public class PandaEyeListAdapter extends RecyclerView.Adapter<PandaEyeListAdapter.Holder> {
    List<PandaEyeListBean.ListBean> list;
    private Context context;

    public PandaEyeListAdapter(List<PandaEyeListBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_last_list_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.mHomeEyePutData.setText(list.get(position).getFocus_date()+"");
        holder.mHomeEyeVideoLength.setText(list.get(position).getVideolength());
        holder.mHomeAreaTitle.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getPicurl()).into(holder.mHomeEyeImage);
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView mHomeEyeImage;
        public ImageView image;
        public TextView mHomeEyeVideoLength;
        public TextView mHomeAreaTitle;
        public TextView mHomeEyePutData;

        public Holder(View itemView) {
            super(itemView);
            this.mHomeEyeImage = (ImageView) itemView.findViewById(R.id.mHomeEyeImage);
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.mHomeEyeVideoLength = (TextView) itemView.findViewById(R.id.mHomeEyeVideoLength);
            this.mHomeAreaTitle = (TextView) itemView.findViewById(R.id.mHomeAreaTitle);
            this.mHomeEyePutData = (TextView) itemView.findViewById(R.id.mHomeEyePutData);
        }
    }


}
