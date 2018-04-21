package com.sunny.mvppandatv.view.LiveChina;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/14.
 */

public class LiveChinaFragAdapter extends FragmentPagerAdapter {
    private List<ChinaLiveFragment> fragments_list;
    private List<LiveChinaBean.TablistBean> titles_list;

    public LiveChinaFragAdapter(FragmentManager fm, List<ChinaLiveFragment> fragments_list, List<LiveChinaBean.TablistBean> titles_list) {
        super(fm);
        this.fragments_list = fragments_list;
        this.titles_list = titles_list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments_list.get(position);
    }

    @Override
    public int getCount() {
        return fragments_list.isEmpty()?0:fragments_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles_list.get(position).getTitle();
    }
}
