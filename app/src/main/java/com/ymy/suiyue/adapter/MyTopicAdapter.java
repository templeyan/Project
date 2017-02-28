package com.ymy.suiyue.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.HPTopicBean;

import java.util.List;

/**
 * RecyclerView的适配器
 * Created by Galaxy on 2017/2/20.
 */

public class MyTopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<HPTopicBean> list;

    public MyTopicAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_myrecyclerview_topic, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyTopicAdapter.ViewHolder viewHolder = (MyTopicAdapter.ViewHolder) holder;
        if(!list.get(position).getCover_photo().equals("")) {
            Picasso.with(context).load(list.get(position).getCover_photo()).config(Bitmap.Config.RGB_565).into(viewHolder.img);
        }else{
            viewHolder.img.setImageResource(R.drawable.nopicture);
        }
        viewHolder.title.setText("# " + list.get(position).getTitle() + " #");
        viewHolder.introduce.setText(list.get(position).getIntroduce());
        viewHolder.play_num.setText(list.get(position).getPlay_num());
        viewHolder.works_count.setText(list.get(position).getWorks_count());

        //给点击事件添加数据
        viewHolder.itemView.setTag(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //接口回调
    public interface MyRecyclerViewClickListener{
        void onClick(View v,HPTopicBean hpTopicBean);
    }

    MyRecyclerViewClickListener myRecyclerViewClickListener = null;

    public void setListener(MyRecyclerViewClickListener myRecyclerViewClickListener){
        this.myRecyclerViewClickListener = myRecyclerViewClickListener;
    }

    //点击事件
    @Override
    public void onClick(View v) {
        myRecyclerViewClickListener.onClick(v, (HPTopicBean) v.getTag());
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;//图片
        private TextView title, introduce, play_num, works_count;//主题，简介，收听数，作品数，没有图片时显示的提示

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            introduce = (TextView) itemView.findViewById(R.id.introduce);
            play_num = (TextView) itemView.findViewById(R.id.play_num);
            works_count = (TextView) itemView.findViewById(R.id.works_count);
        }
    }

}
