package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.view.CCTVFrag.CCTVBean;

/**
 * Created by 张玗 on 2018/4/13.
 */

public interface CCTVContract {
    interface CCTVInView{
        void showCCTVFragData(CCTVBean cctvBean);

    }
    interface CCTVInPresenter{
        void sendData();
    }
}
