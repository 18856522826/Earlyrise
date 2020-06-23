package com.example.demo.org.mapper;

import com.example.demo.org.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.ArrayList;

@Mapper
public interface blogmapper {
    public void setblog(Blog b);
    public Blog getblog(int a);
    public ArrayList getnewblog(int a);
    public ArrayList getmaxblog(int a);
    public ArrayList getitblog();
    public ArrayList getstarblog(@Param("uid")int a,@Param("count") int b);
    public ArrayList getotblog();
    public void setcom(Bcomment b);
    public ArrayList getbcoms(int b);
    public void setattention(Attention a);
    public  Attention getattention(@Param("a")int a,@Param("b")int b);
    public  void delattention(@Param("a")int a,@Param("b")int b);
    public void upblog(@Param("bid")int a,@Param("star")int b);
    public void upblogacc(@Param("bid")int a,@Param("access")int b);
    public void setcollect(Collect c);
    public void delcollect(Collect c);
    public Collect getcollect(Collect C);
    public ArrayList getstarlist(int a);
    public void delblog(int a);
    public ArrayList getlikeblog(@Param("count")int a,@Param("like")String b);
    public void updateblog(Blog b);
    public ArrayList getmyblog(@Param("uid")int a,@Param("count")int count);
    public void setdraft(draft a);
    public ArrayList getnewdra(int a);
    public void deldra(int a);
}

