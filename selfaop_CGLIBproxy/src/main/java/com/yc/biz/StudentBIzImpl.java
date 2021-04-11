package com.yc.biz;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-11 14:59
 */
public class StudentBIzImpl {
    public int add(String name) {
        System.out.println("调用了StudentBizImpl中的add:"+name);
        return 111;
    }


    public void update(String name) {
        System.out.println("调用了StudentBizImpl中的update:"+name);
    }

    public String find(String name) {
        System.out.println("调用了StudentBizImpl中的find:"+name);
        return name+"find";
    }
}
