package com.example.demo.org.Threads;

import com.example.demo.org.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;
@Component
public class score_thread implements Runnable{
    @Autowired
    private com.example.demo.org.service.userservice userservice;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Override
    public void run() {
        while (true){
        try {
            Thread.sleep(1000*60*60*3);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        //签到增加积分
        Set s=template.opsForSet().members("score_uid");
        ArrayList l=new ArrayList();
        l.addAll(s);
        for(int i=0;i<l.size();i++){
            String z=template.opsForValue().get("score"+l.get(i)).toString();
            int count =Integer.parseInt(z);
            userservice.upusersc(Integer.parseInt(l.get(i).toString()),count);
            template.delete("score"+Integer.parseInt(l.get(i).toString()));
        }
        while (template.opsForSet().pop("score_uid")!=null){
            template.opsForSet().pop("score_uid");
        }
        }
    }



}
