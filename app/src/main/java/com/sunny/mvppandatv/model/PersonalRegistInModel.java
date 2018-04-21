package com.sunny.mvppandatv.model;

import com.sunny.mvppandatv.callback.MyNetworkCallback;

/**
 * Created by 张玗 on 2018/4/17.
 */

public interface PersonalRegistInModel {
    void getRegist(String mobel, String verfiCode, String password, MyNetworkCallback myNetworkCallback);
}
