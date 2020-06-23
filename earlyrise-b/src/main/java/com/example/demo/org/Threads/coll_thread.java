package com.example.demo.org.Threads;

import com.example.demo.org.model.Collect;
import com.example.demo.org.model.Qcollect;
import com.example.demo.org.service.blogservice;
import com.example.demo.org.service.qutservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Set;

@Component
public class coll_thread implements Runnable {
    @Autowired
    private com.example.demo.org.service.blogservice blogservice;
    @Autowired
    private com.example.demo.org.service.qutservice qutservice;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Set q=template.opsForSet().members("collect_qut");
            ArrayList qs=new ArrayList();
            qs.addAll(q);
            ArrayList ls=new ArrayList();
            Qcollect qc=new Qcollect();
            for(int x=0;x<qs.size();x++){
                Set qq=template.opsForSet().members("co_qut"+qs.get(x).toString());
                ls.addAll(qq);
                qc.setUid(Integer.parseInt(qs.get(x).toString()));
                for(int xx=0;xx<ls.size();xx++){//检查用户的收藏是否已经在数据库中
                    qc.setQid(Integer.parseInt(ls.get(xx).toString()));
                    if(qutservice.getqcollect(qc)==null){//当数据库中不存在时插入数据
                        qutservice.setqcollect(qc);
                    }
                }
                while(template.opsForSet().pop("co_qut"+qs.get(x).toString())!=null){
                    template.opsForSet().pop("co_qut"+qs.get(x).toString());
                }
            }
            while (template.opsForSet().pop("collect_qut")!=null){
                template.opsForSet().pop("collect_qut");
            }

            //博客的收藏操作
            Set s=template.opsForSet().members("collect_blog");
            ArrayList a=new ArrayList();
            a.addAll(s);
            ArrayList l=new ArrayList();
            Collect c=new Collect();
            for(int i=0;i<a.size();i++){//循环输出数组中的用户
            Set ss= template.opsForSet().members("co_blog"+a.get(i).toString());
            //获得缓存中用户的收藏
            l.addAll(ss);
                c.setUid(Integer.parseInt(a.get(i).toString()));
            for(int j=0;j<l.size();j++){//检查用户的收藏是否已经在数据库中
                c.setBid(Integer.parseInt(l.get(j).toString()));
                if(blogservice.getcollect(c)==null){//当数据库中不存在时插入数据
                    blogservice.setcollect(c);
                }
            }
            while(template.opsForSet().pop("co_blog"+a.get(i).toString())!=null){
                template.opsForSet().pop("co_blog"+a.get(i).toString());
            }
        }
            while (template.opsForSet().pop("collect_blog")!=null){
                template.opsForSet().pop("collect_blog");
            }
    }
    }
}
