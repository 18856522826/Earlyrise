package com.example.demo.org.service;

import com.example.demo.org.model.Comment_t;
import com.example.demo.org.model.Post;
import com.example.demo.org.model.Comment;
import com.example.demo.org.model.User;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class postservice {
    @Resource
    private com.example.demo.org.mapper.postmapper postmapper;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Autowired
    private userservice userservice;
    public int addpost(Post p){
        postmapper.addpost(p);
            return 1;
    }
    public ArrayList<Post> getallpost(int a){
        return postmapper.getallpost(a);
    }
    public ArrayList<Post> getattpost(int a,int b){
        return postmapper.getattpost(a,b);
    }
    public ArrayList<Post> getbarpost(int a){
        return postmapper.getbarpost(a);
    }
    public ArrayList<Post> getpostbyInput(String input){
        return postmapper.getpostbyInput(input);
    }
    public Post getpost(int a){
        return postmapper.getpost(a);
    }
    public int  setcomment(Comment c){
        postmapper.setcomment(c);
        return 1;
    }
    public void upcount(int a,int b){
        postmapper.upcount(a,b);
    }
    public ArrayList<Comment> getcomment(int a){
        return postmapper.getcomment(a);
    }
    public void setcomment_t(Comment_t c){
         postmapper.setcomment_t(c);
    }
    public ArrayList<Comment_t> getcomment_t(int  a){
       return postmapper.getcomment_t(a);
    }
    public void delpcomment(int a){
        postmapper.delpcomment(a);
    }
    public void delallpcomment( int a){
        postmapper.delallpcomment(a);
    }
    public void delpcomment_tall(int a){
        postmapper.delpcomment_tall(a);
    }
    public void delpcomment_tbyid(int a){
        postmapper.delpcomment_tbyid(a);
    }
    public ArrayList getmypost(int a,int b){
        return postmapper.getmypost(a,b);
    }
    public void delpost(int a){
        postmapper.delpost(a);
    }
}
