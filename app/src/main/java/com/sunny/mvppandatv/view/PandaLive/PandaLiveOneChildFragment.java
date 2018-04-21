package com.sunny.mvppandatv.view.PandaLive;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.contract.PandaLiveChildOneContract;
import com.sunny.mvppandatv.presenter.PandaLiveChildOnePresenter;

import java.util.List;

import butterknife.Bind;


public class PandaLiveOneChildFragment extends BaseFragment implements PandaLiveChildOneContract.PandaLiveChildOneInView{


    @Bind(R.id.panda_live_child_one_frag_recyclerView)
    RecyclerView pandaLiveChildOneFragRecyclerView;
    private PandaLiveChildOneContract.PandaLiveChildOneInPresenter pandaLiveChildOneInPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panda_live_one_child;
    }

    @Override
    protected void init() {
        pandaLiveChildOneInPresenter = new PandaLiveChildOnePresenter(this);
        Bundle arguments = getArguments();
        String url = arguments.getString("url");
        Log.d("PandaLiveChildOneFragme", url);
        pandaLiveChildOneInPresenter.sendData(url);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showPandaLiveSonData(PandaLiveSonBean pandaLiveSonBean) {
        List<PandaLiveSonBean.ListBean> list = pandaLiveSonBean.getList();
        PandaLiveChildOneAdapter pandaLiveChildOneAdapter = new PandaLiveChildOneAdapter(list,getContext());
        pandaLiveChildOneFragRecyclerView.setAdapter(pandaLiveChildOneAdapter);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        pandaLiveChildOneFragRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
