package com.yc;

import com.yc.bean2.HelloWorld;
import com.yc.springframework.context.MyAnnotationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-05 14:35
 */
public class Test {
    public static void main(String[] args) {
        MyApplicationContext ac=new MyAnnotationConfigApplicationContext(MyAppConfig.class);
        HelloWorld hw = (HelloWorld) ac.getBean("helloWorld");
        hw.show();
    }
}
