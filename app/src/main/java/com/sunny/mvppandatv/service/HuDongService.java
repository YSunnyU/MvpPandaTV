package com.sunny.mvppandatv.service;

import com.sunny.mvppandatv.view.HuDong.HuDongBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 张玗 on 2018/4/16.
 */

public interface HuDongService {
    @GET("PAGE14501767715521482/index.json")
    Observable<HuDongBean> getHuDongData();
}
