package com.example.demo.org.model;

public class Bar {
    private int Barid;
    private String Barname;
    private String Barbrief;
    private int Baruser;
    private int Barfollowed;
    private String Barimg;

    public String getBarimg() {
        return Barimg;
    }

    public void setBarimg(String barimg) {
        Barimg = barimg;
    }
    public void setBarid(int barid) {
        Barid = barid;
    }

    public void setBarname(String barname) {
        Barname = barname;
    }

    public void setBarbrief(String barbrief) {
        Barbrief = barbrief;
    }

    public void setBaruser(int baruser) {
        Baruser = baruser;
    }

    public void setBarfollowed(int barfollowed) {
        Barfollowed = barfollowed;
    }

    public int getBarid() {
        return Barid;
    }

    public String getBarname() {
        return Barname;
    }

    public String getBarbrief() {
        return Barbrief;
    }

    public int getBaruser() {
        return Baruser;
    }

    public int getBarfollowed() {
        return Barfollowed;
    }
}
