package com.ymy.suiyue.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.ymy.suiyue.bean.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取手机音乐的工具类
 * Created by Galaxy on 2017/2/18.
 */

public class MusicUtils {
    public static List<Song> getMusicData(Context context) {
        List<Song> list = new ArrayList<Song>();
        // 媒体库查询语句（写一个工具类MusicUtils）
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Song song = new Song();
                song.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                if (song.size > 1024 * 1024 &&song.duration!=0){//获取到的文件必须大于一定kb
                    // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                    if (song.song.contains("-")&&!song.song.contains("ara")) {
                        String[] str = song.song.split("-");
                        song.singer = str[0].trim();
                        song.song = str[1].trim();
                    }
                    if (song.song.contains("_")) {
                        String[] str = song.song.split("_");
                        song.singer = str[1].trim();
                        song.song = str[0].trim();
                    }
                    if (song.song.contains("少女时代")) {
                        song.singer = "少女时代";
                        String[] str = song.song.split("_");
                        song.song = str[1].trim();
                    }
                    if (!(song.singer.contains("unknown"))) {
                        list.add(song);
                    }
                }
            }
            // 释放资源
            cursor.close();
        }

        return list;
    }

    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }


}
