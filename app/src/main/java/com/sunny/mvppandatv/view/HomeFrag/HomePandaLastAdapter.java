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
 * Created by mengYao on 2017/11/20.
 */

public class HomePandaLastAdapter extends RecyclerView.Adapter<HomePandaLastAdapter.ViewHolder> implements View.OnClickListener {
    List<HomePandaLastBean.ListBean> dataList;
    private Context context;

    public HomePandaLastAdapter(List<HomePandaLastBean.ListBean> listTwo, Context context) {
        this.dataList = listTwo;
        this.context = context;
    }

    @Override
    public HomePandaLastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomePandaLastAdapter.ViewHolder holder = null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_last_list_item, null);
        holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((ViewHolder) holder).mHomeEyePutData.setText(dataList.get(position).getDaytime());
        ((ViewHolder) holder).mHomeEyeVideoLength.setText(dataList.get(position).getVideoLength());
        ((ViewHolder) holder).mHomeAreaTitle.setText(dataList.get(position).getTitle());
        Glide.with(context).load(dataList.get(position).getImage()).into(((ViewHolder) holder).mHomeEyeImage);
        ((ViewHolder) holder).itemView.setTag(position);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView mHomeEyeImage;
        public ImageView image;
        public TextView mHomeEyeVideoLength;
        public TextView mHomeAreaTitle;
        public TextView mHomeEyePutData;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mHomeEyeImage = (ImageView) rootView.findViewById(R.id.mHomeEyeImage);
            this.image = (ImageView) rootView.findViewById(R.id.image);
            this.mHomeEyeVideoLength = (TextView) rootView.findViewById(R.id.mHomeEyeVideoLength);
            this.mHomeAreaTitle = (TextView) rootView.findViewById(R.id.mHomeAreaTitle);
            this.mHomeEyePutData = (TextView) rootView.findViewById(R.id.mHomeEyePutData);
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
