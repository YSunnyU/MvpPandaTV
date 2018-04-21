package com.sunny.mvppandatv.view.CCTVFrag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 张玗 on 2018/4/13.
 */

public class CCTVAboveAdapter extends FragmentPagerAdapter{
    private List<CCTVBean.TablistBean> title_list;

    private List<CCTVSonFragment> CCTVSonfragments_list;

    public CCTVAboveAdapter(FragmentManager fm, List<CCTVBean.TablistBean> title_list, List<CCTVSonFragment> CCTVSonfragments_list) {
        super(fm);
        this.title_list = title_list;
        this.CCTVSonfragments_list = CCTVSonfragments_list;
    }

    @Override
    public Fragment getItem(int position) {
        return CCTVSonfragments_list.get(position);
    }

    @Override
    public int getCount() {
        return CCTVSonfragments_list.isEmpty()?0:CCTVSonfragments_list.size();
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position);

    }*/

    @Override
    public CharSequence getPageTitle(int position) {
        return title_list.get(position).getTitle();
    }
}
