package com.sunny.mvppandatv.presenter;

import android.util.Log;

import com.sunny.mvppandatv.contract.PandaLiveSonContract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;
import com.sunny.mvppandatv.view.PandaLive.PandaLiveBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class PandaLiveSonPresenter implements PandaLiveSonContract.PandaLiveSonInPresenter{
    public PandaLiveSonContract.PandaLiveSonInView pandaLiveSonInView;
    public PandaLiveSonPresenter (PandaLiveSonContract.PandaLiveSonInView pandaLiveSonInView){
        this.pandaLiveSonInView=pandaLiveSonInView;
    }

    @Override
    public void sendData(String url) {
        RetrofitUtils.getInstance().getPandaLiveService().getPandaLiveData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PandaLiveBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull PandaLiveBean pandaLiveBean) {
                        pandaLiveSonInView.showPandaLiveSonData(pandaLiveBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
