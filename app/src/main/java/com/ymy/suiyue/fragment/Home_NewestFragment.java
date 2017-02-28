package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ymy.suiyue.R;
import com.ymy.suiyue.adapter.MyNewestAdapter;
import com.ymy.suiyue.bean.HPRecommendNewestBean;
import com.ymy.suiyue.constants.InterfaceUri;
import com.ymy.suiyue.util.TimeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 首页最新
 * Created by Galaxy on 2017/2/19.
 */

public class Home_NewestFragment extends Fragment {
    private RecyclerView recyclerView;//显示数据
    private MyNewestAdapter adapter;//RecyclerView适配器
    private List<HPRecommendNewestBean> list;//数据源
    private HPRecommendNewestBean HPRecommendNewestBean;//最新界面的信息对象
    private PullToRefreshScrollView pullToRefreshScrollView;//刷新布局
    private static int page = 1;//刷新的请求参数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_newest, container, false);
        init(view);
        return view;
    }


    //进页面的初次加载
    public void getData(final int page){
        OkHttpUtils.get().url(InterfaceUri.newest + page)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        try {
                            if (page==1) {
                                list = new ArrayList();
                            }
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("data");
                            JSONArray jsonArray = object.getJSONArray("list");
                            int length = jsonArray.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject listObject = jsonArray.getJSONObject(i);
                                HPRecommendNewestBean = new HPRecommendNewestBean();
                                HPRecommendNewestBean.setTime(TimeUtils.getStandardDate(listObject.getString("create_time")));
                                JSONObject userObject = listObject.getJSONObject("user_info");
                                HPRecommendNewestBean.setNickname(userObject.getString("nickname"));
                                HPRecommendNewestBean.setPortrait(userObject.getString("avatar"));
                                JSONObject worksObject = listObject.getJSONObject("works");
                                if (worksObject.getString("type").equals("1")) {
                                    HPRecommendNewestBean.setType("音频");
                                } else if (worksObject.getString("type").equals("2")) {
                                    HPRecommendNewestBean.setType("视频");
                                }
                                HPRecommendNewestBean.setTitle(worksObject.getString("title"));
                                HPRecommendNewestBean.setBackground(worksObject.getString("cover_photo"));
                                if (Integer.parseInt(worksObject.getString("file_long")) < 60) {//秒转换为分秒的形式
                                    HPRecommendNewestBean.setDuration("00:" + worksObject.getString("file_long"));
                                } else {
                                    int t = Integer.parseInt(worksObject.getString("file_long"));
                                    int m = t / 60;
                                    int s = t % 60;
                                    HPRecommendNewestBean.setDuration(m + ":" + s);
                                }
                                HPRecommendNewestBean.setCommentCounts(worksObject.getString("recommend_num"));
                                list.add(HPRecommendNewestBean);
                            }
                            adapter = new MyNewestAdapter(getActivity().getApplicationContext(), list);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void init(View view) {
        findView(view);
        getData(1);
        set();
    }

    private void findView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        pullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.pullToRefreshScrollView);
    }

    private void set() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        pullToRefreshScrollView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {//下拉刷新
                getData(1);
                pullToRefreshScrollView.onRefreshComplete();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {//上拉加载
                page += 1;
                getData(page);
                pullToRefreshScrollView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }
}
