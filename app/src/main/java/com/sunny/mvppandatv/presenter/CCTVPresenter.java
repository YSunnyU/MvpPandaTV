package com.sunny.mvppandatv.presenter;

import android.util.Log;

import com.sunny.mvppandatv.contract.CCTVContract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.CCTVFrag.CCTVBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/13.
 */

public class CCTVPresenter implements CCTVContract.CCTVInPresenter{
    public CCTVContract.CCTVInView cctvInView;
    public CCTVPresenter(CCTVContract.CCTVInView cctvInView){
        this.cctvInView=cctvInView;
    }
    @Override
    public void sendData() {
        RetrofitUtils.getInstance().getCCTVService()
                .getCCTVData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CCTVBean>() {
                    @Override
                    public void accept(CCTVBean cctvBean) throws Exception {
                        cctvInView.showCCTVFragData(cctvBean);
                        Log.e("CCTVPresenter", "cctvBean.getTablist().size():" + cctvBean.getTablist().size());
                    }
                });
    }


}
