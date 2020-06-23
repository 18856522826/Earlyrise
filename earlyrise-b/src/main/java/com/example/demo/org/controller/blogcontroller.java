package com.example.demo.org.controller;

import com.example.demo.org.Threads.Att_thread;
import com.example.demo.org.interceptor.SensitiveFilterService;
import com.example.demo.org.model.*;
import com.example.demo.org.service.userservice;
import com.fasterxml.jackson.annotation.JsonAlias;
import net.minidev.json.JSONObject;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
@RequestMapping("blog")
public class blogcontroller {

    @Autowired
    private  com.example.demo.org.service.blogservice blogservice;
    @Autowired
    private com.example.demo.org.service.userservice userservice;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Autowired
    private SensitiveFilterService filter;

    @RequestMapping("setblog")
    @ResponseBody
    public int setblog(@RequestBody JSONObject jsonparam){
        Calendar t = Calendar.getInstance();
        Date date = t.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
        String a = sdf.format(date);
        String bname= jsonparam.getAsString("title");
        String end=jsonparam.getAsString("sampletext");
        String bcontent=jsonparam.getAsString("content");
        String uname=jsonparam.getAsString("username");
       int uid=userservice.getuser(uname).getUid();
        Blog b=new Blog(bname,end,bcontent,0,0,uid,a);
        blogservice.setblog(b);
        return 1;
    }
    @RequestMapping("getblog")
    @ResponseBody
    public Blog getblog(@RequestBody JSONObject jsonparam){
       int id=Integer.parseInt(jsonparam.getAsString("id"));
            return blogservice.getblog(id);
    }
    @RequestMapping("delblog")
    @ResponseBody
    public int delblog(@RequestBody JSONObject jsonparam){
       int bid= Integer.parseInt(jsonparam.getAsString("bid"));
       blogservice.delblog(bid);
       return 1;

    }
    @RequestMapping("getnewblog")
    @ResponseBody
    public Map getnewblog(@RequestBody JSONObject jsonparam){
       int count= Integer.parseInt(jsonparam.getAsString("count"));
        ArrayList<Blog> a=blogservice.getnewblog(10*count);
        ArrayList z=new ArrayList();
        HashMap m=new HashMap();
        for(int i=0;i<a.size();i++){
          User u= userservice.getuserById(a.get(i).getUid());
          z.add(u);
        }
        m.put("blog",a);
        m.put("user",z);
        return m;
    }
    @RequestMapping("getmaxblog")
    @ResponseBody
    public Map getmaxblog(@RequestBody JSONObject jsonparam){
        int count= Integer.parseInt(jsonparam.getAsString("count"));
        HashMap m=new HashMap();
       ArrayList<Blog> a=blogservice.getmaxblog(10*count);
       ArrayList z=new ArrayList();
       for(int i=0;i<a.size();i++){
           User u= userservice.getuserById(a.get(i).getUid());
           z.add(u);
       }
       m.put("blog",a);
       m.put("user",z);
       return m;
    }
    @RequestMapping("getstarblog")
    @ResponseBody
    public Map getstarblog(@RequestBody JSONObject jsonparam){
        int count= Integer.parseInt(jsonparam.getAsString("count"));
        String uu=jsonparam.getAsString("user");
        int u=userservice.getuser(uu).getUid();
        HashMap m=new HashMap();
        ArrayList <Blog> a=blogservice.getstarblog(u,10*count);
        ArrayList z=new ArrayList();
        for(int i=0;i<a.size();i++){
            User x=userservice.getuserById(a.get(i).getUid());
            z.add(x);
        }
        m.put("blog",a);
        m.put("user",z);
        return m;
    }
    @RequestMapping("setcom")
    @ResponseBody
    public int setcom(@RequestBody JSONObject jsonparam){
        Calendar t = Calendar.getInstance();
        Date date = t.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
        String a = sdf.format(date);
        String bcontent=jsonparam.getAsString("content");
        bcontent= filter.replaceSensitiveWord(bcontent,2,"*");
        int bid= Integer.parseInt(jsonparam.getAsString("bid"));
        String name=(String)jsonparam.getAsString("username");
        int uid= userservice.getuser(name).getUid();
        Bcomment bcomment=new Bcomment(bcontent,bid,uid,a);
        blogservice.setbcom(bcomment);
        return 1;
    }
    @RequestMapping("getbcoms")
    @ResponseBody
    public Map getbcoms(@RequestBody JSONObject jsonparam){
        HashMap m=new HashMap();
          int bid= Integer.parseInt(jsonparam.getAsString("bid"));
        ArrayList<Bcomment> b=blogservice.getbcoms(bid);
        ArrayList u=new ArrayList();
        for(int i=0;i<b.size();i++){
           User uu=userservice.getuserById(b.get(i).getUid());
           u.add(uu);
        }
        m.put("bcoms",b);
        m.put("users",u);
        return m;
    }
    @RequestMapping("attention")
    @ResponseBody
    public int setattention(@RequestBody JSONObject jsonparam){
        String a=jsonparam.getAsString("left");
        int aa= userservice.getuser(a).getUid();
        int b=Integer.parseInt(jsonparam.getAsString("right"));
        template.opsForSet().add("left"+aa,b);
        template.opsForSet().add("att","left"+aa);
        return  1;
    }
    @RequestMapping("getattention")
    @ResponseBody
    public int getattention(@RequestBody JSONObject jsonparam){
        String u= jsonparam.getAsString("left");
        int a=userservice.getuser(u).getUid();
        int b=Integer.parseInt(jsonparam.getAsString("right"));
        Set s=template.opsForSet().members("left"+a);
        ArrayList l=new ArrayList();
        l.addAll(s);
        int z;
        for(int i=0;i<l.size();i++){
            z=Integer.parseInt(l.get(i).toString());
            if(z==b){
                return 1;
            }
        }
      if(blogservice.getattention(a,b)!=null){
          return 1;
      }else{
          return 0;
      }
    }
    @RequestMapping("delattention")
    @ResponseBody
    public int delattention(@RequestBody JSONObject jsonparam){
      String u=jsonparam.getAsString("left");
      int a=userservice.getuser(u).getUid();
      int b=Integer.parseInt(jsonparam.getAsString("right"));
        blogservice.delattention(a,b);
        if(template.opsForSet().isMember("left"+a,b)){
            template.opsForSet().remove("left"+a,b);
        }
        return 1;
    }
    @RequestMapping("ingood")
    @ResponseBody
    public int ingood(@RequestBody JSONObject jsonparam){
       int a= Integer.parseInt(jsonparam.getAsString("bid"));
       String u=jsonparam.getAsString("user");
       int uid=userservice.getuser(u).getUid();
        template.opsForSet().add("good_s",a);
        if(!template.opsForSet().isMember("good_id"+a,uid)){
            if (template.hasKey("good" + a)) {
                template.opsForValue().increment("good" + a);
            } else {
                template.opsForValue().set("good" + a, blogservice.getblog(a).getBstar() + 1);
            }
        }
        template.opsForSet().add("good_id"+a,uid);//在缓存中添加文章和用户id防止重复点赞
        return 1;
    }
    @RequestMapping("degood")
    @ResponseBody
    public int degood(@RequestBody JSONObject jsonparam){
        int a= Integer.parseInt(jsonparam.getAsString("bid"));
        String u=jsonparam.getAsString("user");
        int uid=userservice.getuser(u).getUid();
        if(template.opsForSet().isMember("good_id"+a,uid)){
        if(template.hasKey("good"+a)){
            template.opsForValue().decrement("good"+a);
        }else{
            template.opsForValue().set("good"+a,blogservice.getblog(a).getBstar()-1);
        }
        }
        template.opsForSet().pop("good_id"+a,uid);//在缓存中删除文章和用户id
        return 1;
    }
    @RequestMapping("getgood")
    @ResponseBody
    public Map getgood(@RequestBody JSONObject jsonparam){
        HashMap m=new HashMap();
       int b=Integer.parseInt(jsonparam.getAsString("bid"));
        String u=jsonparam.getAsString("user");
        int uid=userservice.getuser(u).getUid();
         m.put("if",template.opsForSet().isMember("good_id"+b,uid));
       if(template.hasKey("good"+b)){
           m.put("good",Integer.parseInt(template.opsForValue().get("good"+b).toString()));
       }else{
           m.put("good",blogservice.getblog(b).getBstar());
       }
       return m;
    }
    @RequestMapping("inaccess")
    @ResponseBody
    public int inaccess(@RequestBody JSONObject jsonparam){
        int a=Integer.parseInt(jsonparam.getAsString("bid"));
        template.opsForSet().add("count_access",a);
        if(template.hasKey("count"+a)){
            template.opsForValue().increment("count"+a);
        }else {
            template.opsForValue().set("count"+a,blogservice.getblog(a).getBaccess()+1);
        }
        return 1;
    }
    @RequestMapping("getaccess")
    @ResponseBody
    public int getaccess(@RequestBody JSONObject jsonparam){
        int a=Integer.parseInt(jsonparam.getAsString("bid"));
        if(template.hasKey("count"+a)){
            return Integer.parseInt(template.opsForValue().get("count"+a).toString());
        }else{
            template.opsForValue().set("count"+a,blogservice.getblog(a).getBaccess());
            return Integer.parseInt(template.opsForValue().get("count"+a).toString());
        }
    }
    @RequestMapping("setcollect")
    @ResponseBody
    public int setcollect(@RequestBody JSONObject jsonparam){
            String uu=jsonparam.getAsString("uid");
            int u= userservice.getuser(uu).getUid();
            int b=Integer.parseInt(jsonparam.getAsString("bid"));
            template.opsForSet().add("collect_blog",u);
            template.opsForSet().add("co_blog"+u,b);
            return 1;
    }
    @RequestMapping("delcollect")
    @ResponseBody
    public int delcollect(@RequestBody JSONObject jsonparam){
        String uu=jsonparam.getAsString("uid");
        int u= userservice.getuser(uu).getUid();
        int b=Integer.parseInt(jsonparam.getAsString("bid"));
        Collect c=new Collect();
        c.setUid(u);
        c.setBid(b);
        blogservice.delcollect(c);
        if(template.hasKey("co_blog"+u)){
            template.opsForSet().remove("co_blog"+u,b);
        }
        return 1;
    }
    @RequestMapping("getcollect")
    @ResponseBody
    public int getcollect(@RequestBody JSONObject jsonparam){
        String uu=jsonparam.getAsString("uid");
        int u= userservice.getuser(uu).getUid();
        int b=Integer.parseInt(jsonparam.getAsString("bid"));
        Collect c=new Collect();
        c.setUid(u);
        c.setBid(b);
        if(template.opsForSet().isMember("co_blog"+u,b)){
            return 1;
        }
        else{
           if(blogservice.getcollect(c)!=null){
               return 1;
           }else{
               return 0;
           }
        }
    }
    @RequestMapping("getstarlist")
    @ResponseBody
    public Map getstarlist(@RequestBody JSONObject jsonparam){
       String uu=jsonparam.getAsString("uid");
       int u= userservice.getuser(uu).getUid();
      ArrayList<Blog> a= blogservice.getstarlist(u);
       HashMap m=new HashMap();
       ArrayList l=new ArrayList();
       for (int i=0;i<a.size();i++){
           User uuu=userservice.getuserById(a.get(i).getUid());
           l.add(uuu);
       }
       m.put("blog",a);
       m.put("user",l);
        return m;
    }
    @RequestMapping("getlikeblog")
    @ResponseBody
    public Map getlikeblog(@RequestBody JSONObject jsonparam){
        String a=jsonparam.getAsString("like");
        int count=Integer.parseInt(jsonparam.getAsString("count"));
        ArrayList blog= blogservice.getlikeblog(count*10,a);
        HashMap m=new HashMap();
        m.put("blog",blog);
        return m;
    }
    @RequestMapping("updateblog")
    @ResponseBody
    public int updateblog(@RequestBody JSONObject jsonparam){
        int bid=Integer.parseInt(jsonparam.getAsString("bid"));
        String tit=  jsonparam.getAsString("newtit");
        String star= jsonparam.getAsString("newstar");
        String acc=jsonparam.getAsString("newaccess");
        String shor= jsonparam.getAsString("newshort");
        Blog b=blogservice.getblog(bid);
        if(tit!=null){
          b.setBname(tit);
        }
        if(star!=null){
          b.setBstar(Integer.parseInt(star));
        }
        if(acc!=null){
            b.setBaccess(Integer.parseInt(acc));
        }
        if(shor!=null){
            b.setBshort(shor);
        }
        blogservice.updateblog(b);
        return 1;
    }
    @RequestMapping("getmyblog")
    @ResponseBody
    public Map getmyblog(@RequestBody JSONObject jsonparam){
       HashMap m=new HashMap();
      String username= jsonparam.getAsString("username");
      int uid=userservice.getuser(username).getUid();
       int count =Integer.parseInt(jsonparam.getAsString("count"));
       ArrayList <Blog>a=blogservice.getmyblog(uid,10*count);
        ArrayList l=new ArrayList();
        for (int i=0;i<a.size();i++){
            User uuu=userservice.getuserById(a.get(i).getUid());
            l.add(uuu);
        }
        m.put("blog",a);
        m.put("user",l);
        return m;
    }
    @RequestMapping("draft")
    @ResponseBody
    public int setdraft(@RequestBody JSONObject jsonparam){
      String tit=  jsonparam.getAsString("title");
      String dra=  jsonparam.getAsString("draft");
      String sho=  jsonparam.getAsString("short");
      String user=  jsonparam.getAsString("user");
      int uid= userservice.getuser(user).getUid();
        draft d=new draft(tit,sho,dra,uid);
        blogservice.setdraft(d);
        return 1;
    }
    @RequestMapping("getnewdra")
    @ResponseBody
    public Map getnewdra(@RequestBody JSONObject jsonparam){
        String s= jsonparam.getAsString("username");
        HashMap m=new HashMap();
        int uid=userservice.getuser(s).getUid();
        ArrayList <draft> a= blogservice.getnewdra(uid);
        m.put("draft",a);
        m.put("user",userservice.getuser(s));
        return m;
    }
    @RequestMapping("deldra")
    @ResponseBody
    public int getdra(@RequestBody JSONObject jsonparam){
        int did= Integer.parseInt(jsonparam.getAsString("draid"));
        blogservice.deldra(did);
        return 1;
    }
}
