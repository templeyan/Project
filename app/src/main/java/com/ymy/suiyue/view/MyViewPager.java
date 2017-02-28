package com.ymy.suiyue.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ymy.suiyue.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 广告轮播
 * Created by Galaxy on 2017/2/28.
 */

public class MyViewPager extends RelativeLayout {
    private ViewPager viewPager;
    private CircleImageView circleImageView;
    private LinearLayout linearLayout;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_myviewpager,null);
        findViewById(view);
    }

    private void findViewById(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        circleImageView = (CircleImageView) view.findViewById(R.id.dots);
        linearLayout = (LinearLayout) view.findViewById(R.id.dotsLinearLayout);
    }

    public void setViewPager(PagerAdapter adapter){
        viewPager.setAdapter(adapter);
    }

    public void setCircleImageView(int resId){
        circleImageView.setImageResource(resId);
    }
}
