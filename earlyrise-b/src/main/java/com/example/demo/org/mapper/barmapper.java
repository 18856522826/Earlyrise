package com.example.demo.org.mapper;

import com.example.demo.org.model.Bar;
import com.example.demo.org.model.Battention;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
@Mapper
public interface barmapper {
    public ArrayList<Bar> getallBar(String data);
    public void barreg(Bar b);
    public Bar getbar(String data);
    public Bar getbarbyid(int a);
    public void setbattention(Battention b);
    public void delbattention(Battention b);
    public Battention getbattention(Battention b);
    public ArrayList getabar(int a);
    public void delbarid(int a);
}
