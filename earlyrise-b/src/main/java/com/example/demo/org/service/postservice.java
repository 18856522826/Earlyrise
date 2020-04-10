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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class postservice {
    @Autowired
    private com.example.demo.org.mapper.postmapper postmapper;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Autowired
    private userservice userservice;
    public int addpost(Post p){
//        JSONObject jsonParam=(JSONObject)template.opsForList().rightPop("posts");
//        Calendar t = Calendar.getInstance();
//        Date date = t.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
//        String a = sdf.format(date);
//        String pname = jsonParam.getAsString("pname");
//        String content = jsonParam.getAsString("content");
//        String username = jsonParam.getAsString("username");
//        int barid = (int) jsonParam.getAsNumber("barid");
//        Post p = new Post();
//        p.setPostname(pname);
//        p.setPostcontent(content);
//        User u = userservice.getuser(username);
//        p.setUid(u.getUid());
//        p.setBid(barid);
//        p.setcTime(a);
       // Post p=new Post();
        postmapper.addpost(p);
            return 1;
    }
    public ArrayList<Post> getallpost(int a){
        return postmapper.getallpost(a);
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
}
