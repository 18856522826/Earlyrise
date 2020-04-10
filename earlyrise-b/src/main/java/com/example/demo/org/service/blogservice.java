package com.example.demo.org.service;
import com.example.demo.org.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class blogservice {
    @Autowired
    private com.example.demo.org.mapper.blogmapper blogmapper;
    public void setblog(Blog b){
        blogmapper.setblog(b);
    }
    public Blog getblog(int a){
       return  blogmapper.getblog(a);
    }
    public ArrayList getmaxblog(){
        return blogmapper.getmaxblog();
    }
    public ArrayList getstarblog(){
        return blogmapper.getstarblog();
    }
    public ArrayList getitblog(){
        return blogmapper.getitblog();
    }
    public ArrayList getotblog(){
        return blogmapper.getotblog();
    }

}
