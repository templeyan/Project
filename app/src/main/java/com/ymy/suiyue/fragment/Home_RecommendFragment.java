package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_recommend);


        init();
        return view;
    }

    private void init() {
        //getData(1);
        set();
    }

    /*//进页面的初次加载
    public void getData(final int page){
        OkHttpUtils.get().url(InterfaceUri.recommend + page)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (page == 1) {
                            list.clear();
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("data");
                            JSONArray jsonArray = object.getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject listObject = jsonArray.getJSONObject(i);
                                newestInformation = new NewestInformation();
                                newestInformation.setId(TimeUtils.getStandardDate(listObject.getString("id")));
                                newestInformation.setTime(TimeUtils.getStandardDate(listObject.getString("create_time")));
                                JSONObject userObject = listObject.getJSONObject("user_info");
                                newestInformation.setNickname(userObject.getString("nickname"));
                                newestInformation.setPortrait(userObject.getString("avatar"));
                                JSONObject worksObject = listObject.getJSONObject("works");
                                if (worksObject.getString("type").equals("1")) {
                                    newestInformation.setType("音频");
                                } else if (worksObject.getString("type").equals("2")) {
                                    newestInformation.setType("视频");
                                }
                                newestInformation.setTitle(worksObject.getString("title"));
                                newestInformation.setBackground(worksObject.getString("cover_photo"));
                                if (Integer.parseInt(worksObject.getString("file_long")) < 60) {//秒转换为分秒的形式
                                    newestInformation.setDuration("00:" + worksObject.getString("file_long"));
                                } else {
                                    int t = Integer.parseInt(worksObject.getString("file_long"));
                                    int m = t / 60;
                                    int s = t % 60;
                                    newestInformation.setDuration(m + ":" + s);
                                }
                                newestInformation.setCommentCounts(worksObject.getString("recommend_num"));
                                list.add(newestInformation);
                            }
                            adapter = new MyNewestAdapter(getActivity().getApplicationContext(), list);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }*/

    private void set() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }
}
