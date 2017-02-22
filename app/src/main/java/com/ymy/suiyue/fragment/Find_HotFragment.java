package com.ymy.suiyue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ymy.suiyue.R;
import com.ymy.suiyue.activity.MusicStarActivity;
import com.ymy.suiyue.adapter.Find_hot_Adapter1;
import com.ymy.suiyue.adapter.Find_hot_Adapter2;
import com.ymy.suiyue.bean.FindMusicClassification;
import com.ymy.suiyue.bean.FindPeopleIF;
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
 * Created by ymy on 2017/2/20.
 */

public class Find_HotFragment extends Fragment implements View.OnClickListener{
    private  PullToRefreshScrollView PRScrollView;
 private   List<FindPeopleIF> findPeopleIFs1 ;//明星音乐人
    private  List<FindPeopleIF> findPeopleIFs2;//呐喊榜音乐人
    private  List<FindPeopleIF> findPeopleIFs3;//新锐音乐人
    private  List<FindMusicClassification> findMusicCfs;//音乐分类
    private TextView musicstar_more;
    private RecyclerView hot_recylerView1,hot_recylerView2,hot_recylerView3,hot_recylerView4;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgement_find_hot,container,false);
        hot_recylerView1 = (RecyclerView) view.findViewById(R.id.hot_RecylerView1);
        hot_recylerView2 = (RecyclerView) view.findViewById(R.id.hot_RecylerView2);
        hot_recylerView3 = (RecyclerView) view.findViewById(R.id.hot_RecylerView3);
        hot_recylerView4 = (RecyclerView) view.findViewById(R.id.hot_RecylerView4);
        musicstar_more = (TextView) view.findViewById(R.id.musicstar_more);
        PRScrollView = (PullToRefreshScrollView) view.findViewById(R.id.PRScrollView);
        //设置孩子的排列方式    每排显示3个
        hot_recylerView1.setLayoutManager(new GridLayoutManager(getActivity(),3));
        hot_recylerView2.setLayoutManager(new GridLayoutManager(getActivity(),3));
        hot_recylerView3.setLayoutManager(new GridLayoutManager(getActivity(),3));
        hot_recylerView4.setLayoutManager(new GridLayoutManager(getActivity(),2));
        initData1(InterfaceUri.find_hot_musicstar6);//加载明星音乐人数据
        initData2(InterfaceUri.find_hot_screamlist3);//加载呐喊榜音乐人数据
        initData3(InterfaceUri.find_hot_newmusic6);//加载新锐音乐人数据
        initData4(InterfaceUri.find_hot_music_classification6);//加载音乐分类数据
        setOnListener();
        PRScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                initData1(InterfaceUri.find_hot_musicstar6);//加载明星音乐人数据
                initData2(InterfaceUri.find_hot_screamlist3);//加载呐喊榜音乐人数据
                initData3(InterfaceUri.find_hot_newmusic6);//加载新锐音乐人数据
                initData4(InterfaceUri.find_hot_music_classification6);//加载音乐分类数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //睡眠半秒钟
                        try {
                            Thread.sleep(1000);
                            PRScrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    PRScrollView.onRefreshComplete();
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });

        return view;
    }

    private void setOnListener() {
        musicstar_more.setOnClickListener(this);
    }

    private void initData4(final String URL) {
        OkHttpUtils.get().url(URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                findMusicCfs = new ArrayList<FindMusicClassification>();
                //Log.e("000000",""+response);

                if (!response.isEmpty()){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                        JSONArray jsonArray = jsonObject1.optJSONArray("data");
                        for (int i =0 ;i<jsonArray.length();i++){
                            FindMusicClassification fc = new FindMusicClassification();
                            JSONObject obj = jsonArray.getJSONObject(i);
                            fc.setHashtag(obj.getString("hashtag"));
                            fc.setCover_photo(obj.getString("cover_photo"));
                            fc.setTotal_play(obj.getString("total_play"));


                            findMusicCfs.add(fc);
                        }
                        Find_hot_Adapter2 find_hot_adapter2 = new Find_hot_Adapter2(findMusicCfs,getActivity());
                        hot_recylerView4.setAdapter(find_hot_adapter2);
                         find_hot_adapter2.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void initData3(final String URL) {

        OkHttpUtils.get().url(URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                findPeopleIFs3 = new ArrayList<FindPeopleIF>();
                //Log.e("000000",""+response);
                if (!response.isEmpty()){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                        JSONArray jsonArray = jsonObject1.optJSONArray("data");
                        for (int i =0 ;i<jsonArray.length();i++){
                            FindPeopleIF fp = new FindPeopleIF();
                            JSONObject obj = jsonArray.getJSONObject(i);
                            fp.setId(obj.getString("id"));
                            fp.setNickname(obj.getString("nickname"));
                            fp.setAvatar(obj.getString("avatar"));
                            fp.setIdentity(obj.getString("identity"));
                            findPeopleIFs3.add(fp);
                        }
                        //Log.e("----",""+findPeopleIFs3);
                        Find_hot_Adapter1 find_hot_adapter1 = new Find_hot_Adapter1(findPeopleIFs3,getActivity(),URL);
                        hot_recylerView3.setAdapter(find_hot_adapter1);
                        find_hot_adapter1.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void initData2(final String URL) {
        OkHttpUtils.get().url(URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                findPeopleIFs2 = new ArrayList<FindPeopleIF>();
                //Log.e("000000",""+response);
                if (!response.isEmpty()){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                        JSONArray jsonArray = jsonObject1.optJSONArray("data");
                        for (int i =0 ;i<jsonArray.length();i++){
                            FindPeopleIF fp = new FindPeopleIF();
                            JSONObject obj = jsonArray.getJSONObject(i);
                            fp.setId(obj.getString("id"));
                            fp.setNickname(obj.getString("nickname"));
                            fp.setAvatar(obj.getString("avatar"));
                            fp.setStar_cover(obj.getString("star_cover"));
                            fp.setScore(obj.getString("score"));
                            findPeopleIFs2.add(fp);
                        }
                        Find_hot_Adapter1 find_hot_adapter1 = new Find_hot_Adapter1(findPeopleIFs2,getActivity(),URL);
                        hot_recylerView2.setAdapter(find_hot_adapter1);
                        find_hot_adapter1.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }


    private void initData1(final String URL) {
        OkHttpUtils.get().url(URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                findPeopleIFs1 = new ArrayList<FindPeopleIF>();
                //Log.e("000000",""+response);
                if (!response.isEmpty()){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                        JSONArray jsonArray = jsonObject1.optJSONArray("data");
                        for (int i =0 ;i<jsonArray.length();i++){
                            FindPeopleIF fp = new FindPeopleIF();
                            JSONObject obj = jsonArray.getJSONObject(i);
                            fp.setId(obj.getString("id"));
                            fp.setNickname(obj.getString("nickname"));
                            fp.setAvatar(obj.getString("avatar"));
                            fp.setIdentity(obj.getString("identity"));
                            fp.setStar_cover(obj.getString("star_cover"));
                            fp.setScore(obj.getString("score"));

                            findPeopleIFs1.add(fp);
                        }
                        Find_hot_Adapter1 find_hot_adapter1 = new Find_hot_Adapter1(findPeopleIFs1,getActivity(),URL);
                        hot_recylerView1.setAdapter(find_hot_adapter1);
                        find_hot_adapter1.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.musicstar_more:
                Intent intent = new Intent(getActivity(), MusicStarActivity.class);
                startActivity(intent);
        }

    }
}
