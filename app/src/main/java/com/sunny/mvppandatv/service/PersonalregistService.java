package com.sunny.mvppandatv.service;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Sunny on 2018/4/18.
 */

public interface PersonalregistService {
//    用户短信验证码
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> getRegistData(@Url String url, @HeaderMap Map<String,String> map, @FieldMap Map<String,String> parms);
//   用户注册请求服务器
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> goToRegist(@Url String url, @HeaderMap Map<String,String> map, @FieldMap Map<String,String> parms);

//    用户重置密码
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> goResetPsw(@Url String url, @HeaderMap Map<String,String> map, @FieldMap Map<String,String> parms);


}
