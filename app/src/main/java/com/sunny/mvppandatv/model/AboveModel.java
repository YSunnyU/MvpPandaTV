package com.sunny.mvppandatv.model;

import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.service.Service;

/**
 * Created by 张玗 on 2018/4/12.
 */

public class AboveModel implements InModel{
    @Override
    public Service sendData() {
        return RetrofitUtils.getInstance().getHomeService();
    }

        /*homeService.getHomeModelMess()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeNewsMess>() {
                    @Override
                    public void accept(HomeNewsMess homeNewsMess) throws Exception {
                        HomeNewsMess.DataBean data = homeNewsMess.getData();

                    }
                });*/


}
