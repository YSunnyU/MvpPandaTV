package com.sunny.mvppandatv.view.PandaLive;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/16.
 */

public class PandaLiveSonFragAdapter extends FragmentPagerAdapter{
    private List<Fragment> frag_list;
    private List<String> title_list;

    public PandaLiveSonFragAdapter(FragmentManager fm, List<Fragment> frag_list, List<String> title_list) {
        super(fm);
        this.frag_list = frag_list;
        this.title_list = title_list;
    }

    @Override
    public Fragment getItem(int position) {
        return frag_list.get(position);
    }

    @Override
    public int getCount() {
        return frag_list.isEmpty()?0:frag_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position);

    }
}
