package com.sunny.mvppandatv.view.HomeFrag;

import com.sunny.mvppandatv.model.utils.RetrofitUtils;

/**
 * Created by 张玗 on 2018/4/8.
 */

public class HttpFactory {
    public static RetrofitUtils homeService(){
        return RetrofitUtils.getInstance();

    }
}
