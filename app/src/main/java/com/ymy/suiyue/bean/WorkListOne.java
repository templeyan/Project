package com.ymy.suiyue.bean;

/**
 * Created by ymy on 2017/2/21.
 */
/*{
        "id":"42503",
        "user_id":"103230",
        "type":"0",
        "create_time":"1486974359",
        "user_info":{
        "id":"103230",
        "nickname":"青龙",
        "gender":"1",
        "tutor_id":"0",
        "avatar":"https://pic.suiyueyule.com/FsFPy4ciE1cpITYLBF-xq_m-E46M-headpic"
        },
        "works":{
        "id":"39528",
        "title":"北京，你听到了吗",
        "cover_photo":"https://static.suiyueyule.com/20170213161315-76XQHehxtcXTWdi7fZjn5axY6x.png-works.720.720.webp",
        "type":"1",
        "category":"原创"
        }
        },*/

public class WorkListOne {
    private String id ;//作品id
    private String nickname;
    private String title;
    private String cover_photo;
    private int type ;//想给他加个尾部局，用这个标识，（0,1） 1是尾部

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WorkListOne{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", cover_photo='" + cover_photo + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
