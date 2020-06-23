package com.example.demo.org.service;


import com.example.demo.org.model.Admin;
import com.example.demo.org.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;


@Service
public class userservice {
    @Resource
    private com.example.demo.org.mapper.usermapper usermapper;

    public User getuser(String u){
       return usermapper.getuser(u);
    }

    public int register(User u) {
        if (usermapper.getuser(u.getUsername()) != null)
            return 0;
        usermapper.setuser(u);
        return 1;
    }
    public User getuserById(int i){
        return usermapper.getuserById(i);
    }
    public void updateuser(User u){
        usermapper.updateuser(u);
    }
    public Admin getadmin(String s){
        return  usermapper.getadmin(s);
    }
    public void upusersc(int a,int b){
        usermapper.upusersc(a,b);
    }
    public ArrayList getmyatt(int a){
        return usermapper.getmyatt(a);
    }
    public ArrayList gettopuser(){
        return usermapper.gettopuser();
    }
    public ArrayList getalluser(int a){
        return usermapper.getalluser(a);
    }
    public void deluserid(int a){
         usermapper.deluserid(a);
    }
}
