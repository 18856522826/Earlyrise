package com.example.demo;

import com.example.demo.org.model.Bar;
import org.junit.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class testbar  extends DemoApplicationTests{

    @Autowired
    private com.example.demo.org.service.barservice barservice;
    private Bar a=new Bar();
    @Test
   public void contextLoads() {
        a.setBaruser(1);
        a.setBarname("uuuu");
        a.setBarimg("asdasd");
        a.setBarbrief("asdhasild");
        Assert.assertSame("贴吧注册有误",1,barservice.barreg(a)); }}
