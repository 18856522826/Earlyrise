package com.example.demo.org.mapper;
import com.example.demo.org.model.Qcollect;
import com.example.demo.org.model.Qcomment;
import com.example.demo.org.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface qutmapper {
    public void setquestion(Question q);
    public ArrayList getnewqut(int a);
    public Question getquestion(int a);
    public void upqutgood(@Param("count") int a,@Param("id")int b);
    public void setqcollect(Qcollect q);
    public Qcollect getqcollect(Qcollect q);
    public void delqcollect(Qcollect q);
    public ArrayList getstarqut(@Param("count") int a,@Param("uid")int b );
    public ArrayList getmaxqut(int a);
    public void upqutacc(@Param("qid")int a,@Param("access")int b);
    public void setqcomment(Qcomment q);
    public ArrayList<Qcomment> getqcomment(int a);
    public void setqcomsign(int a);
    public  void setquessign(int a);
    public ArrayList getmyqut(@Param("uid")int a,@Param("count")int count);
    public void delqut(int a);
    public ArrayList gettit(String t);
    public void upqut(Question q);
}
