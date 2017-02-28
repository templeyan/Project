package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ymy.suiyue.R;
import com.ymy.suiyue.adapter.MyPagerAdapter;
import com.ymy.suiyue.adapter.MyRecommendAdapter;
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
 * 首页推荐的碎片
 * Created by Galaxy on 2017/2/19.
 */

public class Home_RecommendFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private RecyclerView recyclerView;//列表展示
    private HPRecommendNewestBean HPRecommendNewestBean;//最新界面的信息对象
    private List<HPRecommendNewestBean> list;//列表展示数据源
    private List<String> list1;//广告数据源
    private List<View> listV;//布局数据源
    private List<ImageView> listDots = new ArrayList<>();//小圆点的集合
    private MyRecommendAdapter adapter;//Recycler适配器
    private MyPagerAdapter pagerAdapter;//ViewPager适配器
    private ViewPager viewPager;//广告
    private LinearLayout linearLayout;//显示小圆点的布局
    private int length;//广告个数
    private PullToRefreshScrollView pullToRefreshScrollView;//刷新布局
    private static int page = 1;//刷新的请求参数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_recommend, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        findView(view);
        addImageView();
        getData(1);
        set();
        setListener();
    }

    //找控件
    private void findView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        pullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.pullToRefreshScrollView);
    }

    //绑定监听
    private void setListener() {
        viewPager.addOnPageChangeListener(this);
    }

    //动态添加广告，小圆点
    private void addImageView() {
        listV = new ArrayList<>();
        View view1 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_myviewpager, null);
        View view2 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_myviewpager, null);
        View view3 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_myviewpager, null);
        View view4 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_myviewpager, null);
        View view5 = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_myviewpager, null);
        listV.add(view1);
        listV.add(view2);
        listV.add(view3);
        listV.add(view4);
        listV.add(view5);
        length = listV.size();
        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(8, 0, 8, 0);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setImageResource(R.drawable.dot_selected);
            } else {
                imageView.setImageResource(R.drawable.dot);
            }
            listDots.add(imageView);
            linearLayout.addView(imageView);
        }

    }

    //进页面的初次加载
    public void getData(final int page) {
        OkHttpUtils.get().url(InterfaceUri.recommend + page)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            list1 = new ArrayList();
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
                                    HPRecommendNewestBean.setVideo(listObject.getString("cover_url"));
                                }
                                if (worksObject.has("score")) {
                                    HPRecommendNewestBean.setScore(worksObject.getString("score"));
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
                                if (i > 14 && i < 20) {
                                    list1.add(HPRecommendNewestBean.getBackground());
                                }
                            }
                            adapter = new MyRecommendAdapter(getActivity().getApplicationContext(), list);
                            recyclerView.setAdapter(adapter);
                            pagerAdapter = new MyPagerAdapter(getActivity().getApplicationContext(), list1, listV);
                            viewPager.setAdapter(pagerAdapter);
                            for (int i = 5000; i > 1; i--) {//让viewpager从中间开始
                                if (i % length == 0) {
                                    viewPager.setCurrentItem(i);
                                    break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void set() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < length; i++) {
            if (i == position % length) {
                listDots.get(position % length).setImageResource(R.drawable.dot_selected);
            } else {
                listDots.get(i).setImageResource(R.drawable.dot);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
