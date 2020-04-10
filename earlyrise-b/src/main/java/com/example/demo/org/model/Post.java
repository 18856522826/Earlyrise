package com.example.demo.org.model;

public class Post {
   private  int Postid;
   private String Postname;
   private String Postcontent;
   private int Uid;
   private int Poststar;
   private int Bid;
   private String cTime;
   private int pcount;

    public int getPcount() {
        return pcount;
    }

    public void setPcount(int pcount) {
        this.pcount = pcount;
    }

    public String getcTime() {
        return cTime;
    }
    public void setcTime(String cTime) {
        this.cTime = cTime;
    }
    public int getUid() {
        return Uid;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public int getPoststar() {
        return Poststar;
    }

    public void setPoststar(int poststar) {
        Poststar = poststar;
    }

    public int getBid() {
        return Bid;
    }

    public void setBid(int bid) {
        Bid = bid;
    }



    public void setPostname(String postname) {
        Postname = postname;
    }

    public void setPostcontent(String postcontent) {
        Postcontent = postcontent;
    }

    public int getPostid() {
        return Postid;
    }

    public void setPostid(int postid) {
        Postid = postid;
    }

    public String getPostname() {
        return Postname;
    }
    public String getPostcontent() {
        return Postcontent;
    }

}
