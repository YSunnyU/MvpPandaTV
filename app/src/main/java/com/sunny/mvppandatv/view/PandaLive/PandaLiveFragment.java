package com.sunny.mvppandatv.view.PandaLive;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.contract.PandaLiveContract;
import com.sunny.mvppandatv.presenter.PandaLivePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class PandaLiveFragment extends BaseFragment implements PandaLiveContract.PandaLiveAboveInView {


    @Bind(R.id.fragment_panda_live_tabLayout)
    TabLayout fragmentPandaLiveTabLayout;
    @Bind(R.id.fragment_panda_live_viewPager)
    ViewPager fragmentPandaLiveViewPager;
    private List<PandaLiveSonFragment> sonFrag_list;
    private ArrayList<String> mTitle;
    private PandaLiveContract.PandaLiveAboveInPresenter pandaLiveInPresenter;
    private List<PandaLiveAboveBean.TablistBean> tablist;
    private PandaLiveSonFragment pandaLiveSonFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panda_live;
    }

    @Override
    protected void init() {
        sonFrag_list = new ArrayList<>();
        mTitle=new ArrayList<>();
        pandaLiveInPresenter = new PandaLivePresenter(this);

    }

    @Override
    protected void initData() {
        pandaLiveInPresenter.sendData();
    }


    @Override
    public void showPandaLiveAboveData(PandaLiveAboveBean pandaLiveAboveBean) {
        tablist = pandaLiveAboveBean.getTablist();
        /*pandaLiveSonFragment = new PandaLiveSonFragment();
        sonFrag_list.add(pandaLiveSonFragment);
        Bundle bundle = new Bundle();
        bundle.putString("url", tablist.get(0).getUrl());
        pandaLiveSonFragment.setArguments(bundle);*/
        for (int i = 0; i <tablist.size() ; i++) {
            Log.e("PandaLiveFragment", tablist.get(i).getTitle());
            mTitle.add(tablist.get(i).getTitle());
            pandaLiveSonFragment = new PandaLiveSonFragment();
            Bundle bundle2 = new Bundle();
            bundle2.putString("url", tablist.get(i).getUrl());
            pandaLiveSonFragment.setArguments(bundle2);
            sonFrag_list.add(pandaLiveSonFragment);


        }
     PandaLiveAdapter pandaLiveAdapter = new PandaLiveAdapter(getChildFragmentManager(), sonFrag_list, mTitle);
        fragmentPandaLiveViewPager.setAdapter(pandaLiveAdapter);
        fragmentPandaLiveTabLayout.setupWithViewPager(fragmentPandaLiveViewPager);

    }
}
