package com.yc.bean;

import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestory;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-05 14:20
 */
@MyComponent
public class Helloworld {
    @MyPostConstruct
    public void setup(){
        System.out.println("MyPostConstruct");
    }
    @MyPreDestory
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
