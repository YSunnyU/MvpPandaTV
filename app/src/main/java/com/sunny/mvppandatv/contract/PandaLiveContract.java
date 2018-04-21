package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.view.PandaLive.PandaLiveAboveBean;

/**
 * Created by 张玗 on 2018/4/16.
 */

public interface PandaLiveContract {
    interface PandaLiveAboveInView{
        void showPandaLiveAboveData(PandaLiveAboveBean pandaLiveAboveBean);

    }
    interface PandaLiveAboveInPresenter{
        void sendData();
    }
}
