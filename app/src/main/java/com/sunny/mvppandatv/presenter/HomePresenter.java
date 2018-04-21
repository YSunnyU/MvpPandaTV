package com.sunny.mvppandatv.presenter;

import com.sunny.mvppandatv.bean.HomeNewsMess;
import com.sunny.mvppandatv.contract.Contract;
import com.sunny.mvppandatv.model.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张玗 on 2018/4/12.
 */

public class HomePresenter implements Contract.HomePresenter {
    public Contract.HomeView homeView;
    public HomePresenter (Contract.HomeView homeView){
        this.homeView= homeView;
    }
    @Override
    public void sendData() {
       RetrofitUtils.getInstance().getHomeService().getHomeModelMess()
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<HomeNewsMess>() {
                   @Override
                   public void accept(HomeNewsMess homeNewsMess) throws Exception {
                       homeView.showHomeFragView(homeNewsMess);
                   }
               });

    }


}
