package com.example.demo.org.service;

import com.example.demo.org.mapper.qutmapper;
import com.example.demo.org.model.Qcollect;
import com.example.demo.org.model.Qcomment;
import com.example.demo.org.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.ArrayList;

@Service
public class qutservice {
    @Resource
    private com.example.demo.org.mapper.qutmapper qutmapper;
    public void setquestion(Question q){
        qutmapper.setquestion(q);
    }
    public ArrayList getnewqut(int a){
       return qutmapper.getnewqut(a);
    }
    public Question getquestion(int a){
        return  qutmapper.getquestion(a);
    }
    public void upqutgood(int a,int b){
        qutmapper.upqutgood(a,b);
    }
    public void setqcollect(Qcollect q){
        qutmapper.setqcollect(q);
    }
    public Qcollect getqcollect(Qcollect q){
      return  qutmapper.getqcollect(q);
    }
    public void delqcollect(Qcollect q){
        qutmapper.delqcollect(q);
    }
    public ArrayList getstarqut(int a,int b){
        return qutmapper.getstarqut(a,b);
    }
    public ArrayList getmaxqut(int a){
      return   qutmapper.getmaxqut(a);
    }
    public void upqutacc(int a,int b){
          qutmapper.upqutacc(a,b);
    }
    public void setqcomment(Qcomment qcomment){
        qutmapper.setqcomment(qcomment);
    }
    public ArrayList getqcomment(int a){
        return qutmapper.getqcomment(a);
    }
    public void setqcomsign(int a){
        qutmapper.setqcomsign(a);
    }
    public void setquessign(int a){
        qutmapper.setquessign(a);
    }
    public ArrayList getmyqut(int a,int b){
        return    qutmapper.getmyqut(a,b);
    }
    public void delqut(int a){
        qutmapper.delqut(a);
    }
    public ArrayList gettit(String t){
       return qutmapper.gettit(t);
    }
    public void upqut(Question q){
        qutmapper.upqut(q);
    }
}
