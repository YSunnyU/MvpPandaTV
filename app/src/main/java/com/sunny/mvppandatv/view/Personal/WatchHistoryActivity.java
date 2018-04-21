package com.sunny.mvppandatv.view.Personal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class WatchHistoryActivity extends BaseActivity {


    @Bind(R.id.no_watch_history)
    ImageView noWatchHistory;
    @Bind(R.id.watchHistoryRecyclerView)
    RecyclerView watchHistoryRecyclerView;
    @Bind(R.id.watch_history)
    LinearLayout watchHistory;
    @Bind(R.id.watchHistoryBack)
    ImageView watchHistoryBack;
    @Bind(R.id.bianJiBtn)
    TextView bianJiBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_watch_history;

    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }




    @OnClick({R.id.watchHistoryBack, R.id.bianJiBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.watchHistoryBack:
                finish();
                break;
            case R.id.bianJiBtn:

                break;
        }
    }
}
