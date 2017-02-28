package com.ymy.suiyue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ymy.suiyue.R;
import com.ymy.suiyue.adapter.MyFragmentPagerAdapter;
import com.ymy.suiyue.constants.InterfaceUri;
import com.ymy.suiyue.fragment.Topic_RankFragment;
import com.ymy.suiyue.fragment.Topic_HotFragment;
import com.ymy.suiyue.fragment.Topic_NewestFragment;
import com.ymy.suiyue.util.TimeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class TopicDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_portrait,img_back;//头像，返回键
    private TextView txt_title,txt_mainTitle,txt_nickname,txt_time,txt_introduce;//主题，大主题，昵称，时间，信息简介
    private TabLayout tabLayout;//切换碎片的布局
    private ViewPager viewPager;//显示碎片的布局
    private List<String> titles = new ArrayList<>();//主题集合
    private List<Fragment> fragments = new ArrayList<>();//碎片集合
    public static String id,is_show;//信息序号，是否显示排行

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        init();
    }

    private void init() {
        getMyIntent();
        findView();
        setOnClickListener();
        set();
        getData();
    }

    private void getData() {
        OkHttpUtils.get().url(InterfaceUri.topicDetailUser+id)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject object = jsonObject.getJSONObject("data");
                                    JSONObject topicObject = object.getJSONObject("topic");
                                    txt_mainTitle.setText("# " + topicObject.getString("title") + " #");
                                    txt_title.setText("# " + topicObject.getString("title") + " #");
                                    txt_introduce.setText(topicObject.getString("desc"));
                                    txt_time.setText(TimeUtils.getNormalTime(topicObject.getString("start_time")));
                                    JSONObject userObject = topicObject.getJSONObject("user");
                                    txt_nickname.setText(userObject.getString("nickname"));
                                    Picasso.with(TopicDetailActivity.this).load(userObject.getString("avatar")).into(img_portrait);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
    }

    private void getMyIntent() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        is_show = intent.getStringExtra("is_show");

    }

    private void set() {
        if (is_show.equals("0")) {
            titles.add("最热");
            titles.add("最新");
            fragments.add(new Topic_HotFragment());
            fragments.add(new Topic_NewestFragment());
        }else if (is_show.equals("1")){
            titles.add("排名");
            titles.add("最热");
            titles.add("最新");
            fragments.add(new Topic_RankFragment());
            fragments.add(new Topic_HotFragment());
            fragments.add(new Topic_NewestFragment());
        }
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setOnClickListener() {
        img_back.setOnClickListener(this);
    }

    private void findView() {
        img_back = (ImageView) findViewById(R.id.back);
        img_portrait = (ImageView) findViewById(R.id.img_portrait);
        txt_mainTitle = (TextView) findViewById(R.id.txt_mainTitle);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_nickname = (TextView) findViewById(R.id.txt_nickname);
        txt_time = (TextView) findViewById(R.id.txt_time);
        txt_introduce = (TextView) findViewById(R.id.txt_introduce);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    public void onClick(View v) {
        if (img_back.getId()==v.getId()){
            finish();
        }
    }
}
