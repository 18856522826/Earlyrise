package com.example.demo.org.service;


import com.example.demo.org.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class userservice {
    @Autowired
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


}
