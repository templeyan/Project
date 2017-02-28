package com.ymy.suiyue.bean;

/**
 * Created by ymy on 2017/2/24.
 * question页面的item 类
 */

public class QuestionBean {
    private User_Info user_info1;//回答人
    private User_Info user_info2;//提问人
    private String question;
    private Works works;//讨论的作品
    private String Q_create_time;
    private String A_create_time;
    private String listen_num;
    private String audio_long;
    private String answer_id;//跳到回答页的id

    @Override
    public String toString() {
        return "QuestionBean{" +
                "user_info1=" + user_info1 +
                ", user_info2=" + user_info2 +
                ", question='" + question + '\'' +
                ", works=" + works +
                ", Q_create_time='" + Q_create_time + '\'' +
                ", A_create_time='" + A_create_time + '\'' +
                ", listen_num='" + listen_num + '\'' +
                ", audio_long='" + audio_long + '\'' +
                ", answer_id='" + answer_id + '\'' +
                '}';
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getAudio_long() {
        return audio_long;
    }

    public void setAudio_long(String audio_long) {
        this.audio_long = audio_long;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Works getWorks() {
        return works;
    }

    public void setWorks(Works works) {
        this.works = works;
    }

    public String getQ_create_time() {
        return Q_create_time;
    }

    public void setQ_create_time(String q_create_time) {
        Q_create_time = q_create_time;
    }

    public String getA_create_time() {
        return A_create_time;
    }

    public void setA_create_time(String a_create_time) {
        A_create_time = a_create_time;
    }

    public String getListen_num() {
        return listen_num;
    }

    public void setListen_num(String listen_num) {
        this.listen_num = listen_num;
    }

    public User_Info getUser_info1() {
        return user_info1;
    }

    public void setUser_info1(User_Info user_info1) {
        this.user_info1 = user_info1;
    }

    public User_Info getUser_info2() {
        return user_info2;
    }

    public void setUser_info2(User_Info user_info2) {
        this.user_info2 = user_info2;
    }
}
