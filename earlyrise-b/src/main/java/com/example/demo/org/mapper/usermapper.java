package com.example.demo.org.mapper;

import com.example.demo.org.model.User;
import org.apache.ibatis.annotations.Mapper;
/**
 * Created by Administrator on 2020/2/1.
 */
@Mapper
public interface usermapper {
    public User getuser(String user);
    public void setuser(User user);
    public User getuserById(int i);
    public void updateuser(User user);
}
