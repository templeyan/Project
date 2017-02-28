package com.ymy.suiyue.bean;

import java.io.Serializable;

/**
 * 首页推荐和最新RecyclerView每个Item的信息对象
 * Created by Galaxy on 2017/2/20.
 */

public class HPRecommendNewestBean implements Serializable{
    private String background,video,portrait,nickname,title,introduce;//信息的id，背景图片，视频路径，头像，昵称，主题，介绍
    private String type,duration,comment,commentCounts,time;//视频种类(1--音频，2--视频)，播放时间，评论种类，评论数，发出时间
    private String score="";//打星等级

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(String commentCounts) {
        this.commentCounts = commentCounts;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
