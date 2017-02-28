package com.ymy.suiyue.bean;

/**
 * 话题详情里视频信息实体
 * Created by Galaxy on 2017/2/24.
 */

public class TopicDetailBean {
    private String title,avatar,cover_photo,nickname;//主题，头像，背景图，昵称，是否有排行榜
    private int rank;//名次
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
