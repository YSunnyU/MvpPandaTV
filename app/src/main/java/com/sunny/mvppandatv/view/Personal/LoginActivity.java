package com.sunny.mvppandatv.view.Personal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.logOutLogin)
    Button logOutLogin;
    @Bind(R.id.loginUserName)
    TextView loginUserName;
    @Bind(R.id.loginBack)
    ImageView loginBack;
    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        intent = getIntent();
        String userName = intent.getStringExtra("userName");
        loginUserName.setText(userName);

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.loginBack, R.id.logOutLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginBack:
                finish();
                break;
            case R.id.logOutLogin:
                setResult(7,intent);
                SharedPreferences sharedPreferences = getSharedPreferences("keeepUserName", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("isLogin",false);
                edit.commit();
                finish();
                break;
        }
    }
}
