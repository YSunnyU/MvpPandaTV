package com.sunny.mvppandatv.view.HomeFrag;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.bean.HomeNewsMess;
import com.sunny.mvppandatv.contract.Contract;
import com.sunny.mvppandatv.presenter.HomePresenter;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements Contract.HomeView {

    public Contract.HomePresenter homePresenter;
    @Bind(R.id.home_frag_recy_view)
    RecyclerView homeFragRecyView;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        homePresenter=new HomePresenter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        homeFragRecyView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        homePresenter.sendData();
    }



    @Override
    public void showHomeFragView(HomeNewsMess homeNewsMess) {
        HomeNewsMess.DataBean data = homeNewsMess.getData();
        HomeFragAboveAdapter homeFragAboveAdapter = new HomeFragAboveAdapter(getContext(), data);
        homeFragRecyView.setAdapter(homeFragAboveAdapter);
    }
}
