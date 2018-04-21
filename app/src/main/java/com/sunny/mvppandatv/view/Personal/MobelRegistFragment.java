package com.sunny.mvppandatv.view.Personal;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.service.PersonalregistService;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class MobelRegistFragment extends BaseFragment implements View.OnFocusChangeListener {


    @Bind(R.id.num_edit)
    EditText numEdit;
    @Bind(R.id.num_warn)
    TextView numWarn;
    @Bind(R.id.img_yzm)
    EditText imgYzm;
    @Bind(R.id.img_yzm_img)
    ImageView imgYzmImg;
    @Bind(R.id.img_yzm_warn)
    TextView imgYzmWarn;
    @Bind(R.id.edit_yzm)
    EditText editYzm;
    @Bind(R.id.edit_yzm_btn)
    Button editYzmBtn;
    @Bind(R.id.edit_yzm_warn)
    TextView editYzmWarn;
    @Bind(R.id.edit_pws)
    EditText editPws;
    @Bind(R.id.edit_pws_warn)
    TextView editPwsWarn;

    @Bind(R.id.mobel_xieyi_check)
    CheckBox mobelXieyiCheck;
    @Bind(R.id.personal_reg_xieyi_view)
    TextView personalRegXieyiView;
    @Bind(R.id.mobel_btn_register)
    TextView mobelBtnRegister;
    private String JSESSIONID;
    private Bitmap bitmap;
    private OkHttpClient okHttpClient;
    private InputStream inputStream;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mobel_regist;
    }

    @Override
    protected void init() {
        numEdit.setOnFocusChangeListener(this);
        imgYzm.setOnFocusChangeListener(this);
        editYzm.setOnFocusChangeListener(this);
        editPws.setOnFocusChangeListener(this);
        sendCaptchaHttpMessage();
    }

    @Override
    protected void initData() {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            if (msg.what == 7) {
                String mess = (String) msg.obj;
                Log.e("MobelRegistFragment", mess);

            }
        }
    };

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.num_edit:
                numWarn.setText("");
                break;
            case R.id.img_yzm:
                if (!checkPhone())
                    return;
                imgYzmWarn.setText("");
                break;
            case R.id.edit_yzm:
//                图片验证码判断
                if (!checkCaptcha())
                    return;
                editYzmWarn.setText("");
                break;
            case R.id.edit_pws:
//                短信验证码判断
                if (!checkPhoneCheck()) {

                    return;
                }

                break;
        }
    }

    @OnClick({R.id.mobel_xieyi_check, R.id.personal_reg_xieyi_view, R.id.mobel_btn_register, R.id.img_yzm_img, R.id.edit_yzm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.img_yzm_img:
                sendCaptchaHttpMessage();
                break;

            case R.id.edit_yzm_btn:
                sendCaptchaSmsHttpMessage();
                break;


            case R.id.mobel_xieyi_check:


                numEdit.clearFocus();
                imgYzm.clearFocus();
                editYzm.clearFocus();

                String phone = numEdit.getText().toString().trim();
                String tImageChcek = imgYzm.getText().toString().trim();

                if (TextUtils.isEmpty(tImageChcek)) {
                    imgYzmWarn.setText("图片验证码输入有误");
                }
                //                手机号
                if (!checkPhone())
                    return;
//                图片验证码
                if (!checkCaptcha()) {
                    return;
                } else {

                    numWarn.setText("");
                    imgYzmWarn.setText("");

                    sendCaptchaSmsHttpMessage();
                }
                break;
            case R.id.personal_reg_xieyi_view:

                break;
            case R.id.mobel_btn_register:
//                sendPhoneRegistHttp();
//                手机号判断
                if (!checkPhone())
                    return;

//                密码判断
                if (!checkPasswork()) {
                    return;
                }
//                短信验证码判断
                if (!checkPhoneCheck()) {

                    return;
                }
//                图片验证码
                if (!checkCaptcha()) {
                    return;
                }
//                检查协议复选框
                if (!checkXieyi()) {
                    return;
                }
                //                注册
                sendPhoneRegistHttp();
                break;
        }
    }

    public void setOnFocusChangeListener() {
        numEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String phoneString = numEdit.getText().toString().trim();
                if (TextUtils.isEmpty(phoneString)) {
                    numWarn.setText("手机号码不能为空");
                }
                Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
                Matcher matcher = pattern.matcher(phoneString);
                if (matcher.matches()) {
                    numWarn.setText("");
                } else {
                    numWarn.setText("手机格式不正确");
                }
            }
        });


        editYzm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (inputStream == null) {
                    Toast.makeText(getActivity(), "未获取验证码", Toast.LENGTH_SHORT).show();

                }

                String mCaptchaEditTextString = editYzm.getText().toString().trim();
                if (mCaptchaEditTextString.contains(" ")) {
                    imgYzmWarn.setText("验证码不正确");
                }
                if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
                    imgYzmWarn.setText("验证码不能为空");
                } else {
                    imgYzmWarn.setText("");
                }
            }
        });

        editYzm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String phonecheck = editYzm.getText().toString().trim();

                if (TextUtils.isEmpty(phonecheck)) {
                    editYzmWarn.setText("验证码不能为空");

                } else {
                    editYzmWarn.setText(" ");
                }
            }
        });

    }

    /**
     * 获取图片验证码
     */
    private void sendCaptchaHttpMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String from = "http://reg.cntv.cn/simple/verificationCode.action";
                okHttpClient = new OkHttpClient.Builder().build();
                Request build1 = new Request.Builder().url(from).build();
                okHttpClient.newCall(build1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Headers headers = response.headers();
                        JSESSIONID = headers.get("Set-Cookie");
                        inputStream = response.body().byteStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imgYzmImg.setImageBitmap(bitmap);
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
            tHeaders.put("Cookie", JSESSIONID);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("method", "getRequestVerifiCodeM");
        params.put("mobile", numEdit.getText().toString());
        params.put("verfiCodeType", "1");
        params.put("verificationCode", imgYzm.getText().toString());
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




    /***
     * 手机号注册的请求
     *
     */
    private void sendPhoneRegistHttp() {
        String url = "https://reg.cntv.cn/regist/mobileRegist.do";
        HashMap<String, String> tHeader = new HashMap<String, String>();
        try {

            tHeader.put("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
            tHeader.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
//            tHeader.put("Cookie",JSESSIONID);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HashMap<String, String> tParams = new HashMap<String, String>();
        String tPhoneNumber = numEdit.getText().toString().trim();
        String tCheckPhone = editYzm.getText().toString().trim();
        String tPassWord = editPws.getText().toString();
        tParams.put("method", "saveMobileRegisterM");
        tParams.put("mobile", tPhoneNumber);
        tParams.put("verfiCode", tCheckPhone);
        tParams.put("passWd", tPassWord);
        tParams.put("verfiCodeType", "1");
        try {
            tParams.put("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://www.ipanda.com/kehuduan/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        PersonalregistService personalregistService = build.create(PersonalregistService.class);
        personalregistService.goToRegist(url, tHeader, tParams)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        Log.d("MobelRegistFragment", string);
                    }
                });


    }

    //检查手机号
    private boolean checkPhone() {
        String phoneString = numEdit.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            numWarn.setText("手机号码不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(phoneString);
        if (matcher.matches()) {
            numWarn.setText("");
            return true;
        } else {
            numWarn.setText("手机格式不正确");
            return false;
        }
    }


    /**
     * 检查密码
     *
     * @return
     */
    private boolean checkPasswork() {
        String editpasswordsString = editPws.getText().toString();

        if (TextUtils.isEmpty(editpasswordsString)) {
            editPwsWarn.setText("密码不能为空");
            return false;
        } else if (editpasswordsString.length() < 6 || editpasswordsString.length() > 16) {
            editPwsWarn.setText("密码仅限6-16个字符");
            return false;
        } else {
            editPwsWarn.setText("");
            return true;
        }
    }

    /**
     * 检查手机验证码
     */

    private boolean checkPhoneCheck() {
        String phonecheck = editYzm.getText().toString().trim();

        if (TextUtils.isEmpty(phonecheck)) {
            editYzmWarn.setText("验证码不能为空");
            return false;
        } else {
            editYzmWarn.setText(" ");
            return true;
        }
    }

    /**
     * 检查验证码
     *
     * @return
     */
    private boolean checkCaptcha() {
        if (inputStream == null) {
            Toast.makeText(getActivity(), "未获取验证码", Toast.LENGTH_SHORT).show();
            return false;
        }

        String mCaptchaEditTextString = imgYzm.getText().toString().trim();
        if (mCaptchaEditTextString.contains(" ")) {
            imgYzmWarn.setText("验证码不正确");
            return false;
        }
        if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
            imgYzmWarn.setText("验证码不能为空");
            return false;
        } else {
            imgYzmWarn.setText("");
            return true;
        }

    }


    /**
     * 检查协议
     *
     * @return
     */
    private boolean checkXieyi() {


        if (!mobelXieyiCheck.isChecked()) {
            personalRegXieyiView.setText("未勾选《央视网网络服务使用协议》");
            return false;
        } else {
            personalRegXieyiView.setText("");
            return true;
        }

    }


}
