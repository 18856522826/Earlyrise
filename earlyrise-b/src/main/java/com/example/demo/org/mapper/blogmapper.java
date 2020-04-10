package com.example.demo.org.mapper;

import com.example.demo.org.model.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface blogmapper {
    public void setblog(Blog b);
    public Blog getblog(int a);
    public ArrayList getmaxblog();
    public ArrayList getitblog();
    public ArrayList getstarblog();
    public ArrayList getotblog();
}

