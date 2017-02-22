package com.ymy.suiyue.bean;

/**
 * Created by ymy on 2017/2/20.
 */

/***
 * 发现页面的音乐人
 */

/*"id":"328510",
        "nickname":"陈明",
        "gender":"0",
        "avatar":"https://pic.suiyueyule.com/FonHTF7fYbfn45izNkPKahGULTHk",
        "score":"54",
        "star_cover":"https://pic.suiyueyule.com/FonHTF7fYbfn45izNkPKahGULTHk-star.250.380",
        "tutor_id":"2741",
        "identity":"内地女歌手 第八届CCTV-MTV音乐盛典内地年度最佳女歌手",
        "style":{
        "width":"250",
        "height":"380"
        }*/
public class FindPeopleIF {

    private String id;
    private String nickname;
    private String avatar;
    private String score;
    private String star_cover;
    private String tutor_id;
    private String identity;

    @Override
    public String toString() {
        return "FindPeopleIF{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", score='" + score + '\'' +
                ", star_cover='" + star_cover + '\'' +
                ", tutor_id='" + tutor_id + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStar_cover() {
        return star_cover;
    }

    public void setStar_cover(String star_cover) {
        this.star_cover = star_cover;
    }

    public String getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
