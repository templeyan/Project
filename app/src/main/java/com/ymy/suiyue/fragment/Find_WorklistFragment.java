package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ymy.suiyue.R;
import com.ymy.suiyue.adapter.Find_WorkListAdapter;
import com.ymy.suiyue.bean.WorkListOne;
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
 * 发现——作品榜碎片
 */



public class Find_WorklistFragment extends Fragment {
   private PullToRefreshScrollView worklist_PRScrollView;
    private ImageView worklist_hotpic,worklist_originpic,worklist_salepic,
            worklist_coverpic,worklist_videopic;
    private List<ImageView> listIV ;
    private List<String> listIcon;
    private List<WorkListOne> listWorkListOnes ;
    private List <List> listWorkLists ;
    private Find_WorkListAdapter find_workListAdapter1,find_workListAdapter2,find_workListAdapter3,find_workListAdapter4
    ,find_workListAdapter5;
    private RecyclerView worklist_recyclerview1,worklist_recyclerview2,worklist_recyclerview3,worklist_recyclerview4
           ,worklist_recyclerview5;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_worklist,container,false);
        init(view);
        initdata();

        worklist_PRScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //睡眠半秒钟
                        try {
                            initdata();
                            Thread.sleep(1000);
                            worklist_PRScrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    worklist_PRScrollView.onRefreshComplete();
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

    /***
     * 加载每行的足部布局
     */
   /* private void setFooterView() {
        View footer = LayoutInflater.from(getActivity()).inflate(R.layout.find_worklist_foot,worklist_recyclerview1 , false);
        find_workListAdapter1.setmFooterView(footer);
        View footer1 = LayoutInflater.from(getActivity()).inflate(R.layout.find_worklist_foot,worklist_recyclerview2 , false);
        find_workListAdapter2.setmFooterView(footer1);
        View footer2 = LayoutInflater.from(getActivity()).inflate(R.layout.find_worklist_foot,worklist_recyclerview3 , false);
        find_workListAdapter3.setmFooterView(footer2);
        View footer3 = LayoutInflater.from(getActivity()).inflate(R.layout.find_worklist_foot,worklist_recyclerview4 , false);
        find_workListAdapter4.setmFooterView(footer3);
        View footer4 = LayoutInflater.from(getActivity()).inflate(R.layout.find_worklist_foot,worklist_recyclerview5 , false);
        find_workListAdapter5.setmFooterView(footer4);
    }*/



    private void initdata() {
        OkHttpUtils.get().url(InterfaceUri.find_hot_worklist).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("666666666666",""+response);

                listIcon = new ArrayList<String>();
                try {

                    JSONObject jso = new JSONObject(response);
                    JSONObject jso1 = jso.getJSONObject("data");
                    JSONArray jsonArray = jso1.getJSONArray("list");
                    listWorkLists = new ArrayList();
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject jso2 = jsonArray.getJSONObject(i);
                        listIcon.add(jso2.getString("icon"));
                        listWorkListOnes = new ArrayList<WorkListOne>();
                        JSONArray jsonArray1 = jso2.getJSONArray("data");
                        for(int j=0;j<jsonArray1.length();j++){
                            JSONObject json3 = jsonArray1.getJSONObject(j);
                            WorkListOne workListOne = new WorkListOne();
                            JSONObject json4 = json3.getJSONObject("user_info");
                            workListOne.setNickname(json4.getString("nickname"));
                            JSONObject json5 = json3.getJSONObject("works");
                            workListOne.setId(json5.getString("id"));//作品id
                           workListOne.setTitle(json5.getString("title"));
                            workListOne.setCover_photo(json5.getString("cover_photo"));
                           workListOne.setType(0);
                            listWorkListOnes.add(workListOne);


                        }
                        WorkListOne workListOneLast = new WorkListOne();
                        workListOneLast.setType(1);
                       listWorkListOnes.add(workListOneLast);
                        listWorkLists.add(listWorkListOnes);


                    }
                    //把前面的图片放进去（热度---------）
                    for (int m= 0;m<listIV.size();m++){
                        Glide.with(getActivity())
                                .load(listIcon.get(m))
                                .into(listIV.get(m));
                    }
                  //绑定适配器
                    find_workListAdapter1 = new Find_WorkListAdapter(listWorkLists.get(0),getActivity());
                    find_workListAdapter2 = new Find_WorkListAdapter(listWorkLists.get(1),getActivity());
                    find_workListAdapter3 = new Find_WorkListAdapter(listWorkLists.get(2),getActivity());
                    find_workListAdapter4 = new Find_WorkListAdapter(listWorkLists.get(3),getActivity());
                    find_workListAdapter5 = new Find_WorkListAdapter(listWorkLists.get(4),getActivity());

                    worklist_recyclerview1.setAdapter(find_workListAdapter1);
                    worklist_recyclerview2.setAdapter(find_workListAdapter2);
                    worklist_recyclerview3.setAdapter(find_workListAdapter3);
                    worklist_recyclerview4.setAdapter(find_workListAdapter4);
                    worklist_recyclerview5.setAdapter(find_workListAdapter5);


                    find_workListAdapter1.setmOnitemClickListener(new Find_WorkListAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, WorkListOne workListOne) {
                            if(!TextUtils.isEmpty(workListOne.getId())){
                            Toast.makeText(getActivity(), "作品id为"+workListOne.getId(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getActivity(), "作品id为"+1, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "我是加载更多", Toast.LENGTH_SHORT).show();

                            }
                            //Toast.makeText(getActivity(), "作品id为"+1, Toast.LENGTH_SHORT).show();


                        }
                    });
                    find_workListAdapter2.setmOnitemClickListener(new Find_WorkListAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, WorkListOne workListOne) {
                            if(!TextUtils.isEmpty(workListOne.getId())){
                                Toast.makeText(getActivity(), "作品id为"+workListOne.getId(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getActivity(), "作品id为"+1, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "我是加载更多", Toast.LENGTH_SHORT).show();

                            }
                            //Toast.makeText(getActivity(), "作品id为"+1, Toast.LENGTH_SHORT).show();


                        }
                    });
                    find_workListAdapter3.setmOnitemClickListener(new Find_WorkListAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, WorkListOne workListOne) {
                            if(!TextUtils.isEmpty(workListOne.getId())){
                                Toast.makeText(getActivity(), "作品id为"+workListOne.getId(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getActivity(), "作品id为"+1, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "我是加载更多", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                    find_workListAdapter4.setmOnitemClickListener(new Find_WorkListAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, WorkListOne workListOne) {
                            if(!TextUtils.isEmpty(workListOne.getId())){
                                Toast.makeText(getActivity(), "作品id为"+workListOne.getId(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getActivity(), "作品id为"+1, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "我是加载更多", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                    find_workListAdapter5.setmOnitemClickListener(new Find_WorkListAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, WorkListOne workListOne) {
                            if(!TextUtils.isEmpty(workListOne.getId())){
                                Toast.makeText(getActivity(), "作品id为"+workListOne.getId(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getActivity(), "作品id为"+1, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "我是加载更多", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /***
     * 初始化
     * @param view
     */

    private void init(View view) {
        worklist_PRScrollView = (PullToRefreshScrollView) view.findViewById(R.id.worklist_PRScrollView);
        worklist_hotpic = (ImageView) view.findViewById(R.id.worklist_hotpic);
        worklist_originpic = (ImageView) view.findViewById(R.id.worklist_originpic);
        worklist_salepic = (ImageView) view.findViewById(R.id.worklist_salepic);
        worklist_coverpic = (ImageView) view.findViewById(R.id.worklist_coverpic);
        worklist_videopic = (ImageView) view.findViewById(R.id.worklist_videopic);
        worklist_recyclerview1 = (RecyclerView) view.findViewById(R.id.worklist_recyclerview1);
        worklist_recyclerview2 = (RecyclerView) view.findViewById(R.id.worklist_recyclerview2);
        worklist_recyclerview3 = (RecyclerView) view.findViewById(R.id.worklist_recyclerview3);
        worklist_recyclerview4 = (RecyclerView) view.findViewById(R.id.worklist_recyclerview4);
        worklist_recyclerview5 = (RecyclerView) view.findViewById(R.id.worklist_recyclerview5);
//将图片控件装入集合
        listIV = new ArrayList<>();
        listIV.add(worklist_hotpic);
        listIV.add(worklist_originpic);
        listIV.add(worklist_salepic);
        listIV.add(worklist_coverpic);
        listIV.add(worklist_videopic);

        worklist_recyclerview1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        worklist_recyclerview2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        worklist_recyclerview3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        worklist_recyclerview4.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        worklist_recyclerview5.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));






    }
}
