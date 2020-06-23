package com.example.demo.org.service;
import com.example.demo.org.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class blogservice {
    @Resource
    private com.example.demo.org.mapper.blogmapper blogmapper;

    public void setblog(Blog b){
        blogmapper.setblog(b);
    }
    public Blog getblog(int a){
       return  blogmapper.getblog(a);
    }
    public ArrayList getnewblog(int a){
        return blogmapper.getnewblog(a);
    }
    public ArrayList getmaxblog(int a){
        return blogmapper.getmaxblog(a);
    }
    public ArrayList getstarblog(int a,int b){
        return blogmapper.getstarblog(a,b);
    }
    public ArrayList getitblog(){
        return blogmapper.getitblog();
    }
    public ArrayList getotblog(){
        return blogmapper.getotblog();
    }
    public int setbcom(Bcomment b){
        blogmapper.setcom(b);
        return 1;
    }
    public ArrayList getbcoms(int b){
        return blogmapper.getbcoms(b);
    }

    public int setattention(Attention a){
        blogmapper.setattention(a);
        return 1;
    }
    public Attention getattention(int a,int b){
        return blogmapper.getattention(a,b);
    }
    public int delattention(int a,int b){
        blogmapper.delattention(a,b);
        return 1;
    }
    public void upblog(int a,int b){
        blogmapper.upblog(a,b);
    }
    public  void upblogacc(int a,int b){
        blogmapper.upblogacc(a,b);
    }
    public void setcollect(Collect c){
        blogmapper.setcollect(c);
    }
    public void delcollect(Collect c){
        blogmapper.delcollect(c);
    }
    public Collect getcollect(Collect c){
      return  blogmapper.getcollect(c);
    }
    public ArrayList getstarlist (int a){
        return blogmapper.getstarlist(a);
    }
    public void delblog(int a){
         blogmapper.delblog(a);
    }
    public ArrayList getlikeblog(int a,String b){
        return  blogmapper.getlikeblog(a,b);
    }
    public void updateblog(Blog b){
        blogmapper.updateblog(b);
    }
    public ArrayList getmyblog(int a,int b){
        return blogmapper.getmyblog(a,b);
    }
    public void setdraft(draft a){
        blogmapper.setdraft(a);
    }
    public ArrayList getnewdra(int a){
        return  blogmapper.getnewdra(a);
    }
    public void deldra(int a){
        blogmapper.deldra(a);
    }
}
