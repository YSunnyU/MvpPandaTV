package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.view.LiveChina.LiveChinaBean;

/**
 * Created by 张玗 on 2018/4/14.
 */

public interface LiveChinaContract {
    interface LiveChinaInView{
        void showLiveChinaData(LiveChinaBean liveChinaBean);

    }
    interface LiveChinaInPresenter{
        void sendData();

    }
}
