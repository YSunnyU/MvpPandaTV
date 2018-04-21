package com.sunny.mvppandatv.contract;


import com.sunny.mvppandatv.view.PandaEyeFrag.PandaEyeBean;

/**
 * Created by 张玗 on 2018/4/13.
 */

public interface PandaEyeContract {
    interface PandaEyeInView{
        void showPandaEyeData(PandaEyeBean pandaEyeBean);
    }
    interface PandaEyeInPresenter{
        void sendData();
    }
}
