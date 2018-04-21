package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.view.HuDong.HuDongBean;

/**
 * Created by 张玗 on 2018/4/16.
 */

public interface HuDongContract {
    interface HuDongInView{
        void showHuDongData(HuDongBean huDongBean);
    }
    interface HuDongInPresenter{
        void sendData();
    }
}
