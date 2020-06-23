package com.example.demo.org.model;

/**
 * Created by Administrator on 2020/2/1.
 */
public class User {
    private int Uid;
    private String Username;
    private String password;
    private  String Uimg;
    private String nickname;
    private int bach;
    private String school;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getBach() {
        return bach;
    }
    public void setBach(int bach) {
        this.bach = bach;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUimg() {
        return Uimg;
    }

    public void setUimg(String uimg) {
        Uimg = uimg;
    }

    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
