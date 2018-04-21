package com.sunny.mvppandatv.presenter;

import com.sunny.mvppandatv.contract.HuDongContract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.HuDong.HuDongBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class HuDongPresenter implements HuDongContract.HuDongInPresenter{
    HuDongContract.HuDongInView huDongInView;
    public HuDongPresenter (HuDongContract.HuDongInView huDongInView){
        this.huDongInView=huDongInView;
    }
    @Override
    public void sendData() {
        RetrofitUtils.getInstance().getHuDongService()
                .getHuDongData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HuDongBean>() {
                    @Override
                    public void accept(HuDongBean huDongBean) throws Exception {
                        huDongInView.showHuDongData(huDongBean);
                    }
                });
    }
}
