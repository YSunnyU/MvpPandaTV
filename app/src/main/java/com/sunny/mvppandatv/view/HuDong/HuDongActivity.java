package com.sunny.mvppandatv.view.HuDong;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;
import com.sunny.mvppandatv.contract.HuDongContract;
import com.sunny.mvppandatv.presenter.HuDongPresenter;

import java.util.List;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class HuDongActivity extends BaseActivity implements HuDongContract.HuDongInView{


    @Bind(R.id.activity_hu_dong_recyclerView)
    RecyclerView activityHuDongRecyclerView;
    public HuDongContract.HuDongInPresenter huDongInPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_hu_dong;
    }

    @Override
    protected void init() {
        huDongInPresenter=new HuDongPresenter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        activityHuDongRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        huDongInPresenter.sendData();
    }

    @Override
    public void showHuDongData(HuDongBean huDongBean) {
        List<HuDongBean.InteractiveBean> interactive = huDongBean.getInteractive();
        HuDongAdapter huDongAdapter = new HuDongAdapter(interactive, this);
        activityHuDongRecyclerView.setAdapter(huDongAdapter);
        huDongAdapter.OnShortListener(new HuDongAdapter.OnShortListener() {
            @Override
            public void setOnShortListener(View view, int position) {

                Toast.makeText(HuDongActivity.this, "这可是第一次吐司", Toast.LENGTH_SHORT).show();

            }
        });
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
