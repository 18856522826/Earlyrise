package com.example.demo.org.Threads;

import com.example.demo.org.model.Attention;
import com.example.demo.org.service.blogservice;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Set;
@Component
public class Att_thread implements Runnable {
    @Autowired
    private com.example.demo.org.service.blogservice blogservice;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            Set s =template.opsForSet().members("att");
            ArrayList a=new ArrayList(s);
            ArrayList l=new ArrayList();
            Attention att=new Attention();
            int lu;
            int ru;
            for(int i=0;i<a.size();i++){
                Set ss=template.opsForSet().members(a.get(i));
                l.addAll(ss);
                lu=Integer.parseInt(a.get(i).toString().replace("left",""));
                att.setLuser(lu);
                for(int j=0;j<l.size();j++){
                    ru=Integer.parseInt(l.get(j).toString());
                    att.setRuser(ru);
                    if(blogservice.getattention(lu,ru)==null){
                        blogservice.setattention(att);
                    }
                }
                l.clear();
                while(template.opsForSet().pop(a.get(i))!=null){
                    template.opsForSet().pop(a.get(i));
                }
            }
            while(template.opsForSet().pop("att")!=null){
                template.opsForSet().pop("att");
            }
        }
    }
}
