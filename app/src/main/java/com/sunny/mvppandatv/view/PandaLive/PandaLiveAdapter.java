package com.sunny.mvppandatv.view.PandaLive;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class PandaLiveAdapter extends FragmentPagerAdapter {
    private List<PandaLiveSonFragment> sonFrag_list;
  //  private List<PandaLiveAboveBean.TablistBean> tablist;
  private ArrayList<String> mTitle;

  /*  public PandaLiveAdapter(FragmentManager fm, List<PandaLiveSonFragment> sonFrag_list, List<PandaLiveAboveBean.TablistBean> tablist) {
        super(fm);
        this.sonFrag_list = sonFrag_list;
        this.tablist = tablist;
    }*/

    public PandaLiveAdapter(FragmentManager fm, List<PandaLiveSonFragment> sonFrag_list, ArrayList<String> mTitle) {
        super(fm);
        this.sonFrag_list = sonFrag_list;
        this.mTitle = mTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return sonFrag_list.get(position);
    }

    @Override
    public int getCount() {
        return sonFrag_list.isEmpty()?0:sonFrag_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
