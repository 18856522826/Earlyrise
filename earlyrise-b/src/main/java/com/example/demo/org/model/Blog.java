package com.example.demo.org.model;
public class Blog {
    private int bid;
    private String bname;
    private String bshort;
    private String bcontent;
    private int bstar;
    private int blow;
    private int  uid;
    private String btime;
    private int baccess;
    public Blog(){

    }
     public Blog(String bname,String bshort,String bcontent,int bstar,int blow,int uid,String btime){

         this.bname=bname;
         this.bshort=bshort;
         this.bcontent=bcontent;
         this.bstar=bstar;
         this.blow=blow;
         this.uid=uid;
         this.btime=btime;
     }
    public int getBaccess() {
        return baccess;
    }

    public void setBaccess(int baccess) {
        this.baccess = baccess;
    }

    public String getBshort() {
        return bshort;
    }

    public void setBshort(String bshort) {
        this.bshort = bshort;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public int getBstar() {
        return bstar;
    }

    public void setBstar(int bstar) {
        this.bstar = bstar;
    }

    public int getBlow() {
        return blow;
    }

    public void setBlow(int blow) {
        this.blow = blow;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }
}
