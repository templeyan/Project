package com.ymy.suiyue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ymy.suiyue.R;
import com.ymy.suiyue.activity.TopicDetailActivity;
import com.ymy.suiyue.adapter.MyTopicAdapter;
import com.ymy.suiyue.bean.HPTopicBean;
import com.ymy.suiyue.constants.InterfaceUri;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 首页话题
 * Created by Galaxy on 2017/2/19.
 */

public class Home_TopicFragment extends Fragment implements MyTopicAdapter.MyRecyclerViewClickListener {
    private RecyclerView recyclerView;//显示数据
    private HPTopicBean hpTopicBean;//话题界面的信息对象
    private List<HPTopicBean> list;//数据源
    private MyTopicAdapter adapter;//RecyclerView适配器

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_recommend, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        init();
        return view;
    }

    //进页面的初次加载
    public void getData(final int page) {
        OkHttpUtils.get().url(InterfaceUri.topic + page)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            list = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("data");
                            JSONArray jsonArray = object.getJSONArray("list");
                            int length = jsonArray.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject listObject = jsonArray.getJSONObject(i);
                                JSONObject topicObject = listObject.getJSONObject("topic");
                                hpTopicBean = new HPTopicBean();
                                hpTopicBean.setId(topicObject.getString("id"));
                                hpTopicBean.setIs_show(topicObject.getString("is_show"));
                                hpTopicBean.setTitle(topicObject.getString("title"));
                                hpTopicBean.setIntroduce(topicObject.getString("desc"));
                                hpTopicBean.setCover_photo(topicObject.getString("cover_photo"));
                                if (Integer.parseInt(topicObject.getString("play_num")) > 9999) {
                                    int thousand = Integer.parseInt(topicObject.getString("play_num")) % 10000 / 1000;
                                    if (thousand != 0) {
                                        hpTopicBean.setPlay_num(Integer.parseInt(topicObject.getString("play_num")) / 10000 + "." + thousand + "万");
                                    } else {
                                        hpTopicBean.setPlay_num(Integer.parseInt(topicObject.getString("play_num")) / 10000 + ".0万");
                                    }
                                } else {
                                    hpTopicBean.setPlay_num(topicObject.getString("play_num"));
                                }
                                hpTopicBean.setWorks_count(topicObject.getString("works_count"));
                                list.add(hpTopicBean);
                            }
                            adapter = new MyTopicAdapter(getActivity().getApplicationContext(), list);
                            adapter.setListener(Home_TopicFragment.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void init() {
        getData(1);
        set();
    }

    private void set() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }

    @Override
    public void onClick(View v, HPTopicBean hpTopicBean) {
        Intent intent = new Intent(getActivity(), TopicDetailActivity.class);
        intent.putExtra("id", hpTopicBean.getId());
        intent.putExtra("is_show", hpTopicBean.getIs_show());
        startActivity(intent);
    }
}
