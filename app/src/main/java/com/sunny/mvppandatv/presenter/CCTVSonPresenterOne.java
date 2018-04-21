package com.sunny.mvppandatv.presenter;

import com.sunny.mvppandatv.contract.CCTVSonContractOne;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.CCTVFrag.CctvChaneLiveBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/15.
 */

public class CCTVSonPresenterOne implements CCTVSonContractOne.CCTVInPresenter{

    public CCTVSonContractOne.CCTVInView cctvInView;
    public CCTVSonPresenterOne (CCTVSonContractOne.CCTVInView cctvInView){
        this.cctvInView=cctvInView;
    }
    @Override
    public void showCCTV(String urlString) {
        RetrofitUtils.getInstance().getCCTVService()
                .getCCTV(urlString)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CctvChaneLiveBean>() {
                    @Override
                    public void accept(CctvChaneLiveBean cctvChaneLiveBean) throws Exception {
                        cctvInView.showCCTVData(cctvChaneLiveBean);
                    }
                });

    }
}
