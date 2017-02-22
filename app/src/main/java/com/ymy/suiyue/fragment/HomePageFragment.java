package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ymy.suiyue.R;
import com.ymy.suiyue.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Galaxy on 2017/2/20.
 */

public class HomePageFragment extends Fragment {
    private TabLayout tabLayout;//和viewpager一起使用
    private ViewPager viewPager;//放置fragment的碎片
    private List<String> titles = new ArrayList<>();//主题集合
    private List<Fragment> fragments = new ArrayList<>();//碎片集合

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        findView(view);
        set();
    }

    private void set() {
        titles.add("推荐");
        titles.add("最新");
        titles.add("话题");
        fragments.add(new Home_RecommendFragment());
        fragments.add(new Home_NewestFragment());
        fragments.add(new Home_TopicFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getFragmentManager(), titles, fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void findView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    }
}
