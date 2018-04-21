package com.sunny.mvppandatv.view.LiveChina;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.contract.LiveChinaFrag_ChinaLiveContract;
import com.sunny.mvppandatv.presenter.LiveChinaFrag_ChinaLivePresenter;

import java.util.List;

import butterknife.Bind;

public class ChinaLiveFragment extends BaseFragment implements LiveChinaFrag_ChinaLiveContract.ChinaLivaInView{

    public LiveChinaFrag_ChinaLivePresenter chinaFrag_chinaLivePresenter;
    @Bind(R.id.fragment_china_live_recyclerView)
    RecyclerView fragmentChinaLiveRecyclerView;
    private List<ChinaLiveBean.LiveBean> live;
    private String url;
    private List<ChinaLiveBean.LiveBean> liveBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_china_live;
    }

    @Override
    protected void init() {
        Bundle arguments = getArguments();
        url = arguments.getString("path");
//        url="http://www.ipanda.com/kehuduan/liebiao/fenghuanggucheng/index.json";
        chinaFrag_chinaLivePresenter=new LiveChinaFrag_ChinaLivePresenter(this);
    }

    @Override
    protected void initData() {
        chinaFrag_chinaLivePresenter.showChinaLiveData(url);
    }

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Bundle arguments = getArguments();
        String url = arguments.getString("url");
        //chinaFrag_chinaLivePresenter.showChinaLiveData(url);
        if(isVisibleToUser){
            loadData(url);
        }
    }*/
    
    @Override
    public void showChinaLiveData(ChinaLiveBean chinaLiveBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragmentChinaLiveRecyclerView.setLayoutManager(linearLayoutManager);
        liveBeanList = chinaLiveBean.getLive();
        ChinaLiveFragAdapter chinaLiveFragAdapter = new ChinaLiveFragAdapter(liveBeanList,getContext());
        fragmentChinaLiveRecyclerView.setAdapter(chinaLiveFragAdapter);
//        Log.d("ChinaLiveFragment", "chinaLiveBean.getLive():" + chinaLiveBean.getLive());
//        Log.d("ChinaLiveFragment", "chinaLiveBean.getLive().size():" + chinaLiveBean.getLive().size());

    }
}
