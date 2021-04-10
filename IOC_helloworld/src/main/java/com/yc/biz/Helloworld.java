package com.yc.biz;

import org.springframework.stereotype.Component;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-04 15:32
 */
@Component    //只要加了这个注解  则这个类可以被spring容器托管
public class Helloworld {
    public  Helloworld(){
        System.out.println("无参构造方法");
    }
    public void hello(){
        System.out.println("hello  world");
    }

}
