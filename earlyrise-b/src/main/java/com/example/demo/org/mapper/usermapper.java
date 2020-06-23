package com.example.demo.org.mapper;

import com.example.demo.org.model.Admin;
import com.example.demo.org.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2020/2/1.
 */
@Mapper
public interface usermapper {
    public User getuser(String user);
    public void setuser(User user);
    public User getuserById(int i);
    public void updateuser(User user);
    public Admin getadmin(String user);
    public void upusersc (@Param("uid") int uid,@Param("count") int count);
    public ArrayList getmyatt(int a);
    public ArrayList gettopuser();
    public ArrayList getalluser(int a);
    public void deluserid(int a);
}
