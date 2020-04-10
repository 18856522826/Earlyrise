package com.example.demo.org.mapper;

import com.example.demo.org.model.Comment_t;
import com.example.demo.org.model.Post;
import com.example.demo.org.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface postmapper {
    public void addpost(Post p);
    public ArrayList<Post> getallpost(int a);
    public ArrayList<Post> getbarpost(int a);
    public ArrayList<Post> getpostbyInput(String input);
    public Post getpost (int a);
    public void setcomment(Comment c);
    public void upcount(@Param("count") int a,@Param("id")int b);
    public ArrayList<Comment> getcomment(int a);
    public void setcomment_t(Comment_t c);
    public ArrayList<Comment_t> getcomment_t(int a);
}
