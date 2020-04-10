package com.example.demo.org.mapper;

import com.example.demo.org.model.Bar;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
@Mapper
public interface barmapper {
    public ArrayList<Bar> getallBar(String data);
    public void barreg(Bar b);
    public Bar getbar(String data);
}
