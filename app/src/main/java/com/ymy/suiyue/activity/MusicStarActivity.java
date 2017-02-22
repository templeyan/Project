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
import com.ymy.suiyue.adapter.Find_hot_Adapter1;
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

public class MusicStarActivity extends AppCompatActivity {
    private RecyclerView music_star_RecyclerView;
    private PullToRefreshScrollView music_star_PullTo;
    private List<FindPeopleIF> findPeopleIFs1 ;//明星音乐人
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_star);
        findView();
        initData1(InterfaceUri.find_hot_musicstar100);
        music_star_PullTo.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                String label = DateUtils.formatDateTime(MusicStarActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //睡眠半秒钟
                        try {
                            initData1(InterfaceUri.find_hot_musicstar100);
                            Thread.sleep(1000);
                            music_star_PullTo.post(new Runnable() {
                                @Override
                                public void run() {
                                    music_star_PullTo.onRefreshComplete();
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
        music_star_RecyclerView = (RecyclerView) findViewById(R.id.music_star_RecyclerView);
        music_star_PullTo = (PullToRefreshScrollView) findViewById(R.id.music_star_PullTo);
        music_star_RecyclerView.setLayoutManager(new GridLayoutManager(MusicStarActivity.this,3));
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
                        Find_hot_Adapter1 find_hot_adapter1 = new Find_hot_Adapter1(findPeopleIFs1,MusicStarActivity.this,URL);
                        music_star_RecyclerView.setAdapter(find_hot_adapter1);
                        find_hot_adapter1.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
