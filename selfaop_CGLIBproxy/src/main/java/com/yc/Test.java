package com.yc;

import com.yc.biz.StudentBIzImpl;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-11 15:16
 */
public class Test {
    public static void main(String[] args) {
    StudentBIzImpl biz=new StudentBIzImpl();
    LogAspect la=new LogAspect(biz);

    //生成代理对象
        Object o=la.creatProxy();
       System.out.println(o);
       if (o instanceof  StudentBIzImpl){
           StudentBIzImpl s= (StudentBIzImpl) o;

           s.find("李四");
           s.add("张三");
           s.update("王五");
       }

    }
}
