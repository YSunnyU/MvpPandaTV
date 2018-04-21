package com.sunny.mvppandatv.view.CCTVFrag;

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

public class CCTVSonFragAdapter extends RecyclerView.Adapter<CCTVSonFragAdapter.Holder> {
    private List<CctvChaneLiveBean.ListBean> listBeenList;
    private Context context;

    public CCTVSonFragAdapter(List<CctvChaneLiveBean.ListBean> listBeenList, Context context) {
        this.listBeenList = listBeenList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cctv_frag_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cctv_frag_item, parent, false);
        Holder holder = new Holder(cctv_frag_item);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.cctv_frag_item_content.setText(listBeenList.get(position).getTitle());
        Glide.with(context).load(listBeenList.get(position).getImage()).into(holder.cctv_frag_item_image);
        holder.cctv_frag_item_content.setText(listBeenList.get(position).getTitle());
        holder.cctv_frag_item_brife.setText(listBeenList.get(position).getBrief());
    }

    @Override
    public int getItemCount() {
        return listBeenList.isEmpty() ? 0 : listBeenList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView cctv_frag_item_image;
        public TextView cctv_frag_item_content;
        public TextView cctv_frag_item_brife;
        public Holder(View itemView) {
            super(itemView);
            this.cctv_frag_item_image = (ImageView) itemView.findViewById(R.id.cctv_frag_item_image);
            this.cctv_frag_item_content = (TextView) itemView.findViewById(R.id.cctv_frag_item_content);
            this.cctv_frag_item_brife = (TextView) itemView.findViewById(R.id.cctv_frag_item_brife);

        }
    }



}
