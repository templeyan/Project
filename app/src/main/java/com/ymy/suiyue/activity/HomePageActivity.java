package com.ymy.suiyue.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.ymy.suiyue.R;
import com.ymy.suiyue.fragment.HomePageFragment;
import com.ymy.suiyue.fragment.MusicFragment;

/**
 * 首页
 */
public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioButton btnRecommend,btnFind,btnLocal;//底部3个单选键
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
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frameLayout,new HomePageFragment());

        if (btnRecommend.isChecked()) {
            //FragmentManager manager = getSupportFragmentManager();
           // FragmentTransaction transaction = manager.beginTransaction();
           // transaction.replace(R.id.frameLayout, new HomePageFragment());
           // transaction.commit();
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
        if (btnRecommend.getId()==v.getId()){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frameLayout,new HomePageFragment());
            transaction.commit();
        }else if (btnFind.getId()==v.getId()){

        }else if (btnLocal.getId()==v.getId()){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frameLayout,new MusicFragment());
            transaction.commit();
        }

    }
}
