package com.example.demo.org.controller;

import com.example.demo.org.model.Admin;
import com.example.demo.org.model.Attention;
import com.example.demo.org.model.User;
import com.example.demo.org.service.barservice;
import com.fasterxml.jackson.annotation.JsonAlias;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value="user")
public class usercontroller {
    @Autowired
    private com.example.demo.org.service.userservice userservice;
    @Autowired
    private barservice barservice;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private RedisTemplate<Object,Object> template;

    private static String Pin;
    @ResponseBody
    @RequestMapping(value="login")
    public int login(@RequestBody JSONObject jsonParam){
        String username=jsonParam.getAsString("username");
        String password=jsonParam.getAsString("password");
        if(userservice.getuser(username)==null){
            return 3;
        }
        String p=userservice.getuser(username).getPassword();
       if(password.equals(p)){
           return 1;
       }else{
           return 0;
       }
    }
    @RequestMapping(value="register")
    @ResponseBody
    public int register(@RequestBody JSONObject jsonParam){
        String username=jsonParam.getAsString("name");
        String pas=jsonParam.getAsString("pas");
        String pin =jsonParam.getAsString("pin");
        if(!pin.equals(Pin)){
            return 3;
        }
        User u=new User();
        u.setUsername(username);
        u.setPassword(pas);
        return userservice.register(u);
    }
    @RequestMapping("upload")
    @ResponseBody
    public String upload(@RequestParam(value="name") MultipartFile file) throws IOException {
        String path="d:/earlyriseu/";
        //String path="/earlyriseu/";//线上
        String suffix= Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        Calendar date=Calendar.getInstance();
        Date a=date.getTime();
        SimpleDateFormat z=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String s=z.format(a)+"."+suffix;
        String pp=path;
        byte[] aa = file.getBytes();
        String imgsrc= barservice.savefile(s,aa,pp);
        return imgsrc;
    }
    @RequestMapping("updateimg")
    @ResponseBody
    public void update(@RequestBody JSONObject jsonparam){
       String img=jsonparam.getAsString("img");
       String name=jsonparam.getAsString("name");
       System.out.println(name+"aaaa");
       User u=userservice.getuser(name);
       u.setUimg(img);
       userservice.updateuser(u);
    }
    @RequestMapping("getuserimg")
    @ResponseBody
    public String getuserimg(@RequestBody JSONObject jsonParam){
       String name=jsonParam.getAsString("name");
       return userservice.getuser(name).getUimg();
    }
    @RequestMapping("getuserbyid")
    @ResponseBody
    public User getuserbyid(@RequestBody JSONObject jsonparam){
        int a=Integer.parseInt(jsonparam.getAsString("id"));
        return userservice.getuserById(a);
    }
    @RequestMapping("getuser")
    @ResponseBody
    public User getuser(@RequestBody JSONObject jsonParam){
        String name=jsonParam.getAsString("name");
        return userservice.getuser(name);
    }
    @RequestMapping("mail")
    @ResponseBody
    public void  sendSimpleMail(@RequestBody JSONObject jsonParam){
        String email=jsonParam.getAsString("email");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");
        message.setFrom("1825217778@qq.com");
        message.setTo(email);
        message.setSentDate(new Date());
        int rdom=(int)(Math.random()*100000);
        String a=String.valueOf(rdom);
        message.setText(a);
        Pin=a;
        javaMailSender.send(message);}
    @RequestMapping("upuser")
    @ResponseBody
    public int upuser(@RequestBody JSONObject jsonparam){
        String u= jsonparam.getAsString("username");
        String nick= jsonparam.getAsString("nickname");
        int  sex=Integer.parseInt(jsonparam.getAsString("sex"));
        String school=jsonparam.getAsString("school");
        User uu= userservice.getuser(u);
        uu.setBach(sex);
        uu.setSchool(school);
        uu.setNickname(nick);
        userservice.updateuser(uu);
        return  1;
    }
    @RequestMapping("adminlogin")
    @ResponseBody
    public Map getadmin(@RequestBody JSONObject jsonparam){
       String username= jsonparam.getAsString("username");
       String password= jsonparam.getAsString("password");
        HashMap m=new HashMap();
    Admin a= userservice.getadmin(username);
    if(a.getApassword().equals(password)){
        m.put("if",1);
        m.put("aid",a.getAID());
    }else {
        m.put("if",0);
    }
    return m;
    }
    @RequestMapping("addscore")
    @ResponseBody
    public int addscore(@RequestBody JSONObject jsonparam){
       String u=jsonparam.getAsString("username");
        int uid=userservice.getuser(u).getUid();
        template.opsForSet().add("score_uid",uid);
        if(template.hasKey("score"+uid)){
            template.opsForValue().increment("score"+uid,10);
        }else{
            int sc=userservice.getuser(u).getScore();
            template.opsForValue().set("score"+uid,sc+10);
        }
        return 1;
    }
    @RequestMapping("ifsign")
    @ResponseBody
    public int ifsign(@RequestBody JSONObject jsonparam){
        String u=jsonparam.getAsString("username");
          int uid=userservice.getuser(u).getUid();
        if(template.hasKey("score"+uid)){
            return 1;
        }else {
            return 0;
        }
    }
    @RequestMapping("getmyatt")
    @ResponseBody
    public Map getmyatt(@RequestBody JSONObject jsonparam){
       String username=jsonparam.getAsString("username");
       int uid=userservice.getuser(username).getUid();
       HashMap m=new HashMap();
       ArrayList<Attention> a=userservice.getmyatt(uid);
       ArrayList l=new ArrayList();
       for(int i=0;i<a.size();i++){
           l.add(userservice.getuserById(a.get(i).getRuser()));
       }
            m.put("user",l);
       return  m;
    }
    @RequestMapping("gettopuser")
    @ResponseBody
    public Map gettopuser(@RequestBody JSONObject jsonparam){
        HashMap m=new HashMap();
       ArrayList u= userservice.gettopuser();
        m.put("user",u);
        return m;
    }
    @RequestMapping("getalluser")
    @ResponseBody
    public Map getalluser(@RequestBody JSONObject jsonparam){
       HashMap m=new HashMap();
        int a= Integer.parseInt(jsonparam.getAsString("count"));
        ArrayList l= userservice.getalluser(a*10);
        m.put("users",l);
        return m;
    }
    @RequestMapping("deluserid")
    @ResponseBody
    public int deluserid(@RequestBody JSONObject jsonparam){
        int id=Integer.parseInt(jsonparam.getAsString("id"));
        userservice.deluserid(id);
        return 1;
    }

}
