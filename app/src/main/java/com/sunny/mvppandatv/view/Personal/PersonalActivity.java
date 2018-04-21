package com.sunny.mvppandatv.view.Personal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonalActivity extends BaseActivity {


    @Bind(R.id.user_toolabr)
    Toolbar userToolabr;
    @Bind(R.id.user_head)
    ImageView userHead;
    @Bind(R.id.user_login)
    LinearLayout userLogin;
    @Bind(R.id.user_history)
    LinearLayout userHistory;
    @Bind(R.id.user_shoucang)
    LinearLayout userShoucang;
    @Bind(R.id.user_set)
    LinearLayout userSet;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.personalBack)
    ImageView personalBack;
    private SharedPreferences sharedPreferences;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    //    从内存中获取用户名如果内存中有用户名   就设置
    @Override
    protected void init() {
        sharedPreferences = getSharedPreferences("keeepUserName", MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        if (username.length() != 0) {
            userName.setText(username);
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.personalBack,R.id.user_head, R.id.user_login, R.id.user_history, R.id.user_shoucang, R.id.user_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personalBack:
                finish();
                break;
            case R.id.user_head:
                break;
            case R.id.user_login:
                if ("点击登录".equals(userName.getText().toString())) {
                    startActivity(new Intent(PersonalActivity.this, PersonalLoginActivity.class));
                } else {
                    Intent intent = new Intent(PersonalActivity.this, LoginActivity.class);
                    intent.putExtra("userName", userName.getText().toString());
                    startActivityForResult(intent, 2);
                }
                break;
            case R.id.user_history:
                startActivity(new Intent(PersonalActivity.this,WatchHistoryActivity.class));
                break;
            case R.id.user_shoucang:

                break;
            case R.id.user_set:
                startActivity(new Intent(PersonalActivity.this,SetActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("keeepUserName", MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin==true){
            String username = sharedPreferences.getString("USERNAME", "");
            userName.setText(username);
        }else {
            userName.setText("点击登录");
        }
    }
    /*//    退出登录

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == 7) {
            userName.setText("点击登录");
        }
    }*/
}
