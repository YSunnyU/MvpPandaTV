package com.sunny.mvppandatv.service;


import com.sunny.mvppandatv.bean.HomeNewsMess;
import com.sunny.mvppandatv.view.HomeFrag.HomeCCTVBean;
import com.sunny.mvppandatv.view.HomeFrag.HomePandaLastBean;
import com.sunny.mvppandatv.view.HomeFrag.HomeThreeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;


/**
 * Created by 张玗 on 2018/4/12.
 */

public interface Service {
    //声明的全部是抽象方法
    //怎么来声明网络请求 业务方法

    //存放的是URL地址 该URL地址不包含服务器地址 仅包含业务方法的地址(接口地址)
    //接口地址不能以/开头
    @GET("PAGE14501749764071042/index.json")
    Observable<HomeNewsMess> getHomeModelMess();
    @GET
    Observable<HomeThreeBean> getHomePandaEyeData(@Url String url);
    @GET
    Observable<HomeCCTVBean> getHomeCCTVData(@Url String url);
    @GET
    Observable<HomePandaLastBean> getHomePandaLastBeanData(@Url String url);


}
