package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ymy.suiyue.R;
import com.ymy.suiyue.adapter.Find_tab_Adapter;

import java.util.ArrayList;
import java.util.List;

/***
 * 发现的主页面（有hot，歌曲榜，问答三个碎片）
 */

public class Find_Fragment extends Fragment {
    private TabLayout find_TabLayout;
    private ViewPager find_ViewPager;
    private List<Fragment> list_fragments;
    private List<String> list_title;
    private Find_HotFragment hotFragment;
    private Find_WorklistFragment worklistFragment;
    private Find_QuestionFragment questionFragment;
    private FragmentPagerAdapter fAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_find,container,false);
        //找到控件
        find_TabLayout = (TabLayout) view.findViewById(R.id.find_TabLayout);
        find_ViewPager = (ViewPager) view.findViewById(R.id.find_ViewPager);
        init();
        //小小测试一下
        return view;

    }

    private void init() {

        //初始化fragment
        hotFragment = new Find_HotFragment();
       worklistFragment = new Find_WorklistFragment();
        questionFragment = new Find_QuestionFragment();
        //将fragment加入到集合中
        list_fragments = new ArrayList<>();
        list_fragments.add(hotFragment);
        list_fragments.add(worklistFragment);
        list_fragments.add(questionFragment);
        //将名称加载到tab名字列表
        list_title = new ArrayList<>();
        list_title.add("HOT");
        list_title.add("作品榜");
        list_title.add("问答");
        //设置TABLayout的模式
        find_TabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        find_TabLayout.addTab(find_TabLayout.newTab().setText(list_title.get(0)));
        find_TabLayout.addTab(find_TabLayout.newTab().setText(list_title.get(1)));
        find_TabLayout.addTab(find_TabLayout.newTab().setText(list_title.get(2)));
         fAdapter = new Find_tab_Adapter(getActivity().getSupportFragmentManager(),list_fragments,list_title);
        //ViewPager 加载 adapter
        find_ViewPager.setAdapter(fAdapter);
        find_TabLayout.setupWithViewPager(find_ViewPager);

    }
}
