package com.ymy.suiyue.bean;

/**
 * 首页话题RecyclerView每个Item的信息对象
 * Created by Galaxy on 2017/2/23.
 */

public class HPTopicBean {
    private String id,is_show,title,introduce,play_num,works_count,cover_photo;//信息序号，是否显示排行栏，标题，简介，收听数，作品数，图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
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

    public String getPlay_num() {
        return play_num;
    }

    public void setPlay_num(String play_num) {
        this.play_num = play_num;
    }

    public String getWorks_count() {
        return works_count;
    }

    public void setWorks_count(String works_count) {
        this.works_count = works_count;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }
}
