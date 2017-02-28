package com.ymy.suiyue.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.HPRecommendNewestBean;

import java.util.List;

/**
 * RecyclerView的适配器
 * Created by Galaxy on 2017/2/20.
 */

public class MyRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HPRecommendNewestBean> list;

    public MyRecommendAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_myrecyclerview_item, null);
        return new MyRecommendAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyRecommendAdapter.ViewHolder viewHolder = (MyRecommendAdapter.ViewHolder) holder;
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.duration.setText(list.get(position).getDuration());
        viewHolder.time.setText(list.get(position).getTime());
        viewHolder.nickname.setText(list.get(position).getNickname());
        viewHolder.type.setText(list.get(position).getType());
        if (list.get(position).getScore().equals("")) {
            viewHolder.ratingBar.setVisibility(View.GONE);
        }else {
            viewHolder.ratingBar.setRating(Float.parseFloat(list.get(position).getScore()));
        }
        viewHolder.line.setVisibility(View.GONE);
        Picasso.with(context).load(list.get(position).getBackground()).config(Bitmap.Config.RGB_565).into(viewHolder.background);
        if (!TextUtils.isEmpty(list.get(position).getPortrait()) || !list.get(position).getPortrait().equals("")) {
            Picasso.with(context).load(list.get(position).getPortrait()).config(Bitmap.Config.RGB_565).into(viewHolder.portrait);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nickname, title, introduce, type, duration, comment, commentCounts, time, line;
        private ImageView portrait, background;
        private RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            portrait = (ImageView) itemView.findViewById(R.id.img_portrait);
            background = (ImageView) itemView.findViewById(R.id.img_item);
            nickname = (TextView) itemView.findViewById(R.id.txt_nickname);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            introduce = (TextView) itemView.findViewById(R.id.txt_introduce);
            type = (TextView) itemView.findViewById(R.id.txt_type);
            duration = (TextView) itemView.findViewById(R.id.txt_duration);
            comment = (TextView) itemView.findViewById(R.id.txt_comment);
            commentCounts = (TextView) itemView.findViewById(R.id.txt_commentCounts);
            time = (TextView) itemView.findViewById(R.id.txt_time);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            line = (TextView) itemView.findViewById(R.id.txt_line);
        }
    }

}
