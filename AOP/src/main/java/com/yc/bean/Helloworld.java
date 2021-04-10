package com.yc.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-05 14:20
 */
//@Component
public class Helloworld {
    @PostConstruct
    public void setup(){
        System.out.println("MyPostConstruct");
    }
    @PreDestroy
    public void destory(){
        System.out.println("MyPreDestory");
    }


    public Helloworld(){
        System.out.println(" hello world 构造");
    }
    public void show(){
        System.out.println("show");
    }
}
