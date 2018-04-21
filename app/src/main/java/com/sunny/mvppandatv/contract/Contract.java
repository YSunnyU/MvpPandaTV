package com.sunny.mvppandatv.contract;

import com.sunny.mvppandatv.bean.HomeNewsMess;

/**
 * Created by 张玗 on 2018/4/12.
 */

public interface Contract {
    interface HomeView{
        void showHomeFragView(HomeNewsMess homeNewsMess);

    }
    interface HomePresenter{
        void sendData();
    }
}
