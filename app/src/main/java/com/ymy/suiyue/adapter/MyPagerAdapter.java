package com.ymy.suiyue.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ymy.suiyue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告轮播适配器
 * Created by Galaxy on 2017/2/28.
 */
public class MyPagerAdapter extends PagerAdapter{
    private Context context;
    private List<String> list = new ArrayList<>();
    private List<View> listV = new ArrayList<>();
    private ImageView imageView;
    private int length;

    public MyPagerAdapter(Context context,List<String> list,List<View> listV) {
        this.context = context;
        this.list = list;
        this.listV = listV;
        length = list.size();
    }

    @Override
    public int getCount() {
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listV.get(position%length));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = listV.get(position%length);
        imageView = (ImageView) view.findViewById(R.id.img);
        Picasso.with(context).load(list.get(position%length)).config(Bitmap.Config.RGB_565).into(imageView);
        container.addView(view);
        return view;
    }

}
