package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.view.LiveChina.ChinaLiveBean;

/**
 * Created by 张玗 on 2018/4/14.
 */

public interface LiveChinaFrag_ChinaLiveContract {
    interface ChinaLivaInView{
        void showChinaLiveData(ChinaLiveBean chinaLiveBean);
    }
    interface ChinaLiveInPresenter{

        void showChinaLiveData(String url);
    }
}
