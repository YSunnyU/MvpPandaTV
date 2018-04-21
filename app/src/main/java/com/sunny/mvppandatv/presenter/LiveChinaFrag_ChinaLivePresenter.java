package com.sunny.mvppandatv.presenter;

import com.sunny.mvppandatv.contract.LiveChinaFrag_ChinaLiveContract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.LiveChina.ChinaLiveBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/14.
 */

public class LiveChinaFrag_ChinaLivePresenter implements LiveChinaFrag_ChinaLiveContract.ChinaLiveInPresenter {
    public LiveChinaFrag_ChinaLiveContract.ChinaLivaInView chinaLivaInView;
    public LiveChinaFrag_ChinaLivePresenter (LiveChinaFrag_ChinaLiveContract.ChinaLivaInView chinaLivaInView){
        this.chinaLivaInView=chinaLivaInView;

    }
    @Override
    public void showChinaLiveData(String url) {
        RetrofitUtils.getInstance()
                .getLiveChinaService()
                .getChinaLiveData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChinaLiveBean>() {
                    @Override
                    public void accept(ChinaLiveBean chinaLiveBean) throws Exception {
                        chinaLivaInView.showChinaLiveData(chinaLiveBean);
                    }
                });
    }
}
