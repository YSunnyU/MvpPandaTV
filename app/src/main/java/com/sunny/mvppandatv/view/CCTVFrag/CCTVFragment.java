package com.sunny.mvppandatv.view.CCTVFrag;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.contract.CCTVContract;
import com.sunny.mvppandatv.presenter.CCTVPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class CCTVFragment extends BaseFragment implements CCTVContract.CCTVInView {


    @Bind(R.id.fragment_cctv_tabLayout)
    TabLayout fragmentCctvTabLayout;
    @Bind(R.id.fragment_cctv_viewPager)
    ViewPager fragmentCctvViewPager;
    private CCTVContract.CCTVInPresenter cctvInPresenter;
    private List<CCTVBean.TablistBean> title_list;

    private List<CCTVSonFragment> CCTVSonfragments_list;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cctv;
    }

    @Override
    protected void init() {
        cctvInPresenter = new CCTVPresenter(this);
        title_list = new ArrayList<>();
        CCTVSonfragments_list=new ArrayList<>();
        fragmentCctvTabLayout.setupWithViewPager(fragmentCctvViewPager);
    }

    @Override
    protected void initData() {
        cctvInPresenter.sendData();

    }


    @Override
    public void showCCTVFragData(CCTVBean cctvBean) {
        List<CCTVBean.TablistBean> tablist = cctvBean.getTablist();
        Log.e("CCTVFragment", "tablist.size():" + tablist.size());
        for (CCTVBean.TablistBean tablistBean : tablist) {
            CCTVSonFragment cctvSonFragment = new CCTVSonFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", tablistBean.getUrl());
            cctvSonFragment.setArguments(bundle);
            CCTVSonfragments_list.add(cctvSonFragment);
            title_list.add(tablistBean);
        }
        CCTVAboveAdapter cctvAboveAdapter = new CCTVAboveAdapter(getActivity().getSupportFragmentManager(), title_list, CCTVSonfragments_list);
        fragmentCctvViewPager.setAdapter(cctvAboveAdapter);
    }


}
