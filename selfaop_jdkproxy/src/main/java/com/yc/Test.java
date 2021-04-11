package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-10 20:36
 */
public class Test {
    public static void main(String[] args) {
        StudentBiz stu=new StudentBizImpl();
        LogAspect lc=new LogAspect(stu);
        Object obj=lc.creatProxy(); // obj 就是代理对象
        System.out.println(obj);
        if(obj instanceof  StudentBiz){
            StudentBiz sb= (StudentBiz) obj;
            sb.find("张三");
            sb.add("李四");
            sb.update("王五");
        }
    }
}
