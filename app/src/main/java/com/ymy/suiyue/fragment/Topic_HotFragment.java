package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ymy.suiyue.R;
import com.ymy.suiyue.activity.TopicDetailActivity;
import com.ymy.suiyue.adapter.MyTopicDetailAdapter;
import com.ymy.suiyue.bean.TopicDetailBean;
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
 * 话题详情最新的碎片
 * Created by Galaxy on 2017/2/23.
 */

public class Topic_HotFragment extends Fragment {
    private RecyclerView recyclerView;//列表展示
    private TopicDetailBean topicDetailBean;//item信息封装对象
    private List<TopicDetailBean> list;//数据源
    private MyTopicDetailAdapter adapter;//列表适配器
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_detail, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        init();
        return view;
    }

    private void init() {
        getData();
        set();
    }

    private void set() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
    }

    //进页面的初次加载
    public void getData() {
        OkHttpUtils.get().url(InterfaceUri.topicDetailHot+TopicDetailActivity.id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            list = new ArrayList();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("data");
                            JSONArray jsonArray = object.getJSONArray("list");
                            int length = jsonArray.length();
                            for (int i = 0; i < length; i++) {
                                topicDetailBean = new TopicDetailBean();
                                JSONObject o = jsonArray.getJSONObject(i);
                                JSONObject worksObject = o.getJSONObject("works");
                                topicDetailBean.setTitle(worksObject.getString("title"));
                                topicDetailBean.setCover_photo(worksObject.getString("cover_photo"));
                                JSONObject user_infoObject = o.getJSONObject("user_info");
                                topicDetailBean.setAvatar(user_infoObject.getString("avatar"));
                                topicDetailBean.setNickname(user_infoObject.getString("nickname"));
                                list.add(topicDetailBean);
                            }
                            adapter = new MyTopicDetailAdapter(getContext().getApplicationContext(),list);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
}
