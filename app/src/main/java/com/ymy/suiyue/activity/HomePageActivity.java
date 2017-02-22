package com.ymy.suiyue.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.ymy.suiyue.R;
import com.ymy.suiyue.fragment.Find_Fragment;
import com.ymy.suiyue.fragment.HomePageFragment;
import com.ymy.suiyue.fragment.MusicFragment;

/**
 * 首页
 */
public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioButton btnRecommend,btnFind,btnLocal;//底部3个单选键
    private HomePageFragment homePageFragment;
    private Find_Fragment find_fragment;
    private MusicFragment musicFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
       init();
    }

    private void init() {
        findView();
        setOnClickListener();
        setFragment();
    }

    private void setFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        homePageFragment = new HomePageFragment();
        find_fragment = new Find_Fragment();
        musicFragment = new MusicFragment();
        transaction.add(R.id.frameLayout,homePageFragment);
        transaction.add(R.id.frameLayout,find_fragment);
        transaction.add(R.id.frameLayout,musicFragment);

        if (btnRecommend.isChecked()) {
            transaction.show(homePageFragment);
            transaction.hide(find_fragment);
            transaction.hide(musicFragment);
            transaction.commit();
        }
    }

    private void setOnClickListener() {
        btnRecommend.setOnClickListener(this);
        btnRecommend.setChecked(true);
        btnFind.setOnClickListener(this);
        btnLocal.setOnClickListener(this);
    }

    private void findView() {
        btnRecommend = (RadioButton) findViewById(R.id.btnRecommend);
        btnFind = (RadioButton) findViewById(R.id.btnFind);
        btnLocal = (RadioButton) findViewById(R.id.btnLocal);
    }


    @Override
    public void onClick(View v) {
        transaction = manager.beginTransaction();
        if (btnRecommend.getId()==v.getId()){
            transaction.show(homePageFragment);
            transaction.hide(find_fragment);
            transaction.hide(musicFragment);
            transaction.commit();
        }else if (btnFind.getId()==v.getId()){
            transaction.hide(homePageFragment);
            transaction.show(find_fragment);
            transaction.hide(musicFragment);
            transaction.commit();
        }else if (btnLocal.getId()==v.getId()){
            transaction.hide(homePageFragment);
            transaction.hide(find_fragment);
            transaction.show(musicFragment);
            transaction.commit();
        }

    }
}
