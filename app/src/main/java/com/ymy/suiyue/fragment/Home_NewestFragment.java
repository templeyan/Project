package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ymy.suiyue.R;

import com.ymy.suiyue.constants.InterfaceUri;
import com.ymy.suiyue.adapter.MyNewestAdapter;
import com.ymy.suiyue.bean.NewestInformation;
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
 * 首页推荐
 * Created by Galaxy on 2017/2/19.
 */

public class Home_NewestFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyNewestAdapter adapter;
    private List<NewestInformation> list = new ArrayList<>();
    private NewestInformation newestInformation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_recommend, container, false);
        init();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_recommend);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    private void init() {
        OkHttpUtils.get().url(InterfaceUri.newest)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("info", "onResponse: "+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("data");
                            JSONArray jsonArray = object.getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject listObject = jsonArray.getJSONObject(i);
                                newestInformation = new NewestInformation();
                                newestInformation.setTime(TimeUtils.getStandardDate(listObject.getString("create_time")));
                                JSONObject userObject = listObject.getJSONObject("user_info");
                                newestInformation.setNickname(userObject.getString("nickname"));
                                newestInformation.setPortrait(userObject.getString("avatar"));
                                JSONObject worksObject = listObject.getJSONObject("works");
                                if (worksObject.getString("type").equals("1")){
                                    newestInformation.setType("音频");
                                }else if (worksObject.getString("type").equals("2")){
                                    newestInformation.setType("视频");
                                }
                                newestInformation.setTitle(worksObject.getString("title"));
                                newestInformation.setBackground(worksObject.getString("cover_photo"));
                                if (Integer.parseInt(worksObject.getString("file_long")) < 60){
                                    newestInformation.setDuration("00:"+worksObject.getString("file_long"));
                                }else {
                                    int t = Integer.parseInt(worksObject.getString("file_long"));
                                    int m = t/60;
                                    int s = t%60;
                                    newestInformation.setDuration(m+":"+s);
                                }
                                newestInformation.setCommentCounts(worksObject.getString("recommend_num"));
                                list.add(newestInformation);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter = new MyNewestAdapter(getActivity().getApplicationContext(),list);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroy();
    }
}
