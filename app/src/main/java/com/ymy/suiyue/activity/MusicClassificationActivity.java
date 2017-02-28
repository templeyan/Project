package com.ymy.suiyue.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ymy.suiyue.R;
import com.ymy.suiyue.adapter.Find_hot_Adapter2;
import com.ymy.suiyue.bean.FindMusicClassification;
import com.ymy.suiyue.constants.InterfaceUri;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MusicClassificationActivity extends AppCompatActivity {

    private RecyclerView music_classification_RecyclerView;
    private PullToRefreshScrollView music_classification_PullTo;
    private List<FindMusicClassification> findMusicCfs ;//明星音乐人
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_classification);
        findView();
        initData1(InterfaceUri.find_hot_music_classification);
        music_classification_PullTo.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(MusicClassificationActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //睡眠1秒钟
                        try {
                            initData1(InterfaceUri.find_hot_music_classification);
                            Thread.sleep(1000);
                            music_classification_PullTo.post(new Runnable() {
                                @Override
                                public void run() {
                                    music_classification_PullTo.onRefreshComplete();
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


    }

    private void findView() {
        music_classification_RecyclerView = (RecyclerView) findViewById(R.id.music_classification_RecyclerView);
        music_classification_PullTo = (PullToRefreshScrollView) findViewById(R.id.music_classification_PullTo);
        music_classification_RecyclerView.setLayoutManager(new GridLayoutManager(MusicClassificationActivity.this,2));
    }

    private void initData1(final String URL) {
        OkHttpUtils.get().url(URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                findMusicCfs = new ArrayList<>();
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
                        Find_hot_Adapter2 find_hot_adapter = new Find_hot_Adapter2(findMusicCfs,MusicClassificationActivity.this,1);
                        music_classification_RecyclerView.setAdapter(find_hot_adapter);
                        find_hot_adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
