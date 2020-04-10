package com.example.demo.org.controller;

import com.example.demo.org.model.User;
import com.example.demo.org.service.barservice;
import com.fasterxml.jackson.annotation.JsonAlias;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


@Controller
@RequestMapping(value="user")
public class usercontroller {
    @Autowired
    private com.example.demo.org.service.userservice userservice;
    @Autowired
    private barservice barservice;
    @Autowired
    JavaMailSender javaMailSender;
    private static String Pin;
    @ResponseBody
    @RequestMapping(value="/login")
    public int login(@RequestBody JSONObject jsonParam){
        String username=jsonParam.getAsString("username");
        String password=jsonParam.getAsString("password");
        String p=userservice.getuser(username).getPassword();
        System.out.println(p);
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
        javaMailSender.send(message);
    }
}
