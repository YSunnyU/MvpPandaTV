package com.sunny.mvppandatv.view.HomeFrag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.mvppandatv.R;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by mengYao on 2017/11/20.
 */

public class HomeCctvListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    List<HomeCCTVBean.ListBean> dataList;
    private Context context;
    private JCVideoPlayerStandard mHomeCctvImage;

    public HomeCctvListAdapter(List<HomeCCTVBean.ListBean> list, Context context) {
        this.dataList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = View.inflate(context, R.layout.home_cctv_list_item, null);
        holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
//            Glide.with(context).load(dataList.get(position).getImage()).into(((ViewHolder) holder).mHomeCctvImage);
            ((ViewHolder) holder).mHomeCctvTitle.setText(dataList.get(position).getTitle());
            ((ViewHolder) holder).mHomeCctvImage.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "这是节操视频播放器");
//        holder.videoPlayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
            Glide.with(context).load(dataList.get(position).getImage()).into(((ViewHolder) holder).mHomeCctvImage.thumbImageView);
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

    public interface OnShortListener{
            void setOnShortListener(View view,int position);
        }
        private OnShortListener shortListener;
        public void OnShortListener(OnShortListener shortListener){
            this.shortListener=shortListener;
        };
//    public static

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        //        public ImageView mHomeCctvImage;
        public ImageView image;
        public ImageView image1;
        public JCVideoPlayerStandard mHomeCctvImage;
        public TextView mHomeCctvTitle;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
//            this.mHomeCctvImage = (ImageView) rootView.findViewById(R.id.mHomeCctvImage);
            this.image = (ImageView) rootView.findViewById(R.id.image);
            this.image1 = (ImageView) rootView.findViewById(R.id.image1);
            this.mHomeCctvTitle = (TextView) rootView.findViewById(R.id.mHomeCCtvTitle);
            this.mHomeCctvImage = (JCVideoPlayerStandard) rootView.findViewById(R.id.mHomeCctvImage);

        }

    }

}
