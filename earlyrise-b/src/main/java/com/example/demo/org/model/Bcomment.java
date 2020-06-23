package com.example.demo.org.model;

public class Bcomment {
    private int bcomid;
    private String bcontent;
    private int bid;
    private int uid;
    private String bctime;

    public Bcomment(){

    }
    public Bcomment( String a,int b,int c,String bctime){
        bcontent=a;
        bid=b;
        uid=c;
        this.bctime=bctime;
    }
    public int getBcomid() {
        return bcomid;
    }

    public void setBcomid(int bcomid) {
        this.bcomid = bcomid;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBctime() {
        return bctime;
    }

    public void setBctime(String bctime) {
        this.bctime = bctime;
    }
}
