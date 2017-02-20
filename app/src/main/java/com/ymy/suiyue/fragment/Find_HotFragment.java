package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ymy.suiyue.R;

import java.util.List;


public class Find_HotFragment extends Fragment {
    private TabLayout find_TabLayout;
    private ViewPager find_ViewPager;
    private List<Fragment> list_fragments;
    private List<String> list_title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_findhot,container,false);
        init();
        int i =0;


        return view;

    }

    private void init() {
        //找到控件
        find_TabLayout = (TabLayout) getView().findViewById(R.id.find_TabLayout);
        find_ViewPager = (ViewPager) getView().findViewById(R.id.find_ViewPager);
        //初始化

    }
}
