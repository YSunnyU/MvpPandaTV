package com.sunny.mvppandatv.view.PandaLive;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunny.mvppandatv.R;
import com.sunny.mvppandatv.base.BaseFragment;
import com.sunny.mvppandatv.contract.PandaLiveSonContract;
import com.sunny.mvppandatv.presenter.PandaLiveSonPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class PandaLiveSonFragment extends BaseFragment implements PandaLiveSonContract.PandaLiveSonInView {


    @Bind(R.id.panda_live_live_image)
    ImageView pandaLiveLiveImage;
    @Bind(R.id.panda_live_live_title)
    TextView pandaLiveLiveTitle;
    @Bind(R.id.panda_live_live_bottomBtn)
    ImageView pandaLiveLiveBottomBtn;
    @Bind(R.id.panda_live_live_content)
    TextView pandaLiveLiveContent;
    @Bind(R.id.panda_live_live_topBtn)
    ImageView pandaLiveLiveTopBtn;
    @Bind(R.id.panda_live_live_sonTabLayout)
    TabLayout pandaLiveLiveSonTabLayout;
    @Bind(R.id.panda_live_live_recyclerView)
    ViewPager pandaLiveLiveRecyclerView;
    private List<String> title_list;
    public PandaLiveSonContract.PandaLiveSonInPresenter liveSonInPresenter;
    private List<Fragment> frag_list;
    private PandaLiveSonFragAdapter pandaLiveSonFragAdapter;
    private String url;
    private PandaLiveBean.LiveBean liveBean;
    private Bundle arguments;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_panda_live_son;
    }

    @Override
    protected void init() {
        title_list=new ArrayList<>();
        frag_list=new ArrayList<>();
        arguments = getArguments();
        if (arguments != null)
            url = arguments.getString("url");
        Log.d("PandaLiveSonFragment", url);
        liveSonInPresenter = new PandaLiveSonPresenter(this);



    }

    @Override
    protected void initData() {

            liveSonInPresenter.sendData(url);
    }


    @Override
    public void showPandaLiveSonData(PandaLiveBean pandaLiveBean) {
        List<PandaLiveBean.LiveBean> live = pandaLiveBean.getLive();
        liveBean = live.get(0);
        Glide.with(getContext()).load(liveBean.getImage()).into(pandaLiveLiveImage);
        pandaLiveLiveTitle.setText("[正在直播]" + liveBean.getTitle());
        pandaLiveLiveBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pandaLiveLiveBottomBtn.setVisibility(View.GONE);
                pandaLiveLiveContent.setVisibility(View.VISIBLE);
                pandaLiveLiveContent.setText(liveBean.getBrief());
                pandaLiveLiveTopBtn.setVisibility(View.VISIBLE);
            }
        });
        pandaLiveLiveTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pandaLiveLiveBottomBtn.setVisibility(View.VISIBLE);
                pandaLiveLiveContent.setVisibility(View.GONE);
                pandaLiveLiveTopBtn.setVisibility(View.GONE);
            }
        });
        PandaLiveBean.BookmarkBean bookmark = pandaLiveBean.getBookmark();
        String title = bookmark.getMultiple().get(0).getTitle();
        String title1 = bookmark.getWatchTalk().get(0).getTitle();
        title_list.add(title);
        title_list.add(title1);
        PandaLiveOneChildFragment pandaLiveChildOneFragment1 = new PandaLiveOneChildFragment();
        frag_list.add(pandaLiveChildOneFragment1);
        frag_list.add(new PandaLiveChildTwoFragment());
        pandaLiveLiveSonTabLayout.setupWithViewPager(pandaLiveLiveRecyclerView);
        Bundle bundle = new Bundle();
        String url = bookmark.getMultiple().get(0).getUrl();
        Log.d("PandaLiveSonFragment", url);
        bundle.putString("url",url);
        pandaLiveChildOneFragment1.setArguments(bundle);
        pandaLiveSonFragAdapter = new PandaLiveSonFragAdapter(getChildFragmentManager(), frag_list, title_list);
        pandaLiveLiveRecyclerView.setAdapter(pandaLiveSonFragAdapter);
        pandaLiveSonFragAdapter.notifyDataSetChanged();

    }


    public void data(final PandaLiveBean.LiveBean liveBean) {
        Glide.with(getContext()).load(liveBean.getImage()).into(pandaLiveLiveImage);
        pandaLiveLiveTitle.setText("[正在直播]" + liveBean.getTitle());
        pandaLiveLiveBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pandaLiveLiveBottomBtn.setVisibility(View.GONE);
                pandaLiveLiveContent.setVisibility(View.VISIBLE);
                pandaLiveLiveContent.setText(liveBean.getBrief());
                pandaLiveLiveTopBtn.setVisibility(View.VISIBLE);
            }
        });
        pandaLiveLiveTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pandaLiveLiveBottomBtn.setVisibility(View.VISIBLE);
                pandaLiveLiveContent.setVisibility(View.GONE);
                pandaLiveLiveTopBtn.setVisibility(View.GONE);
            }
        });


    }


}
