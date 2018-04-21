package com.sunny.mvppandatv.presenter;

import com.sunny.mvppandatv.contract.PandaLiveContract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.PandaLive.PandaLiveAboveBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class PandaLivePresenter implements PandaLiveContract.PandaLiveAboveInPresenter{

    public  PandaLiveContract.PandaLiveAboveInView pandaLiveInView;
    public PandaLivePresenter(PandaLiveContract.PandaLiveAboveInView pandaLiveInView){
        this.pandaLiveInView=pandaLiveInView;

    }
    @Override
    public void sendData() {
        RetrofitUtils.getInstance().getPandaLiveService().getPandaLiveAboveData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PandaLiveAboveBean>() {
                    @Override
                    public void accept(PandaLiveAboveBean pandaLiveAboveBean) throws Exception {
                        pandaLiveInView.showPandaLiveAboveData(pandaLiveAboveBean);

                    }
                });
    }
}
