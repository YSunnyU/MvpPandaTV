package com.sunny.mvppandatv.view.Personal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseActivity;
import com.sunny.mvppandatv.service.PersonalregistService;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgetPasswordActivity extends BaseActivity implements View.OnFocusChangeListener {


    @Bind(R.id.edit_phonenumber)
    EditText editPhonenumber;
    @Bind(R.id.hint_phonenumber)
    TextView hintPhonenumber;
    @Bind(R.id.edit_checkimage)
    EditText editCheckimage;
    @Bind(R.id.personal_reg_imgcheck)
    ImageView personalRegImgcheck;
    @Bind(R.id.hint_checkimage)
    TextView hintCheckimage;
    @Bind(R.id.edit_checkphone)
    EditText editCheckphone;
    @Bind(R.id.personal_reg_phonecheck)
    Button personalRegPhonecheck;
    @Bind(R.id.hint_checkphone)
    TextView hintCheckphone;
    @Bind(R.id.edit_newpssword)
    EditText editNewpssword;
    @Bind(R.id.hint_newpssword)
    TextView hintNewpssword;
    @Bind(R.id.tvfoundpswd)
    TextView tvfoundpswd;
    @Bind(R.id.forgetPswBack)
    ImageView forgetPswBack;
    private String jsessionid;
    private InputStream inputStream;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void init() {
        editCheckimage.setOnFocusChangeListener(this);
        editCheckphone.setOnFocusChangeListener(this);
        editNewpssword.setOnFocusChangeListener(this);
//        获取图片验证码
        sendCaptchaHttpMessage();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.forgetPswBack, R.id.personal_reg_imgcheck, R.id.personal_reg_phonecheck, R.id.tvfoundpswd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forgetPswBack:
//                finish();
                break;
            case R.id.personal_reg_imgcheck:
//                获取图片验证码
                sendCaptchaHttpMessage();
                break;
            case R.id.personal_reg_phonecheck:
//                获取短信验证码
                sendCaptchaSmsHttpMessage();
                break;
            case R.id.tvfoundpswd:
//                手机号码重置
                sendPhoneRegistHttp();
                break;
        }
    }


    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.edit_checkimage:
                if (!checkPhone())
                    return;
                hintPhonenumber.setText("");

//                hintCheckimage.setText("");
            case R.id.edit_checkphone:

                if (!checkCaptcha())
                    return;
                hintCheckimage.setText("");
                break;
            case R.id.edit_newpssword:
//                短信验证码判断
                if (!checkPhoneCheck()) {

                    return;
                }
                hintCheckphone.setText("");
                break;

        }
    }
//    获取图片验证码

    private void sendCaptchaHttpMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String from = "http://reg.cntv.cn/simple/verificationCode.action";
                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                Request build1 = new Request.Builder().url(from).build();
                okHttpClient.newCall(build1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Headers headers = response.headers();
                        jsessionid = headers.get("Set-Cookie");
                        inputStream = response.body().byteStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                personalRegImgcheck.setImageBitmap(bitmap);
                            }
                        });

                    }
                });

            }
        }).start();

    }

    /**
     * 短信验证码的请求
     */
    private void sendCaptchaSmsHttpMessage() {
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";
        HashMap<String, String> tHeaders = new HashMap<String, String>();
        try {
            tHeaders.put("Referer", URLEncoder.encode(from, "UTF-8"));
            tHeaders.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            tHeaders.put("Cookie", jsessionid);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("method", "getRequestVerifiCodeM");
        params.put("mobile", editPhonenumber.getText().toString());
        params.put("verfiCodeType", "1");
        params.put("verificationCode", editCheckimage.getText().toString());
//        tHandler.postHeaderJson(url, tHeaders, params, 0);
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://www.ipanda.com/kehuduan/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        PersonalregistService personalregistService = build.create(PersonalregistService.class);
        personalregistService.getRegistData(url, tHeaders, params).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        Log.d("MobelRegistFragment", string);
//                        Message message = handler.obtainMessage(7, string);
//                        handler.sendMessage(message);
                    }
                });
    }

    //手机号重置请求
    private void sendPhoneRegistHttp() {
        String url = "https://reg.cntv.cn/regist/resetPasswdByMobile.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";
        HashMap<String, String> tHeader = new HashMap<String, String>();
        try {
            tHeader.put("Referer", URLEncoder.encode(from, "UTF-8"));
            tHeader.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            tHeader.put("Cookie",  jsessionid);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HashMap<String, String> tParams = new HashMap<String, String>();
        String tPhoneNumber = editPhonenumber.getText().toString().trim();
        String tCheckPhone = editCheckphone.getText().toString().trim();
        tParams.put("mobile", tPhoneNumber);
        tParams.put("verfiCode", tCheckPhone);
        try {

            tParams.put("passWd",editNewpssword.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tParams.put("from", from);
        tParams.put("Callback", "");
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://www.ipanda.com/kehuduan/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        PersonalregistService personalregistService = build.create(PersonalregistService.class);
        personalregistService.goResetPsw(url, tHeader, tParams)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        Log.e("ForgetPasswordActivity", string);

                        JSONObject jsonObject = new JSONObject(string);
                        if (jsonObject.has("msg")){

                            
                        }
                    }
                });
    }

    //检查手机号
    private boolean checkPhone() {
        String phoneString = editPhonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            hintPhonenumber.setText("手机号码不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneString);
        if (matcher.matches()) {
            hintPhonenumber.setText("");
            return true;
        } else {
            hintPhonenumber.setText("手机格式不正确");
            return false;
        }
    }

    /**
     * 检查验证码
     *
     * @return
     */
    private boolean checkCaptcha() {
        if (inputStream == null) {
            Toast.makeText(this, "未获取验证码", Toast.LENGTH_SHORT).show();
            return false;
        }

        String mCaptchaEditTextString = editCheckimage.getText().toString().trim();
        if (mCaptchaEditTextString.contains(" ")) {
            hintCheckimage.setText("验证码不正确");
            return false;
        }
        if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
            hintCheckimage.setText("验证码不能为空");
            return false;
        } else {
            hintCheckimage.setText("");
            return true;
        }

    }

    /**
     * 检查手机验证码
     */

    private boolean checkPhoneCheck() {
        String phonecheck = editCheckphone.getText().toString().trim();

        if (TextUtils.isEmpty(phonecheck)) {
            hintCheckphone.setText("验证码不能为空");
            return false;
        } else {
            hintCheckphone.setText(" ");
            return true;
        }
    }
}
