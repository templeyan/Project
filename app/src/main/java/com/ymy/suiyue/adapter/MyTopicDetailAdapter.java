package com.ymy.suiyue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.TopicDetailBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 话题详情里视频信息的适配器
 * Created by Galaxy on 2017/2/24.
 */

public class MyTopicDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<TopicDetailBean> list;

    public MyTopicDetailAdapter(Context context, List<TopicDetailBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_myrecycler_topicdetail, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (!list.get(position).getCover_photo().equals("") && !list.get(position).getAvatar().equals("")) {
            Picasso.with(context).load(list.get(position).getCover_photo()).into(viewHolder.img_background);
            Picasso.with(context).load(list.get(position).getAvatar()).into(viewHolder.img_portrait);
        }
        viewHolder.txt_nickname.setText(list.get(position).getNickname());
        viewHolder.txt_title.setText(list.get(position).getTitle());
        if (list.get(position).getRank() > 0 && list.get(position).getRank()<4) {
            viewHolder.txt_rank.setVisibility(View.VISIBLE);
            if (list.get(position).getRank() == 1) {
                viewHolder.txt_rank.setText(R.string.first);
                viewHolder.txt_rank.setBackgroundResource(R.color.rankBackground);
            } else if (list.get(position).getRank() == 2) {
                viewHolder.txt_rank.setText(R.string.second);
                viewHolder.txt_rank.setBackgroundResource(R.color.topicDetailBackground);
            } else if (list.get(position).getRank() == 3) {
                viewHolder.txt_rank.setText(R.string.third);
                viewHolder.txt_rank.setBackgroundResource(R.color.topicDetailBackground);
            }
        } else {
            viewHolder.txt_rank.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_title, txt_nickname, txt_rank;//主题，昵称，排名
        private ImageView img_background;//整个布局的图片背景
        private CircleImageView img_portrait;//头像

        public ViewHolder(View itemView) {
            super(itemView);
            txt_rank = (TextView) itemView.findViewById(R.id.txt_rank);
            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_nickname = (TextView) itemView.findViewById(R.id.txt_nickname);
            img_background = (ImageView) itemView.findViewById(R.id.img_background);
            img_portrait = (CircleImageView) itemView.findViewById(R.id.img_portrait);
        }
    }

}
