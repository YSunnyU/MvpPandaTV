package com.sunny.mvppandatv.presenter;

import com.sunny.mvppandatv.contract.PandaLiveChildOneContract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.PandaLive.PandaLiveSonBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class PandaLiveChildOnePresenter implements PandaLiveChildOneContract.PandaLiveChildOneInPresenter{
    public PandaLiveChildOneContract.PandaLiveChildOneInView pandaLiveChildOneInView;
    public PandaLiveChildOnePresenter(PandaLiveChildOneContract.PandaLiveChildOneInView pandaLiveChildOneInView){
        this.pandaLiveChildOneInView=pandaLiveChildOneInView;
    }
    @Override
    public void sendData(String url) {
        RetrofitUtils.getInstance().getPandaLiveService()
                .getPandaLiveSonData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PandaLiveSonBean>() {
                    @Override
                    public void accept(PandaLiveSonBean pandaLiveSonBean) throws Exception {
                        pandaLiveChildOneInView.showPandaLiveSonData(pandaLiveSonBean);
                    }
                });
    }
}
