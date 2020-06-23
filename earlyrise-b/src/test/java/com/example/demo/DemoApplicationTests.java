package com.example.demo;

import com.example.demo.org.model.Bar;
import com.example.demo.org.service.barservice;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }
    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}
