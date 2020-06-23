package com.example.demo.org.Threads;

import com.example.demo.org.service.blogservice;
import com.example.demo.org.service.qutservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

@Component
public class good_thread implements Runnable {
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Autowired
    private com.example.demo.org.service.blogservice blogservice;
    @Autowired
    private com.example.demo.org.service.qutservice qutservice;

    @Override
    public void run() {
        while (true){
            try { Thread.sleep(1000);} catch (InterruptedException e){ e.printStackTrace(); }
            Set q= template.opsForSet().members("quts");
            ArrayList qs=new ArrayList();
            qs.addAll(q);
            int qstar=0;
            for(int j=0;j<qs.size();j++){
                qstar=Integer.parseInt(template.opsForValue().get("qut"+qs.get(j)).toString());
                System.out.println(qstar);
                qutservice.upqutgood(qstar,Integer.parseInt(qs.get(j).toString()));
            }
            while(template.opsForSet().pop("quts")!=null){
                template.opsForSet().pop("quts");
            }

            //博客的点赞更新
            Set s= template.opsForSet().members("good_s");
            ArrayList a=new ArrayList();
            a.addAll(s);
            int goodid=0;
            for(int i=0;i<a.size();i++){
               goodid=Integer.parseInt(template.opsForValue().get("good"+a.get(i)).toString());
               blogservice.upblog(Integer.parseInt(a.get(i).toString()),goodid);
            }
            while(template.opsForSet().pop("good_s")!=null){
                template.opsForSet().pop("good_s");
            }
        }
    }
}
