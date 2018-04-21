package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.view.CCTVFrag.CctvChaneLiveBean;

/**
 * Created by 张玗 on 2018/4/15.
 */

public interface CCTVSonContractOne {
    interface CCTVInView{

        void showCCTVData(CctvChaneLiveBean listBeen);

    }
    interface CCTVInPresenter{

        void showCCTV(String urlString);
    }
}
