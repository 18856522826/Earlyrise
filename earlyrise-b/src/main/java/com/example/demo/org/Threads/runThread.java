package com.example.demo.org.Threads;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class runThread implements ApplicationRunner {
    @Autowired
    private  Att_thread att_thread;
    @Autowired
    private good_thread good_thread;
    @Autowired
    private acess_thread acess_thread;
    @Autowired
     private coll_thread coll_thread;
    @Autowired
    private  score_thread score_thread;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread t1=new Thread(att_thread);
        Thread t2=new Thread(good_thread);
        Thread t3=new Thread(acess_thread);
        Thread t4=new Thread(coll_thread);
        Thread t5=new Thread(score_thread);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
