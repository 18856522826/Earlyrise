package com.example.demo.org.service;

import com.example.demo.org.controller.barcontroller;
import com.example.demo.org.mapper.barmapper;
import com.example.demo.org.model.Bar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
@Service
public class barservice {
    @Autowired
    private com.example.demo.org.mapper.barmapper barmapper;
    @Autowired
    private com.example.demo.org.mapper.usermapper usermapper;

    public ArrayList<Bar> getallbar(String data){

        return barmapper.getallBar(data);
    }
    public String savefile(String name,byte file[],String path) throws IOException {
        File f=new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        File ff=new File(path+name);
        if(!ff.exists()){
            ff.createNewFile();
        }
        FileOutputStream o = new FileOutputStream(ff);
        o.write(file);
        o.close();
        return "earlyriseu/"+name;
    }
    public int barreg(Bar b){
       if(barmapper.getbar(b.getBarname())!=null){
           return 0;
       }
        barmapper.barreg(b);
        return 1;
    }
    public Bar getbar(String name){
        return  barmapper.getbar(name);
    }
}
