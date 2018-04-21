package com.sunny.mvppandatv.view.HomeFrag;

import android.content.Intent;

import com.bumptech.glide.Glide;
import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {


    @Bind(R.id.homeFrag_videoPlayer)
    JCVideoPlayerStandard homeFragVideoPlayer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        homeFragVideoPlayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "这个视频名字叫十二");
        Glide.with(this).load(intent.getStringExtra("videoPath")).into(homeFragVideoPlayer.thumbImageView);

    }

    @Override
    protected void initData() {

    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
