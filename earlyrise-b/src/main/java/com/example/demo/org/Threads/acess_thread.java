package com.example.demo.org.Threads;

import com.example.demo.org.service.blogservice;
import com.example.demo.org.service.qutservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;
@Component
public class acess_thread implements Runnable{
    @Autowired
    private com.example.demo.org.service.blogservice blogservice;
    @Autowired
    private com.example.demo.org.service.qutservice qutservice;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            //问答访问量更新
            Set q=template.opsForSet().members("count_qaccess");
            ArrayList qq=new ArrayList();
            qq.addAll(q);
            for(int i=0;i<qq.size();i++){
                int cc=Integer.parseInt(template.opsForValue().get("qaccess"+qq.get(i)).toString());
                qutservice.upqutacc(Integer.parseInt(qq.get(i).toString()),cc);
            }
            //博客访问量更新
            Set s= template.opsForSet().members("count_access");
            ArrayList a=new ArrayList();
            a.addAll(s);
            for(int i=0;i<a.size();i++){
              int cc=Integer.parseInt(template.opsForValue().get("count"+a.get(i)).toString());
              blogservice.upblogacc(Integer.parseInt(a.get(i).toString()),cc);
            }
        }

    }
}
