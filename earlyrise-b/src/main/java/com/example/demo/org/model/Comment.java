package com.example.demo.org.model;

public class Comment {
    private int Ccid;
    private String Ccontent;
    private int Postid;
    private String Username;
    private int Ccount;
    private String Pctime;

    public String getPctime() {
        return Pctime;
    }

    public void setPctime(String pctime) {
        Pctime = pctime;
    }

    public int getCcount() {
        return Ccount;
    }

    public void setCcount(int ccount) {
        Ccount = ccount;
    }

    public int getCcid() {
        return Ccid;
    }

    public void setCcid(int ccid) {
        Ccid = ccid;
    }

    public String getCcontent() {
        return Ccontent;
    }

    public void setCcontent(String ccontent) {
        Ccontent = ccontent;
    }

    public int getPostid() {
        return Postid;
    }

    public void setPostid(int postid) {
        Postid = postid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
