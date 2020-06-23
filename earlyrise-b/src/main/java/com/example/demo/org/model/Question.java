package com.example.demo.org.model;

public class Question {
    private int qid;
    private String qname;
    private String qsample;
    private String qcontent;
    private String qtype;
    private int uid;
    private int qstar;
    private int qaccess;
    private int score;
    private int bool;
    private String qtime;


    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQsample() {
        return qsample;
    }

    public void setQsample(String qsample) {
        this.qsample = qsample;
    }

    public String getqContent() {
        return qcontent;
    }

    public void setqContent(String qContent) {
        this.qcontent = qContent;
    }

    public int getQstar() {
        return qstar;
    }

    public void setQstar(int qstar) {
        this.qstar = qstar;
    }

    public int getQaccess() {
        return qaccess;
    }

    public void setQaccess(int qaccess) {
        this.qaccess = qaccess;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBool() {
        return bool;
    }

    public void setBool(int bool) {
        this.bool = bool;
    }

    public String getQtime() {
        return qtime;
    }

    public void setQtime(String qtime) {
        this.qtime = qtime;
    }
}
