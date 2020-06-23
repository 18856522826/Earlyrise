package com.example.demo.org.model;

public class draft {
    private int draid;
    private String dratit;
    private String  drashort;
    private String dracontent;
    private int drauid;
    public draft(String dratit,String drashort,String dracontent,int drauid){
        this.dratit=dratit;
        this.drashort=drashort;
        this.dracontent=dracontent;
        this.drauid=drauid;
    }
    public draft(){}
    public int getDraid() {
        return draid;
    }

    public void setDraid(int draid) {
        this.draid = draid;
    }

    public String getDratit() {
        return dratit;
    }

    public void setDratit(String dratit) {
        this.dratit = dratit;
    }

    public String getDrashort() {
        return drashort;
    }

    public void setDrashort(String drashort) {
        this.drashort = drashort;
    }

    public String getDracontent() {
        return dracontent;
    }

    public void setDracontent(String dracontent) {
        this.dracontent = dracontent;
    }

    public int getDrauid() {
        return drauid;
    }

    public void setDrauid(int drauid) {
        this.drauid = drauid;
    }
}
