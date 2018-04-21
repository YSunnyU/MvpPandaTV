package com.sunny.mvppandatv.presenter;

import com.sunny.mvppandatv.contract.PandaEyeContract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.PandaEyeFrag.PandaEyeBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/13.
 */

public class PandaEyePresenter implements PandaEyeContract.PandaEyeInPresenter {
    public PandaEyeContract.PandaEyeInView pandaEyeInView;
    public PandaEyePresenter (PandaEyeContract.PandaEyeInView pandaEyeInView){
        this.pandaEyeInView=pandaEyeInView;
    }
    @Override
    public void sendData() {
        RetrofitUtils.getInstance().getPandaEyeService()
                .getPandaEyeData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PandaEyeBean>() {
                    @Override
                    public void accept(PandaEyeBean pandaEyeBean) throws Exception {
                        pandaEyeInView.showPandaEyeData(pandaEyeBean);
                    }
                });
    }
}
