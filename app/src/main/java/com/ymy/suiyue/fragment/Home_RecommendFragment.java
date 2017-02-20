package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ymy.suiyue.R;

/**
 * 首页推荐的碎片
 * Created by Galaxy on 2017/2/19.
 */

public class Home_RecommendFragment extends Fragment {
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_recommend,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_recommend);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }
}
