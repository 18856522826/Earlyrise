package com.example.demo.org.controller;

import com.example.demo.org.model.Bar;
import com.example.demo.org.service.barservice;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


@Controller
@RequestMapping("bar")
public class barcontroller {
    @Autowired
    private com.example.demo.org.service.barservice barservice;
    @Autowired
    private HttpServletRequest http;
    @Autowired
    private com.example.demo.org.service.userservice userservice;
    @RequestMapping("getallbar")
    @ResponseBody
    public ArrayList<Bar> getallbar(@RequestBody JSONObject jsonParam){
        String data=jsonParam.getAsString("input");
        return barservice.getallbar(data);
    }
    @RequestMapping("upload")
    @ResponseBody
    public String upload(@RequestParam(value="name") MultipartFile  file) throws IOException {
             String path="d:/earlyriseu/";
             String suffix=file.getOriginalFilename().split("\\.")[1];
             Calendar date=Calendar.getInstance();
             Date a=date.getTime();
             SimpleDateFormat z=new SimpleDateFormat("yyyyMMddHHmmssSSSS");
             String s=z.format(a)+"."+suffix;
             String pp=path;
             byte aa[]=file.getBytes();
             String imsrc= barservice.savefile(s,aa,pp);
             return imsrc;
    }
    @RequestMapping("barreg")
    @ResponseBody
     public int barreg(@RequestBody  JSONObject jsonParam){
       String img=jsonParam.getAsString("img");
       String name=jsonParam.getAsString("name");
       String cont=jsonParam.getAsString("content");
       String user=jsonParam.getAsString("user");
       Bar b=new Bar();
       b.setBarname(name);
       b.setBarbrief(cont);
       b.setBarimg(img);
       int u=userservice.getuser(user).getUid();
       b.setBaruser(u);
       return  barservice.barreg(b);
      }
}
