package com.example.demo.org.controller;

import com.example.demo.org.model.Blog;
import com.example.demo.org.service.blogservice;
import com.example.demo.org.service.userservice;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
@Controller
@RequestMapping("blog")
public class blogcontroller {

    @Autowired
    private  com.example.demo.org.service.blogservice blogservice;
    @Autowired
    private com.example.demo.org.service.userservice userservice;
    @RequestMapping("setblog")
    @ResponseBody
    public int setblog(@RequestBody JSONObject jsonparam){
        Calendar t = Calendar.getInstance();
        Date date = t.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
        String a = sdf.format(date);
        String bname= jsonparam.getAsString("title");
        String bcontent=jsonparam.getAsString("content");
        String uname=jsonparam.getAsString("username");
       int uid=userservice.getuser(uname).getUid();
        Blog b=new Blog(bname,bcontent,0,0,uid,a);
        blogservice.setblog(b);
        return 1;
    }
    @RequestMapping("getblog")
    public Blog getblog(@RequestBody JSONObject jsonparam){
       int id=Integer.parseInt(jsonparam.getAsString("id"));
            return blogservice.getblog(id);
    }
    @RequestMapping("getmaxblog")
    public Map getmaxblog(){
        HashMap m=new HashMap();
       ArrayList b=blogservice.getmaxblog();
       m.put("blog",b);
       return m;
    }
    @RequestMapping("getstarblog")
    public Map getstarblog(){
        HashMap m=new HashMap();
        ArrayList b=blogservice.getstarblog();
        m.put("blog",b);
        return m;
    }
}
