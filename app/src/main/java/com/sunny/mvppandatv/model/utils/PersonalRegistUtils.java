package com.sunny.mvppandatv.model.utils;

import com.sunny.mvppandatv.callback.MyNetworkCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Sunny on 2018/4/17.
 */

public class PersonalRegistUtils {

    /**
     * 单例设计模式
     * 1、构造函数私有化
     * 2、提供一个公有的、静态、返回值类型是当前类的对象的方法
     */
    private static PersonalRegistUtils okHttpUtils;
    private OkHttpClient okHttpClient;//单例OKhttpClient对象的好处：1、避免重复创建销毁对象造成内存开销 2、OKhttp的高并发的优点没有被发挥出来

    private PersonalRegistUtils() {
        okHttpClient = new OkHttpClient.Builder().build();
    }

    /**
     * 懒汉式
     *
     * @return
     */
    public static PersonalRegistUtils getInstance() {
        if (okHttpUtils == null)
            okHttpUtils = new PersonalRegistUtils();
        return okHttpUtils;
    }


    public void doPost(String url, Map<String, String> params, HashMap<String, String> tHeader, final MyNetworkCallback callback) {
        Request.Builder builder1 = new Request.Builder();

        if (tHeader != null && tHeader.size() > 0) {
            Set<String> key = tHeader.keySet();
            for (String keys : key) {
                String value = tHeader.get(keys);
                builder1.addHeader(keys, value);
            }


        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            Set<String> keySet = params.keySet();
            for (String keyss : keySet) {
                String value = params.get(keyss);
                formBuilder.add(keyss,value);

            }
        }

        Request request = builder1.post(formBuilder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onErrorMess(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onRightMess(response.body().string());
            }
        });

    }
}
