package com.sunny.mvppandatv.view.CCTVFrag;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.contract.CCTVSonContractOne;
import com.sunny.mvppandatv.presenter.CCTVSonPresenterOne;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class CCTVSonFragment extends BaseFragment implements CCTVSonContractOne.CCTVInView{


    @Bind(R.id.fragment_cctvson_recyclerView)
    PullLoadMoreRecyclerView fragmentCctvsonRecyclerView;
    private CCTVSonContractOne.CCTVInPresenter cctvInPresenter;
    private Handler handler=new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cctvson;
    }

    @Override
    protected void init() {
        cctvInPresenter=new CCTVSonPresenterOne(this);
        fragmentCctvsonRecyclerView.setLinearLayout();
        fragmentCctvsonRecyclerView.setPullRefreshEnable(true);
        fragmentCctvsonRecyclerView.setPushRefreshEnable(true);
        fragmentCctvsonRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fragmentCctvsonRecyclerView.setPullLoadMoreCompleted();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        fragmentCctvsonRecyclerView.setPullLoadMoreCompleted();
                    }
                },2000);
            }
        });

    }

    /**
     * 实现Fragment懒加载的方法 该方法只有在Fragment被加载到ViewPager中才会被调用
     *
    // * @param isVisibleToUser 如果为true 代表当前Fragment处于可见状态 加载数据
     */
   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Bundle arguments = getArguments();
        String url = arguments.getString("url");
//        networkCallback.showCCTVDataCallback(url, fragmentCctvsonRecyclerView);
        cctvInPresenter.showCCTV(url);
    }*/

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        String url = arguments.getString("url");
//      networkCallback.showCCTVDataCallback(url, fragmentCctvsonRecyclerView);
        cctvInPresenter.showCCTV(url);
    }
    @Override
    public void showCCTVData(CctvChaneLiveBean listBeen) {
        List<CctvChaneLiveBean.ListBean> listBeenList = listBeen.getList();

        Log.e("CCTVSonFragment", "listBeenList.size():" + listBeenList.size());
        CCTVSonFragAdapter cctvSonFragAdapter = new CCTVSonFragAdapter(listBeenList, getContext());
        fragmentCctvsonRecyclerView.setAdapter(cctvSonFragAdapter);
    }
}
