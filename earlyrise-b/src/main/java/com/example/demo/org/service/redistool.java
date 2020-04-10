package com.example.demo.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class redistool {
    @Autowired
    private  RedisTemplate<Object,Object> template;
      public  int getpostID(){
          int a=(int )template.opsForValue().get("postid");
           a=a+1;
           template.opsForValue().set("postid",a);
            return a;
      }
}
