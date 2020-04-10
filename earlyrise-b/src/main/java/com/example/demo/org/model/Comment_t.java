package com.example.demo.org.model;

public class Comment_t {
    private int Comid;
    private String Comcontent;
    private String Comuname;
    private int  Pcommentid;
    private  int Postid;
    public int getComid() {
        return Comid;
    }

    public void setComid(int comid) {
        Comid = comid;
    }

    public String getComcontent() {
        return Comcontent;
    }

    public void setComcontent(String comcontent) {
        Comcontent = comcontent;
    }

    public String getComuname() {
        return Comuname;
    }

    public void setComuname(String comuname) {
        Comuname = comuname;
    }

    public int getPcommentid() {
        return Pcommentid;
    }

    public void setPcommentid(int pcommentid) {
        Pcommentid = pcommentid;
    }

    public int getPostid() {
        return Postid;
    }

    public void setPostid(int postid) {
        Postid = postid;
    }
}
