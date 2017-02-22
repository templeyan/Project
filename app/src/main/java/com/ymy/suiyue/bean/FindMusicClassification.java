package com.ymy.suiyue.bean;

/**
 * Created by ymy on 2017/2/21.
 */

public class FindMusicClassification {
    private String hashtag;
    private String cover_photo;
    private String total_play;

    @Override
    public String toString() {
        return "FindMusicClassification{" +
                "hashtag='" + hashtag + '\'' +
                ", cover_photo='" + cover_photo + '\'' +
                ", total_play='" + total_play + '\'' +
                '}';
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    public String getTotal_play() {
        return total_play;
    }

    public void setTotal_play(String total_play) {
        this.total_play = total_play;
    }
}
/*"hashtag":"民谣",
        "cover_photo":"https://static.suiyueyule.com/20161117115625-t4kJZhzHk2xXnPHc7y7JRmA2PN.jpe",
        "total_play":2118234*/
