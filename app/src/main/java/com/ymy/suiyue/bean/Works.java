package com.ymy.suiyue.bean;

/**
 * Created by ymy on 2017/2/24.
 * 作品类
 *  "works":{
 "id":"31534",
 "user_id":"149740",
 "title":"或許明天…",
 "cover_photo":"https://static.suiyueyule.com/20170129011308-FrQIHWAeFid0S-AHVWvS-xJnmZD9.jpg-works.720.720.webp",
 "type":"1",
 "category":"原创",
 "hashtags":[
 "摇滚"
 ],
 "like_num":"151",
 "play_num":"5314",
 "goods_id":"309767",
 "is_like":0,
 "is_listened":0,
 "is_paid":false,
 "is_reward":false,
 "file_long":"281",
 "recommend_list":Array[1],
 "recommend_num":1,
 "online":"1",

 */

public class Works {
    private String id;
    private String title;
    private String cover_photo;
    private String type;
    private String category;
    private String like_num;
    private String play_num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getPlay_num() {
        return play_num;
    }

    public void setPlay_num(String play_num) {
        this.play_num = play_num;
    }

    @Override
    public String toString() {
        return "Works{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", cover_photo='" + cover_photo + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", like_num='" + like_num + '\'' +
                ", play_num='" + play_num + '\'' +
                '}';
    }
}
