package com.sunny.mvppandatv.view.HuDong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.mvppandatv.R;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class HuDongAdapter extends RecyclerView.Adapter<HuDongAdapter.Holder> implements View.OnClickListener {
    List<HuDongBean.InteractiveBean> interactive;
    private Context context;

    public HuDongAdapter(List<HuDongBean.InteractiveBean> interactive, Context context) {
        this.interactive = interactive;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.hudong_item, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.hudong_item_title.setText(interactive.get(position).getTitle());
//        Glide.with(context).load(interactive.get(position).getImage()).into(holder.hudong_item_imageView);
        holder.videoPlayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
        Glide.with(context).load(interactive.get(position).getImage()).into(holder.videoPlayer.thumbImageView);
//        videoPlayer.setUp();
//        videoPlayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View view) {
        if (shortListener != null) {
            shortListener.setOnShortListener(view, (int) view.getTag());
        }
    }

    public interface OnShortListener {
        void setOnShortListener(View view, int position);
    }

    private OnShortListener shortListener;

    public void OnShortListener(OnShortListener shortListener) {
        this.shortListener = shortListener;
    }

    ;

    @Override
    public int getItemCount() {
        return interactive.isEmpty() ? 0 : interactive.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView hudong_item_title;
        public JCVideoPlayerStandard videoPlayer;

        //        public ImageView hudong_item_imageView;
        public Holder(View itemView) {
            super(itemView);
            this.hudong_item_title = (TextView) itemView.findViewById(R.id.hudong_item_title);
//            this.hudong_item_imageView = (ImageView) itemView.findViewById(R.id.hudong_item_imageView);
            this.videoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoPlayer);

        }
    }



}
