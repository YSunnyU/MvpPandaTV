package com.sunny.mvppandatv.service;

import com.sunny.mvppandatv.view.PandaEyeFrag.PandaEyeBean;
import com.sunny.mvppandatv.view.PandaEyeFrag.PandaEyeListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by 张玗 on 2018/4/13.
 */

public interface PandaEyeService {
//    http://www.ipanda.com/kehuduan/PAGE14503485387528442/index.json
    @GET("PAGE14503485387528442/index.json")
    Observable<PandaEyeBean> getPandaEyeData();
    @GET
    Observable<PandaEyeListBean> getPandaEyeListData(@Url String path);
}
