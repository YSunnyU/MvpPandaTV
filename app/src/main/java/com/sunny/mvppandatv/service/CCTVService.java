package com.sunny.mvppandatv.service;

import com.sunny.mvppandatv.view.CCTVFrag.CCTVBean;
import com.sunny.mvppandatv.view.CCTVFrag.CctvChaneLiveBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 张玗 on 2018/4/13.
 */

public interface CCTVService {
//    http://www.ipanda.com/kehuduan/PAGE14501777938482582/index.json
    @GET("PAGE14501777938482582/index.json")
    Observable<CCTVBean> getCCTVData();

    @GET
    Observable<CctvChaneLiveBean> getCCTV(@Url String urlString);
}
