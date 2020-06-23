package com.example.demo.org.controller;
import com.example.demo.org.interceptor.SensitiveFilterService;
import com.example.demo.org.model.*;
import com.example.demo.org.service.qutservice;
import com.example.demo.org.service.userservice;
import com.fasterxml.jackson.annotation.JsonAlias;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("question")
public class qutcontroller {
    @Autowired
    private com.example.demo.org.service.userservice userservice;
    @Autowired
    private com.example.demo.org.service.qutservice qutservice;
    @Autowired
    private RedisTemplate <Object,Object> template;
    @Autowired
    private SensitiveFilterService filter;
    @RequestMapping("setquestion")
    @ResponseBody
    public int setquestion(@RequestBody JSONObject jsonparam){
        String user= jsonparam.getAsString("username");
        int u=userservice.getuser(user).getUid();
        int sc=userservice.getuser(user).getScore();
        int  s= Integer.parseInt(jsonparam.getAsString("score"));
        if(sc-s>=0){
            userservice.upusersc(u,sc-s);
        Calendar t = Calendar.getInstance();
        Date date = t.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
        String a = sdf.format(date);
        String qname=jsonparam.getAsString("title");
        String type= jsonparam.getAsString("type");
        String sample= jsonparam.getAsString("samplecontent");
        String content= jsonparam.getAsString("content");
        Question q=new Question();
        q.setQname(qname);
        q.setQsample(sample);
        q.setQtype(type);
        q.setUid(u);
        q.setqContent(content);
        q.setScore(s);
        q.setQtime(a);
        qutservice.setquestion(q);
        return 1;
        }else{
            return 0;
        }
    }
    @RequestMapping("getnewqut")
    @ResponseBody
    public Map getnewqut(@RequestBody JSONObject jsonparam){
       int a=Integer.parseInt(jsonparam.getAsString("count"));
        HashMap m=new HashMap();
      ArrayList<Question>  q=qutservice.getnewqut(a*10);
      ArrayList n=new ArrayList();
      for(int i=0;i<q.size();i++){
          n.add(userservice.getuserById(q.get(i).getUid()));
      }
        m.put("qut",q);
        m.put("user",n);
        return m;
    }
    @RequestMapping("getquestion")
    @ResponseBody
    public Question getquestion(@RequestBody JSONObject jsonparam){
       int a=Integer.parseInt(jsonparam.getAsString("qid"));
       return qutservice.getquestion(a);
    }
    @RequestMapping("ingood")
    @ResponseBody
    public int ingood(@RequestBody JSONObject jsonparam){
       int q=Integer.parseInt(jsonparam.getAsString("qid"));
       String uu=jsonparam.getAsString("username");
       int u=userservice.getuser(uu).getUid();
       template.opsForSet().add("quts",q);
        if(template.opsForSet().isMember("qut_id"+q,u)){
            return 0;
        }
        if(template.hasKey("qut"+q)){
            template.opsForValue().increment("qut"+q);
        }else{
           template.opsForValue().set("qut"+q,qutservice.getquestion(q).getQstar()+1);
        }
            template.opsForSet().add("qut_id"+q,u);//添加用户id防止重复
            return 1;
    }
    @RequestMapping("getgood")
    @ResponseBody
    public Map getgood(@RequestBody JSONObject jsonparam){
        HashMap m=new HashMap();
        int q=Integer.parseInt(jsonparam.getAsString("qid"));
        String uu=jsonparam.getAsString("username");
        int u=userservice.getuser(uu).getUid();
        m.put("if",template.opsForSet().isMember("qut_id"+q,u));
        if(template.hasKey("qut"+q)){
         m.put("good",Integer.parseInt(template.opsForValue().get("qut"+q).toString()));
        }else{
            template.opsForValue().set("qut"+q,qutservice.getquestion(q).getQstar());
            m.put("good",qutservice.getquestion(q).getQstar());
        }
        return m;
    }
    @RequestMapping("delgood")
    @ResponseBody
    public int delgood( @RequestBody JSONObject jsonparam){
        int q=Integer.parseInt(jsonparam.getAsString("qid"));
        String uu=jsonparam.getAsString("username");
        int u=userservice.getuser(uu).getUid();
        if(template.hasKey("qut"+q)){
            template.opsForValue().decrement("qut"+q);
        }else{
            template.opsForValue().set("qut"+q,qutservice.getquestion(q).getQstar()-1);
        }
        template.opsForSet().pop("qut_id"+q,u);
        return  1;
    }
    @RequestMapping("setqcollect")
    @ResponseBody
    public int setqcollect(@RequestBody JSONObject jsonparam){
        String uu=jsonparam.getAsString("uid");
        int u= userservice.getuser(uu).getUid();
        int b=Integer.parseInt(jsonparam.getAsString("qid"));
        if(template.opsForSet().isMember("co_id"+b,u)){
            return 0;
        }
        template.opsForSet().add("collect_qut",u);
        template.opsForSet().add("co_qut"+u,b);
        template.opsForSet().add("co_id"+b,u);
        return 1;
    }
    @RequestMapping("getqcollect")
    @ResponseBody
    public int getqcollect(@RequestBody JSONObject jsonparam){
        String uu=jsonparam.getAsString("uid");
        int u= userservice.getuser(uu).getUid();
        int b=Integer.parseInt(jsonparam.getAsString("qid"));
        Qcollect q=new Qcollect();
        q.setUid(u);
        q.setQid(b);
        if(template.opsForSet().isMember("co_qut"+u,b)){
            return 1;
        }
        else{
            if(qutservice.getqcollect(q)!=null){
                return 1;
            }else{
                return 0;
            }
        }
    }
    @RequestMapping("delqcollect")
    @ResponseBody
    public int dekqcollect(@RequestBody JSONObject jsonparam){
        String uu=jsonparam.getAsString("uid");
        int u= userservice.getuser(uu).getUid();
        int b=Integer.parseInt(jsonparam.getAsString("qid"));
        Qcollect c=new Qcollect();
        c.setUid(u);
        c.setQid(b);
        qutservice.delqcollect(c);
        if(template.hasKey("co_qut"+u)){
            template.opsForSet().remove("co_qut"+u,b);
        }
        template.opsForSet().pop("co_id"+b,u);
        return 1;
    }
    @RequestMapping("getstarqut")
    @ResponseBody
    public Map getstarqut(@RequestBody JSONObject jsonparam){
        int a= Integer.parseInt(jsonparam.getAsString("count"));
        String uu=jsonparam.getAsString("uid");
        int u= userservice.getuser(uu).getUid();
        HashMap m=new HashMap();
        ArrayList<Question> l=qutservice.getstarqut(a*10,u);
        ArrayList n=new ArrayList();
        for(int i=0;i<l.size();i++){
            n.add(userservice.getuserById(l.get(i).getUid()));
        }
        m.put("qut",l);
        m.put("user",n);
        return m;
    }
    @RequestMapping("getmaxqut")
    @ResponseBody
    public Map getmaxqut(@RequestBody JSONObject jsonparam){
        HashMap m=new HashMap();
        int a= Integer.parseInt(jsonparam.getAsString("count"));
       ArrayList<Question> q= qutservice.getmaxqut(a);
       ArrayList l=new ArrayList();
       for(int i=0;i<q.size();i++){
           l.add(userservice.getuserById(q.get(i).getUid()));
       }
        m.put("qut",q);
        m.put("user",l);
        return m;
    }
    @RequestMapping("inqaccess")
    @ResponseBody
    public int inqaccess(@RequestBody JSONObject jsonparam){
       int qid= Integer.parseInt(jsonparam.getAsString("qid"));
       template.opsForSet().add("count_qaccess",qid);
       if(template.hasKey("qaccess"+qid)){
           template.opsForValue().increment("qaccess"+qid);
       }else {
         template.opsForValue().set("qaccess"+qid,qutservice.getquestion(qid).getQaccess()+1);
       }
       return 1;
    }
    @RequestMapping("getqaccess")
    @ResponseBody
    public int getqaccess(@RequestBody JSONObject jsonparam){
        int qid= Integer.parseInt(jsonparam.getAsString("qid"));
        if(template.hasKey("qaccess"+qid)) {
            return Integer.parseInt(template.opsForValue().get("qaccess"+qid).toString());
        }else{
            template.opsForValue().set("qaccess"+qid,qutservice.getquestion(qid).getQaccess());
            return Integer.parseInt(template.opsForValue().get("qaccess"+qid).toString());
        }
    }
    @RequestMapping("setqcomment")
    @ResponseBody
    public int setqcomment(@RequestBody JSONObject jsonparam){
        String cc= jsonparam.getAsString("content");
        cc= filter.replaceSensitiveWord(cc,2,"*");
        Calendar t = Calendar.getInstance();
        Date date = t.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
        String a = sdf.format(date);
        String u=jsonparam.getAsString("username");
        int uid=userservice.getuser(u).getUid();
        int qid=Integer.parseInt(jsonparam.getAsString("qid"));
        Qcomment q=new Qcomment();
        q.setQcontent(cc);
        q.setUid(uid);
        q.setQid(qid);
        q.setQtime(a);
        qutservice.setqcomment(q);
        return 1;
    }
    @RequestMapping("getqcomment")
    @ResponseBody
    public Map getqcomment(@RequestBody JSONObject jsonparam){
       int qid= Integer.parseInt(jsonparam.getAsString("qid"));
       ArrayList<Qcomment> a= qutservice.getqcomment(qid);
       ArrayList u=new ArrayList();
       for(int i=0;i<a.size();i++){
         User uu= userservice.getuserById(a.get(i).getUid());
         u.add(uu);
       }
       HashMap m=new HashMap();
       m.put("qcom",a);
       m.put("user",u);
       return m;
    }
    @RequestMapping("ok")
    @ResponseBody
    public int ok(@RequestBody JSONObject jsonparam){
       int qcomid=Integer.parseInt(jsonparam.getAsString("qcomid"));
       int qid= Integer.parseInt(jsonparam.getAsString("qid"));
       int uid=Integer.parseInt(jsonparam.getAsString("uid"));
       int score=Integer.parseInt(jsonparam.getAsString("score"));
        qutservice.setqcomsign(qcomid);
        qutservice.setquessign(qid);
        int sc=  userservice.getuserById(uid).getScore();
        userservice.upusersc(uid,score+sc);
        return 1;
    }
    @RequestMapping("getmyqut")
    @ResponseBody
    public Map getmyqut(@RequestBody JSONObject jsonparam){
        HashMap m=new HashMap();
        String username= jsonparam.getAsString("username");
        int uid=userservice.getuser(username).getUid();
        int count =Integer.parseInt(jsonparam.getAsString("count"));
        ArrayList<Question> a=qutservice.getmyqut(uid,10*count);
        ArrayList l=new ArrayList();
        for(int i=0;i<a.size();i++){
            User uuu=userservice.getuserById(a.get(i).getUid());
            l.add(uuu);
        }
        m.put("qut",a);
        m.put("user",l);
        return m;
    }
    @RequestMapping("delqut")
    @ResponseBody
    public int delqut(@RequestBody JSONObject jsonparam){
       int a=Integer.parseInt(jsonparam.getAsString("qid"));
        qutservice.delqut(a);
        return  1;
    }
    @RequestMapping("gettit")
    @ResponseBody
    public Map gettit(@RequestBody JSONObject jsonparam){
        String tit=jsonparam.getAsString("title");
        ArrayList a= qutservice.gettit(tit);
        HashMap m=new HashMap();
        m.put("qut",a);
        return m;
    }
    @RequestMapping("upqut")
    @ResponseBody
    public void upqut(@RequestBody JSONObject jsonparam){
        int qid=Integer.parseInt(jsonparam.getAsString("qid"));
        String tit=  jsonparam.getAsString("newtit");
        String star= jsonparam.getAsString("newstar");
        String acc=jsonparam.getAsString("newaccess");
        String shor= jsonparam.getAsString("newshort");
        Question q=qutservice.getquestion(qid);
        if(jsonparam.getAsString("newscore")!=null){
            int score=Integer.parseInt(jsonparam.getAsString("newscore"));
            q.setScore(score);
        }
        if(tit!=null){
            q.setQname(tit);
        }
        if(star!=null){
            q.setQstar(Integer.parseInt(star));
        }
        if(acc!=null){
            q.setQaccess(Integer.parseInt(acc));
        }
        if(shor!=null){
            q.setQsample(shor);
        }
        qutservice.upqut(q);
    }
}
