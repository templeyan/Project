package com.ymy.suiyue.bean;

/**
 * Created by ymy on 2017/2/24.
 */
/*用户的信息
*  "user_info":{
                    "id":"149740",
                    "nickname":"李然",
                    "gender":"1",
                    "tutor_id":"1998",
                    "avatar":"https://pic.suiyueyule.com/Fl_mIs-kuxeo963gL1N3CmF61LCp-headpic",
                    "identity_tag":"唱作人"
                },*/

public class User_Info {
    private String id;
    private String nickname;
    private String gender;
    private String tutor_id;
    private String avatar;
    private String indentity_tag;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIndentity_tag() {
        return indentity_tag;
    }

    public void setIndentity_tag(String indentity_tag) {
        this.indentity_tag = indentity_tag;
    }

    @Override
    public String toString() {
        return "User_Info{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", tutor_id='" + tutor_id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", indentity_tag='" + indentity_tag + '\'' +
                '}';
    }
}
