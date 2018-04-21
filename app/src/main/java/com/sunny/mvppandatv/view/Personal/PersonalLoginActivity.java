package com.sunny.mvppandatv.view.Personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.sunny.mvppandatv.R.id.user_account;

public class PersonalLoginActivity extends BaseActivity {


    @Bind(R.id.user_enroll)
    TextView userEnroll;
    @Bind(R.id.user_login_toolbar)
    Toolbar userLoginToolbar;
    @Bind(R.id.user_weixin)
    TextView userWeixin;
    @Bind(R.id.user_qq)
    TextView userQq;
    @Bind(R.id.user_weibo)
    TextView userWeibo;
    @Bind(R.id.user_psw)
    EditText userPsw;
    @Bind(R.id.user_no_psw)
    TextView userNoPsw;
    @Bind(R.id.user_go)
    Button userGo;
    @Bind(R.id.numisEmpty)
    TextView numisEmpty;
    @Bind(R.id.pwdisEmpty)
    TextView pwdisEmpty;
    //获取用户ID、昵称
    private static final int MSG_LGOIN_IN_GET_NICKNAME = 107;
    private static final int MSG_LOGIN_IN_ERROR = 108;
    private static final int MSG_GET_NICKNAME_SUCCESS = 109;
    @Bind(R.id.personalLoginBack)
    ImageView personalLoginBack;
    @Bind(R.id.user_account)
    EditText userAccount;
    private SharedPreferences sharedPreferences;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LGOIN_IN_GET_NICKNAME:
                    getUserTicket();
                    break;
                case MSG_LOGIN_IN_ERROR:
                    break;
                case MSG_GET_NICKNAME_SUCCESS:
                    keepUserName();
                    startActivity(new Intent(PersonalLoginActivity.this, PersonalActivity.class));
                    break;
            }
        }
    };
    private String mUserSeqId;
    private String mUserId;
    private String string;
    private String mNickName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_login;
    }

    @Override
    protected void init() {
        userNoPsw.setText("忘记密码?");
        userNoPsw.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        userNoPsw.getPaint().setAntiAlias(true);//抗锯齿
        userNoPsw.setTextColor(Color.parseColor("#1f539e"));
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.personalLoginBack,R.id.user_enroll, R.id.user_weixin, R.id.user_qq, R.id.user_weibo, user_account, R.id.user_psw, R.id.user_no_psw, R.id.user_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personalLoginBack:
                finish();
                break;
            case R.id.user_enroll:
                startActivity(new Intent(PersonalLoginActivity.this, PersonalRegistActivity.class));
                break;
            case R.id.user_weixin:
                new AlertDialog.Builder(this)
                        .setMessage("熊猫频道想要打开微信")
                        .setPositiveButton("确定", null)
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
                break;
            case R.id.user_qq:
                new AlertDialog.Builder(this)
                        .setMessage("熊猫频道想要打开QQ")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                UMShareAPI.get(PersonalLoginActivity.this).getPlatformInfo(PersonalLoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create()
                        .show();
                break;
            case R.id.user_weibo:
                break;
            case user_account:

                break;
            case R.id.user_psw:
                break;
            case R.id.user_no_psw:
//                sharedPreferences = getSharedPreferences("keeepUserName", MODE_PRIVATE);
//                SharedPreferences.Editor edit = sharedPreferences.edit();
//                mNickName="";
//                edit.putString("name",mNickName);
                startActivity(new Intent(PersonalLoginActivity.this,ForgetPasswordActivity.class));
                break;
            case R.id.user_go:
                goLogin();
                if (userAccount == null) {
                    numisEmpty.setVisibility(View.VISIBLE);
                }
                if (userPsw == null) {
                    pwdisEmpty.setVisibility(View.VISIBLE);
                } else {

                }
                break;
        }
    }


    /**
     * 登录
     */
    private void goLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String mUserNameString = userAccount.getText().toString()
                        .trim();
                String mPassWordString = userPsw.getText().toString()
                        .trim();

                String from = "https://reg.cntv.cn/login/login.action";
                try {
                    String url = from + "?username="
                            + URLEncoder.encode(mUserNameString, "UTF-8")
                            + "&password=" + mPassWordString
                            + "&service=client_transaction" + "&from="
                            + URLEncoder.encode(from, "UTF-8");

                    OkHttpClient okhttpClient = new OkHttpClient.Builder().build();
                    Request.Builder url1 = new Request.Builder().url(url);
                    Request.Builder referer = url1.addHeader("Referer", URLEncoder.encode(from, "UTF-8"));
                    url1.addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CYNTV_MOBILE", "UTF-8"));
                    okhttpClient.newCall(url1.build()).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String string = response.body().string();
                            Log.d("PersonalLoginActivity", string);
                            try {
                                JSONObject jo = new JSONObject(string);
                                if (jo.getString("errType").equals("0")) {
                                    if (jo.has("user_seq_id")) {
                                        mUserSeqId = jo.getString("user_seq_id");
                                    }
                                    if (jo.has("usrid")) {
                                        mUserId = jo.getString("usrid");
                                    }
                                    handler.sendEmptyMessage(MSG_LGOIN_IN_GET_NICKNAME);
                                } else {
                                    // String codeString = jo.getString("errType");
                                    String errMsg = jo.getString("errMsg");

                                    Message msg = handler
                                            .obtainMessage(MSG_LOGIN_IN_ERROR);
                                    msg.obj = errMsg;
                                    handler.sendMessage(msg);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
    /*private void  loginErrorMsg(String errtype){
        if (errtype.equals("105")) {
            mTxtTishiPassword.setText("密码错误，请重输");
        }
        if (errtype.equals("104")) {
            mTxtTishiAccount.setText("该账号未注册。");
        }
        if (errtype.equals("106")) {
            mTxtTishiAccount.setText("很抱歉，您的账号还没有激活，请激活后再登录。");
        }
        if (errtype.equals("102")) {
            mTxtTishiAccount.setText("缺少参数");
        }

    }*/

    /**
     * 获取昵称
     */
    private void getUserTicket() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String client = "http://cbox_mobile.regclientuser.cntv.cn";
                String url = "http://my.cntv.cn/intf/napi/api.php" + "?client="
                        + "cbox_mobile" + "&method=" + "user.getNickName"
                        + "&userid=" + mUserSeqId;
                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                Request.Builder requestTwo = new Request.Builder();

                try {
                    requestTwo.addHeader("Referer",
                            URLEncoder.encode(client, "UTF-8"));
                    requestTwo.addHeader("User-Agent", URLEncoder.encode(
                            "CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
//                    requestTwo.addHeader("Cookie", "verifycode=" + verifycode);
                    okHttpClient.newCall(requestTwo.url(url).build()).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            string = response.body().string();
                            Log.d("PersonalLoginActivity", string);
                            try {
                                JSONObject jo = new JSONObject(string);
                                if (jo.getString("code").equals("0")) {
                                    if (jo.has("content")) {
                                        JSONObject contentJSONObject = jo.getJSONObject("content");
                                        if (contentJSONObject.has("nickname")) {
                                            mNickName = contentJSONObject
                                                    .getString("nickname");
                                        } else {
                                            String mNickName = "default";
                                        }
                                    }
                                    handler.sendEmptyMessage(MSG_GET_NICKNAME_SUCCESS);
                                } else {
                                    String codeErrorString = jo.getString("error");
                                    Message msg = handler
                                            .obtainMessage(MSG_LOGIN_IN_ERROR);
                                    msg.obj = codeErrorString;
                                    handler.sendMessage(msg);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void keepUserName() {
        sharedPreferences = getSharedPreferences("keeepUserName", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("USERNAME", mNickName);
        edit.putBoolean("isLogin",true);
        edit.commit();
        finish();
    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


}
