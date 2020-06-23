package com.example.demo.org.controller;
import com.example.demo.org.config.MyWebSocket;
import com.example.demo.org.model.User;
import com.example.demo.org.service.userservice;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("mes")
public class mescontroller {
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Autowired
    private userservice userservice;
    @RequestMapping("send")
    @ResponseBody
    public void send(@RequestBody JSONObject jsonparam) throws IOException {
        Calendar t = Calendar.getInstance();
        Date date = t.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
        String a = sdf.format(date);
        String u= jsonparam.getAsString("username");
        String ru= jsonparam.getAsString("ruser");
        String  c=jsonparam.getAsString("content");
        template.opsForList().rightPush("L"+u+"R"+ru,a+"_"+c);
        template.opsForSet().add("meslist"+u,ru);
        template.opsForSet().add("meslist"+ru,u);
        if(MyWebSocket.webSocketMap.get(ru)!=null) {
          System.out.println(MyWebSocket.webSocketMap.get(ru));
          MyWebSocket.webSocketMap.get(ru).sendMessage(c);
        } }
    @RequestMapping("getmes")
    @ResponseBody
    public Map getmes(@RequestBody JSONObject jsonparam){
        String u= jsonparam.getAsString("username");
        String ru= jsonparam.getAsString("ruser");
        ArrayList l=new ArrayList();
     for (int i=0;i<template.opsForList().size("L"+u+"R"+ru);i++){
         l.add(template.opsForList().index("L"+u+"R"+ru,i)+"0");
     }
     ArrayList r=new ArrayList();
        for (int j=0;j<template.opsForList().size("L"+ru+"R"+u);j++){
            r.add(template.opsForList().index("L"+ru+"R"+u,j)+"1");
        }
        HashMap m=new HashMap();
        m.put("myleft",l);
        m.put("yourig",r);
        return m;
    }
    @RequestMapping("getmeslist")
    @ResponseBody
    public Map getmeslist(@RequestBody JSONObject jsonparam){
      String u=jsonparam.getAsString("username");
      Set meslist= template.opsForSet().members("meslist"+u);
      ArrayList a=new ArrayList();
      a.addAll(meslist);
      ArrayList users=new ArrayList();
      for(int i=0;i<a.size();i++){
       User user= userservice.getuser(a.get(i).toString());
          users.add(user);
      }
      HashMap m=new HashMap();
      m.put("users",users);
      return m;
    }
}
