package com.sunny.mvppandatv.service;

import com.sunny.mvppandatv.view.LiveChina.ChinaLiveBean;
import com.sunny.mvppandatv.view.LiveChina.LiveChinaBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 张玗 on 2018/4/14.
 */

public interface LiveChinaService {
    @GET("PAGE14501775094142282/index.json")
    Observable<LiveChinaBean> getLiveChinaData();

    @GET
    Observable<ChinaLiveBean> getChinaLiveData(@Url String urlString);


}
