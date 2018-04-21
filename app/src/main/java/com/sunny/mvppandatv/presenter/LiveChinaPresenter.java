package com.sunny.mvppandatv.presenter;

import com.sunny.mvppandatv.contract.LiveChinaContract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.LiveChina.LiveChinaBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/14.
 */

public class LiveChinaPresenter implements LiveChinaContract.LiveChinaInPresenter{
    public LiveChinaContract.LiveChinaInView liveChinaInView;
    public LiveChinaPresenter (LiveChinaContract.LiveChinaInView liveChianInView){
        this.liveChinaInView=liveChianInView;
    }
    @Override
    public void sendData() {
        RetrofitUtils.getInstance().getLiveChinaService()
                .getLiveChinaData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LiveChinaBean>() {
                    @Override
                    public void accept(LiveChinaBean liveChinaBean) throws Exception {
                        liveChinaInView.showLiveChinaData(liveChinaBean);
                        //Log.e("LiveChinaPresenter", "liveChinaBean.getAlllist().size():" + liveChinaBean.getAlllist().size());

                    }
                });

    }


}
