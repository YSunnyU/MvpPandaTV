package com.sunny.mvppandatv.view;

import android.content.Intent;
import android.os.Handler;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;
import com.sunny.mvppandatv.view.Personal.AboveActivity;

public class ShowActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              startActivity(new Intent(ShowActivity.this,AboveActivity.class));
                finish();
            }
        },3000);

    }

    @Override
    protected void initData() {

    }
}
