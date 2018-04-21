package com.sunny.mvppandatv.view.PandaEyeFrag;


import android.os.Handler;
import android.support.v4.app.Fragment;

import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.contract.PandaEyeContract;
import com.sunny.mvppandatv.presenter.PandaEyePresenter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import butterknife.Bind;

import static com.sunny.mvppandatv.R.id.panda_eye_frag_recy_view;

/**
 * A simple {@link Fragment} subclass.
 */
public class PandaEyeFragment extends BaseFragment implements PandaEyeContract.PandaEyeInView{

    public PandaEyeContract.PandaEyeInPresenter pandaEyeInPresenter;
    @Bind(panda_eye_frag_recy_view)
    PullLoadMoreRecyclerView pandaEyeRecyView;
    private Handler handler=new Handler();
    public PandaEyeFragment() {
        // Required empty public constructor
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panda_eye;
    }

    @Override
    protected void init() {
        pandaEyeInPresenter=new PandaEyePresenter(this);
        pandaEyeRecyView.setLinearLayout();
        pandaEyeRecyView.setPullRefreshEnable(true);
        pandaEyeRecyView.setPushRefreshEnable(true);
        pandaEyeRecyView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pandaEyeRecyView.setPullLoadMoreCompleted();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        pandaEyeRecyView.setPullLoadMoreCompleted();
                    }
                },2000);
            }
        });

    }

    @Override
    protected void initData() {
        pandaEyeInPresenter.sendData();
    }


    @Override
    public void showPandaEyeData(PandaEyeBean pandaEyeBean) {
        PandaEyeAdapter pandaEyeAdapter = new PandaEyeAdapter(pandaEyeBean,getContext());
        pandaEyeRecyView.setAdapter(pandaEyeAdapter);
    }
}
