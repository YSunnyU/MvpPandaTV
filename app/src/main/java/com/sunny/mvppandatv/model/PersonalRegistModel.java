package com.sunny.mvppandatv.model;

import com.sunny.mvppandatv.callback.MyNetworkCallback;
import com.sunny.mvppandatv.model.utils.PersonalRegistUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张玗 on 2018/4/17.
 */

public class PersonalRegistModel implements PersonalRegistInModel{
    @Override
    public void getRegist(String mobel, String verfiCode, String passWd, MyNetworkCallback myNetworkCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("mobel",mobel);
        map.put("verfiCode",verfiCode);
        map.put("passWd",passWd);
        map.put("verfiCodeType", "1");
        map.put("method", "saveMobileRegisterM");
        try {
            map.put("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HashMap<String, String> tHeader = new HashMap<String, String>();
        try {

            tHeader.put("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
            tHeader.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PersonalRegistUtils.getInstance().doPost("http://reg.cntv.cn/regist/getVerifiCode.action",map,tHeader,myNetworkCallback);
    }
}
