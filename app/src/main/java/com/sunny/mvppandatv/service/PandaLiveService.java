package com.sunny.mvppandatv.service;

import com.sunny.mvppandatv.view.PandaLive.PandaLiveAboveBean;
import com.sunny.mvppandatv.view.PandaLive.PandaLiveBean;
import com.sunny.mvppandatv.view.PandaLive.PandaLiveSonBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 张玗 on 2018/4/16.
 */

public interface PandaLiveService {


//    http://www.ipanda.com/kehuduan/PAGE14501772263221982/index.json

    @GET("PAGE14501772263221982/index.json")
    Observable<PandaLiveAboveBean> getPandaLiveAboveData();
//    http://www.ipanda.com/kehuduan/PAGE14501769230331752/PAGE14501787896813312/index.json
    @GET
    Observable<PandaLiveBean> getPandaLiveData(@Url String urlString);

    @GET
    Observable<PandaLiveSonBean> getPandaLiveSonData(@Url String urlString);
}
