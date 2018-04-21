package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.view.PandaLive.PandaLiveBean;

/**
 * Created by 张玗 on 2018/4/16.
 */

public interface PandaLiveSonContract {
    interface PandaLiveSonInView{
        void showPandaLiveSonData(PandaLiveBean pandaLiveBean);

    }
    interface PandaLiveSonInPresenter{
        void sendData(String url);
    }
}
