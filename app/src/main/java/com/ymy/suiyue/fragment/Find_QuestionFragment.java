package com.ymy.suiyue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ymy.suiyue.R;
import com.ymy.suiyue.adapter.Find_Question_Adapter;
import com.ymy.suiyue.bean.QuestionBean;
import com.ymy.suiyue.bean.User_Info;
import com.ymy.suiyue.bean.Works;
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
 *
 *
 * find的问答页面,
 * 这有一个优化问题，关于上拉刷新，下拉加载的
 */

public class Find_QuestionFragment extends Fragment{
    private List<QuestionBean> questionList ;
    private Find_Question_Adapter adapter ;
    private RecyclerView question_recyclerview;
    private PullToRefreshScrollView question_PullTo;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_question,container,false);

        question_recyclerview = (RecyclerView) view.findViewById(R.id.question_recyclerview);
        question_PullTo = (PullToRefreshScrollView) view.findViewById(R.id.question_PullTo);
        question_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        questionList = new ArrayList<QuestionBean>();
        initData(page);
        question_PullTo.setMode(PullToRefreshBase.Mode.BOTH);
        question_PullTo.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
               //下拉刷新
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                questionList = new ArrayList<QuestionBean>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        page =1;//这步很关键，要不page一直再涨，上拉加载页数错误
                        initData(page);
                        try {
                            Thread.sleep(1000);
                            question_PullTo.post(new Runnable() {
                                @Override
                                public void run() {
                                    question_PullTo.onRefreshComplete();
                                }
                            });
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
              //上拉加载
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        initData(++page);
                        try {
                            Thread.sleep(1000);
                            question_PullTo.post(new Runnable() {
                                @Override
                                public void run() {
                                    question_PullTo.onRefreshComplete();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        return view;

    }


    private void initData(int page) {
        OkHttpUtils.get().url(InterfaceUri.find_question+page).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("sssssss",""+response);
                if (response != null){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("list");
                        Log.e("1111111111",""+jsonArray.length());
                        for(int i = 0;i<jsonArray.length();i++){
                            QuestionBean questionBean = new QuestionBean();
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            JSONObject jsonObject3 = jsonObject2.getJSONObject("user_info");
                            User_Info user_info1 = new User_Info();
                            //userinfo1
                            user_info1.setId(jsonObject3.getString("id"));
                            user_info1.setNickname(jsonObject3.getString("nickname"));
                            user_info1.setAvatar(jsonObject3.getString("avatar"));
                            user_info1.setIndentity_tag(jsonObject3.optString("identity_tag"));
                            //works
                            Works works = new Works();
                            if(jsonObject2.optJSONObject("works")!=null) {
                                JSONObject jsonObject6 = jsonObject2.optJSONObject("works");
                                works.setCover_photo(jsonObject6.getString("cover_photo"));

                                works.setId(jsonObject6.getString("id"));
                                works.setTitle(jsonObject6.getString("title"));
                                works.setCategory(jsonObject6.getString("category"));
                                works.setLike_num(jsonObject6.getString("like_num"));
                                works.setPlay_num(jsonObject6.getString("play_num"));
                            }
                            //quwstion
                            JSONObject jsonObject4 = jsonObject2.getJSONObject("question");
                            questionBean.setQuestion(jsonObject4.getString("question"));
                            questionBean.setQ_create_time(jsonObject4.getString("create_time"));
                            //answer
                            JSONObject jsonObject5 = jsonObject2.getJSONObject("answer");
                            questionBean.setAnswer_id(jsonObject5.getString("id"));
                            questionBean.setA_create_time(jsonObject5.getString("create_time"));
                            questionBean.setListen_num(jsonObject5.getString("listen_num"));
                            questionBean.setAudio_long(jsonObject5.getString("audio_long"));
                            JSONObject jsonObject7 = jsonObject5.getJSONObject("user_info");
                            User_Info user_info2 = new User_Info();
                            user_info2.setId(jsonObject7.getString("id"));
                            user_info2.setNickname(jsonObject7.getString("nickname"));
                            user_info2.setAvatar(jsonObject7.getString("avatar"));
                            user_info2.setIndentity_tag(jsonObject7.optString("identity_tag"));
                            questionBean.setWorks(works);
                            questionBean.setUser_info1(user_info1);
                            questionBean.setUser_info2(user_info2);
                            questionList.add(questionBean);
                            //Log.e("999","+++"+questionList);
                        }
                        //Log.e("9999","+++"+questionList);
                      adapter = new Find_Question_Adapter(getActivity(),questionList);
                        //写点击item中的作品的事件
                        adapter.setmOnworkClidckListener(new Find_Question_Adapter.OnWorkClickListener() {
                            @Override
                            public void onWorkClick(View view, QuestionBean questionBean) {
                                Toast.makeText(getActivity(),"作品id为："+1,Toast.LENGTH_SHORT).show();

                            }
                        });
                        //还要写点击item的事件
                        adapter.setOnItemClickListener(new Find_Question_Adapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, QuestionBean questionBean) {
                                Toast.makeText(getActivity(),"问答id："+questionBean.getAnswer_id(),Toast.LENGTH_SHORT).show();

                            }
                        });

                       question_recyclerview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }
        });

    }

}
