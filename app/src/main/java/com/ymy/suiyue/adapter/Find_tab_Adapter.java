package com.ymy.suiyue.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 发现界面的适配器
 */

public class Find_tab_Adapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragments;
    private  List<String> list_title;
    public Find_tab_Adapter(FragmentManager fm,List<Fragment> list_fragments,List<String> list_title) {
        super(fm);
        this.list_fragments = list_fragments;
        this.list_title = list_title;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragments.get(position);
    }

    @Override
    public int getCount() {
        return list_title.size();
    }
    //用此方法来显示Tab上的名字

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position%list_title.size());
    }
}
