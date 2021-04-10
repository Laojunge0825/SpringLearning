package com.yc.bean2;

import com.yc.springframework.stereotype.MyAutowired;
import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyResource;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 14:57
 */
@MyComponent
public class HelloWorld {
    private MySession session1;
    private MySession session2;


    @MyPostConstruct
    public void setUp() {
        System.out.println("MyPostConstruct");
    }

    //@MyPreDestroy
    public void destroy() {
        System.out.println("MyPreDestroy");
    }

    public HelloWorld() {
        System.out.println("hello world 构造");
    }

    @MyAutowired
    public void setSession(MySession session) {
        this.session1 = session;
    }

   @MyResource(name = "session")
    public void setSession2(MySession session) {
        this.session2 = session;
    }

    public void show() {
        System.out.println("show" + "\t" + "\t"+this.session1.toString()+this.session2.toString());
    }


}
