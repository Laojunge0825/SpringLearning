package com.yc.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: Spring01
 * @author: 作者
 * @create: 2021-04-10 18:51
 */
@Aspect
@Component
@Order(value=1)
public class Log2Aspect {
    //切入点的声明
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))")//切入点表达式  在哪些方法上增强
    private void add(){

    }
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))")
    private  void update(){

    }
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    private  void find(){

    }
    @Pointcut("add()||update()")
    private void addAndUpdate(){

    }


    //增强的声明
    @Before("com.yc.aspect.Log2Aspect.addAndUpdate()")
    public void Log(){
        System.out.println("=========前置增强日志1==========");
        Date d=new Date();
        SimpleDateFormat sd =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s=sd.format(d);
        System.out.println("执行时间1："+s);
        System.out.println("=========前置增强日志结束1==============");
    }
}
