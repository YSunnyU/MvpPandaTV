package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.view.PandaLive.PandaLiveSonBean;

/**
 * Created by 张玗 on 2018/4/16.
 */

public interface PandaLiveChildOneContract {
    interface PandaLiveChildOneInView{
        void showPandaLiveSonData(PandaLiveSonBean pandaLiveSonBean);

    }
    interface PandaLiveChildOneInPresenter{
        void sendData(String url);
    }
}
