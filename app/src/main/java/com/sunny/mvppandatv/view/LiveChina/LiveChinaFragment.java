package com.sunny.mvppandatv.view.LiveChina;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.contract.LiveChinaContract;
import com.sunny.mvppandatv.presenter.LiveChinaPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class LiveChinaFragment extends BaseFragment implements LiveChinaContract.LiveChinaInView {


    @Bind(R.id.fragment_live_china_tabLayout)
    TabLayout fragmentLiveChinaTabLayout;
    @Bind(R.id.fragment_live_china_viewPager)
    ViewPager fragmentLiveChinaViewPager;

    private List<ChinaLiveFragment> fragments_list;
    private List<LiveChinaBean.TablistBean> titles_list;
    private LiveChinaContract.LiveChinaInPresenter liveChinaInPresenter;
    private LiveChinaFragAdapter liveChinaFragAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_china;
    }

    @Override
    protected void init() {
        liveChinaInPresenter = new LiveChinaPresenter(this);
        this.titles_list = new ArrayList<>();
        this.fragments_list = new ArrayList<>();
        fragmentLiveChinaTabLayout.setupWithViewPager(fragmentLiveChinaViewPager);
    }

    @Override
    protected void initData() {
        liveChinaInPresenter.sendData();
    }

    @Override
    public void showLiveChinaData(LiveChinaBean liveChinaBean) {
        List<LiveChinaBean.TablistBean> tablist = liveChinaBean.getTablist();
        Log.d("LiveChinaFragment", "tablist.size():" + tablist.size());
        for (LiveChinaBean.TablistBean liveBean : tablist) {
            ChinaLiveFragment chinaLiveFragment = new ChinaLiveFragment();
            Bundle bundle = new Bundle();
            bundle.putString("path", liveBean.getUrl());
            chinaLiveFragment.setArguments(bundle);
            fragments_list.add(chinaLiveFragment);
            titles_list.add(liveBean);
        }
        liveChinaFragAdapter = new LiveChinaFragAdapter(getActivity().getSupportFragmentManager(), fragments_list, titles_list);
        fragmentLiveChinaViewPager.setAdapter(liveChinaFragAdapter);
    }


    /*@Override
    public void showLiveChianData(LiveChinaBean liveChinaBean) {
        List<LiveChinaBean.TablistBean> tablist = liveChinaBean.getTablist();
        Log.d("LiveChinaFragment", "234567890-098977665365768p7tiuiugut78t87o");
        *//*for (LiveChinaBean.TablistBean liveBean : tablist) {
            ChinaLiveFragment chinaLiveFragment = new ChinaLiveFragment();
            Bundle bundle = new Bundle();

            bundle.putString("url", liveBean.getUrl());
            chinaLiveFragment.setArguments(bundle);
            fragments_list.add(chinaLiveFragment);
            titles_list.add(liveBean);
        }*//*

        liveChinaFragAdapter = new LiveChinaFragAdapter(getActivity().getSupportFragmentManager(), fragments_list, titles_list);
        fragmentLiveChinaViewPager.setAdapter(liveChinaFragAdapter);
//        liveChinaFragAdapter.notifyDataSetChanged();
        *//*for (LiveChinaBean.TablistBean tablistBean : tablist) {
            ChinaLiveFragment chinaLiveFragment = new ChinaLiveFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", tablist.get(0).getUrl());
            chinaLiveFragment.setArguments(bundle);
//            fragments.add(chinaLiveFragment);
//            titles.add(liveBean);
        }*//*
    }*/


}
