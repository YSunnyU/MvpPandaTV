package com.sunny.mvppandatv.view.Personal;

import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {


    @Bind(R.id.setBack)
    ImageView setBack;
    @Bind(R.id.messPush)
    CheckBox messPush;
    @Bind(R.id.autoPlayNext)
    CheckBox autoPlayNext;
    @Bind(R.id.clearCache)
    TextView clearCache;
    @Bind(R.id.personal_set_delete_cache_layout)
    RelativeLayout personalSetDeleteCacheLayout;
    @Bind(R.id.personal_tickling_layout)
    RelativeLayout personalTicklingLayout;
    @Bind(R.id.personal_set_udpate_layout)
    RelativeLayout personalSetUdpateLayout;
    @Bind(R.id.personal_set_ping_layout)
    RelativeLayout personalSetPingLayout;
    @Bind(R.id.personal_set_about_layout)
    RelativeLayout personalSetAboutLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void init() {
        try {
            long cache = RetrofitUtils.getFolderSize(new File(Environment.getExternalStorageDirectory().getPath()+"/cache"));
            clearCache.setText(RetrofitUtils.getFormatSize(cache));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.setBack, R.id.messPush, R.id.autoPlayNext, R.id.personal_set_delete_cache_layout, R.id.personal_tickling_layout, R.id.personal_set_udpate_layout, R.id.personal_set_ping_layout, R.id.personal_set_about_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setBack:
                finish();
                break;
            case R.id.messPush:

                break;
            case R.id.autoPlayNext:
                break;
            case R.id.personal_set_delete_cache_layout:
                RetrofitUtils.deleteFile(Environment.getExternalStorageDirectory().getPath()+"/cache");
                init();
                break;
            case R.id.personal_tickling_layout:
                break;
            case R.id.personal_set_udpate_layout:
                break;
            case R.id.personal_set_ping_layout:
                break;
            case R.id.personal_set_about_layout:
                break;
        }
    }
}
