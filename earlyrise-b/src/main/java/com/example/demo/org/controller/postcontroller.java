package com.example.demo.org.controller;

import com.example.demo.org.model.*;
import com.example.demo.org.service.barservice;
import com.example.demo.org.service.postservice;
import com.example.demo.org.service.redistool;
import com.example.demo.org.service.userservice;
import com.fasterxml.jackson.annotation.JsonAlias;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("post")
public class postcontroller {
    @Autowired
    private com.example.demo.org.service.postservice postservice;
    @Autowired
    private com.example.demo.org.service.userservice userservice;
    @Autowired
    private com.example.demo.org.service.barservice barservice;
    @Autowired
     private RedisTemplate<Object,Object> template;
    @Autowired
    private redistool r;
    @RequestMapping("addpost")
    @ResponseBody
    public int addpost(@RequestBody JSONObject jsonParam){
        Calendar t = Calendar.getInstance();
        Date date = t.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
        String a = sdf.format(date);
        String pname = jsonParam.getAsString("pname");
        String content = jsonParam.getAsString("content");
        String username = jsonParam.getAsString("username");
        int barid = (int) jsonParam.getAsNumber("barid");
        Post p = new Post();
        p.setPostname(pname);
        p.setPostcontent(content);
        User u = userservice.getuser(username);
        p.setUid(u.getUid());
        p.setBid(barid);
        p.setcTime(a);
           // System.out.println (jsonParam);
            int id= r.getpostID();
            //template.opsForList().rightPush("posts",jsonParam);
        return postservice.addpost(p);
    }
    @RequestMapping("getallpost")
    @ResponseBody
    public Map getallpost(@RequestBody JSONObject jsonParam) {
        int num = (int) jsonParam.getAsNumber("count");
        HashMap m = new HashMap();
        ArrayList<Post> a = postservice.getallpost(num);
        ArrayList name = new ArrayList();
        for (int i = 0; i < a.size(); i++){
            String username = userservice.getuserById(a.get(i).getUid()).getUsername();
            name.add(username);
        }
        m.put("name", name);
        m.put("post", a);
        return m;
    }

    @RequestMapping("getbarpost")
    @ResponseBody
    public Map getbarpost(@RequestBody JSONObject jsonParam) {
        int num = (int) jsonParam.getAsNumber("id");
        HashMap m = new HashMap();
        ArrayList<Post> a = postservice.getbarpost(num);
        ArrayList<String> name = new ArrayList();
        for (int i = 0; i < a.size(); i++) {
            String username = userservice.getuserById(a.get(i).getUid()).getUsername();
            name.add(username);
        }
        m.put("name", name);
        m.put("post", a);
        return m;
    }
    @RequestMapping("getpostbyInput")
    @ResponseBody
    public Map getpostbyInput(@RequestBody JSONObject jsonParam) {
        String input = jsonParam.getAsString("input");
        HashMap m = new HashMap();
        ArrayList<Post> a = postservice.getpostbyInput(input);
        ArrayList<String> name = new ArrayList();
        String aa;
        for (int i = 0; i < a.size(); i++) {
            aa = userservice.getuserById(a.get(i).getUid()).getUsername();
            name.add(aa);
        }
        m.put("posts", a);
        m.put("name", name);
        return m;
    }
    @RequestMapping("getpost")
    @ResponseBody
    public Post getpost(@RequestBody JSONObject jsonParam) {
        int a = Integer.parseInt(jsonParam.getAsString("pid"));
         return  postservice.getpost(a);
    }
    @RequestMapping("comment")
    @ResponseBody
    public int setcomment(@RequestBody JSONObject jsonParam){
       String content=jsonParam.getAsString("content");
       int p=Integer.parseInt(jsonParam.getAsString("pid"));
       String name=jsonParam.getAsString("name");
        int pcount=postservice.getpost(p).getPcount();
        Comment c=new Comment();
        c.setCcontent(content);
        c.setPostid(p);
        c.setUsername(name);
        c.setCcount(pcount+1);
        postservice.upcount(pcount+1,p);
        return postservice.setcomment(c);
    }
    @RequestMapping("getcomment")
    @ResponseBody
    public Map getcomment(@RequestBody JSONObject jsonParam){
       int a=Integer.parseInt(jsonParam.getAsString("pid"));
        ArrayList<Comment> c=postservice.getcomment(a);
        ArrayList<User> u=new ArrayList<>();
        for (int i = 0; i <c.size(); i++) {
            u.add(userservice.getuser(c.get(i).getUsername()));
        }
        HashMap m=new HashMap();
        m.put("cc",c);
        m.put("u",u);
        return m;
    }
    @RequestMapping("setcomment_t")
    @ResponseBody
    public int  setcomment_t(@RequestBody JSONObject jsonParam){
        String pcom=jsonParam.getAsString("pcom");
        String name=jsonParam.getAsString("name");
        int pid=Integer.parseInt(jsonParam.getAsString("pid"));
        int postid=Integer.parseInt(jsonParam.getAsString("postid"));
        Comment_t com=new Comment_t();
        com.setComcontent(pcom);
        com.setComuname(name);
        com.setPcommentid(pid);
        com.setPostid(postid);
        postservice.setcomment_t(com);
        return  1;
    }
    @RequestMapping("getcomment_t")
    @ResponseBody
    public Map getcomment_t(@RequestBody JSONObject jsonParam){
       int a=Integer.parseInt(jsonParam.getAsString("id"));
        ArrayList<Comment_t> com=postservice.getcomment_t(a);
        ArrayList<String> im=new ArrayList<>();
        for (int i = 0; i <com.size(); i++) {
          im.add(userservice.getuser(com.get(i).getComuname()).getUimg());
        }
        HashMap m=new HashMap();
        m.put("com",com);
        m.put("img",im);
        return m;
    }
}
