package com.ymy.suiyue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.Song;
import com.ymy.suiyue.util.MusicUtils;

import java.util.List;

/**
 * ListView的适配器
 * Created by Galaxy on 2017/2/18.
 */
public class MySongAdapter extends BaseAdapter {
    private Context context;
    private List<Song> list;

    public MySongAdapter(Context context, List<Song> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            //引入布局
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_listview, null);
            //实例化对象
            holder.song = (TextView) convertView.findViewById(R.id.item_mymusic_song);
            holder.singer = (TextView) convertView.findViewById(R.id.item_mymusic_singer);
            holder.duration = (TextView) convertView.findViewById(R.id.item_mymusic_duration);
            holder.position = (TextView) convertView.findViewById(R.id.item_mymusic_postion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //给控件赋值
        holder.song.setText(list.get(i).song);
        holder.singer.setText(list.get(i).singer);
        //时间需要转换一下
        int duration = list.get(i).duration;
        String time = MusicUtils.formatTime(duration);
        holder.duration.setText(time);
        holder.position.setText(i + 1 + "");

        return convertView;
    }

    static class ViewHolder {
        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView position;//序号
        ImageView img;//专辑图片
    }
}
